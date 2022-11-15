package course.springdata.cardealer.services.impl;

import course.springdata.cardealer.domain.dtos.export.lastExport.SaleExportDto;
import course.springdata.cardealer.domain.dtos.export.lastExport.SaleExportRootDto;
import course.springdata.cardealer.domain.entities.Car;
import course.springdata.cardealer.domain.entities.Customer;
import course.springdata.cardealer.domain.entities.Sale;
import course.springdata.cardealer.domain.repositories.CarRepository;
import course.springdata.cardealer.domain.repositories.CustomerRepository;
import course.springdata.cardealer.domain.repositories.SaleRepository;
import course.springdata.cardealer.services.SaleService;
import course.springdata.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepo;
    private final CarRepository carRepo;
    private final CustomerRepository customerRepo;
    private static final String SALE_DISCOUNT_PATH = "src/main/resources/exported/sales-discounts.xml";
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepo, CarRepository carRepo, CustomerRepository customerRepo, ModelMapper modelMapper, XmlParser xmlParser) {
        this.saleRepo = saleRepo;
        this.carRepo = carRepo;
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedSales() {
        Sale sale1 = new Sale();
        sale1.setCustomer(getRandomCustomer());
        sale1.setCar(getRandomCar());
        sale1.setDiscount(5);

        Sale sale2 = new Sale();
        sale2.setCustomer(getRandomCustomer());
        sale2.setCar(getRandomCar());
        sale2.setDiscount(10);

        Sale sale3 = new Sale();
        sale3.setCustomer(getRandomCustomer());
        sale3.setCar(getRandomCar());
        sale3.setDiscount(30);

        this.saleRepo.saveAndFlush(sale1);
        this.saleRepo.saveAndFlush(sale2);
        this.saleRepo.saveAndFlush(sale3);
    }

    @Override
    public void salesDiscounts() throws JAXBException {
        SaleExportRootDto saleExportRootDto = new SaleExportRootDto();
        List<SaleExportDto> saleExportDtos = new ArrayList<>();
        for (Sale sale : this.saleRepo.findAll()) {
            SaleExportDto saleExportDto = this.modelMapper.map(sale, SaleExportDto.class);
            saleExportDto.setDiscount(saleExportDto.getDiscount() / 100);
            double totalPrice = sale.getCar().getParts()
                    .stream()
                    .mapToDouble(p -> Double.parseDouble(p.getPrice().toString())).sum();
            saleExportDto.setPrice(BigDecimal.valueOf(totalPrice));
            double priceWithDiscount = totalPrice - totalPrice * sale.getDiscount() * 1.00 / 100;
            saleExportDto.setPriceWithDiscount(BigDecimal.valueOf(priceWithDiscount));

            saleExportDtos.add(saleExportDto);
        }

        saleExportRootDto.setSales(saleExportDtos);
        this.xmlParser.exportXml(saleExportRootDto, SaleExportRootDto.class,SALE_DISCOUNT_PATH);
    }

    private Customer getRandomCustomer() {
        Random random = new Random();
        long id = (long) random.nextInt((int) this.customerRepo.count()) + 1;
        return this.customerRepo.findById(id).get();
    }

    private Car getRandomCar() {
        Random random = new Random();
        long id = (long) random.nextInt((int) this.carRepo.count()) + 1;
        return this.carRepo.findById(id).get();
    }
}
