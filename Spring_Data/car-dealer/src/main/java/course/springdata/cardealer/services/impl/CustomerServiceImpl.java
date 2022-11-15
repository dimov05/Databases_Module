package course.springdata.cardealer.services.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dtos.export.CustomerExportDto;
import course.springdata.cardealer.domain.dtos.export.CustomerOrderedExportDto;
import course.springdata.cardealer.domain.dtos.export.CustomerOrderedExportRootDto;
import course.springdata.cardealer.domain.dtos.imports.CustomerImportDto;
import course.springdata.cardealer.domain.dtos.imports.CustomerImportRootDto;
import course.springdata.cardealer.domain.dtos.imports.CustomerSeedDto;
import course.springdata.cardealer.domain.entities.Customer;
import course.springdata.cardealer.domain.repositories.CustomerRepository;
import course.springdata.cardealer.services.CustomerService;
import course.springdata.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMERS_PATH = "src/main/resources/jsons/customers.json";
    private static final String CUSTOMERS_ORDERED_PATH = "src/main/resources/exported/ordered-customers.xml";
    private static final String CUSTOMERS_PATH_XML = "src/main/resources/xmls/customers.xml";
    private final CustomerRepository customerRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    public CustomerServiceImpl(CustomerRepository customerRepo, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of(CUSTOMERS_PATH)));

        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(content, CustomerSeedDto[].class);
        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
            Customer customer = this.modelMapper.map(customerSeedDto, Customer.class);
            this.customerRepo.saveAndFlush(customer);
        }
    }

    @Override
    public void seedCustomersFromXml() throws IOException, JAXBException {
        CustomerImportRootDto customerImportRootDto = this.xmlParser.parseXml(CustomerImportRootDto.class, CUSTOMERS_PATH_XML);
        for (CustomerImportDto customerImportDto : customerImportRootDto.getCustomers()) {
            this.customerRepo.saveAndFlush(this.modelMapper.map(customerImportDto, Customer.class));
        }
    }

    @Override
    public String orderedCustomer() {
        Set<Customer> customers = this.customerRepo.getAllByOrderByBirthDateAscYoungDriverAsc();

        Set<CustomerExportDto> toExport = new HashSet<>();
        for (Customer customer : customers) {
            CustomerExportDto customerExportDto = this.modelMapper.map(customer, CustomerExportDto.class);
            toExport.add(customerExportDto);
        }
        return this.gson.toJson(toExport);
    }

    @Override
    public void exportOrdered() throws JAXBException {
        List<CustomerOrderedExportDto> dtos = this.customerRepo.findAllAndSort()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerOrderedExportDto.class))
                .collect(Collectors.toList());
        CustomerOrderedExportRootDto rootDto = new CustomerOrderedExportRootDto();
        rootDto.setCustomers(dtos);
        this.xmlParser.exportXml(rootDto, CustomerOrderedExportRootDto.class, CUSTOMERS_ORDERED_PATH);
    }
}
