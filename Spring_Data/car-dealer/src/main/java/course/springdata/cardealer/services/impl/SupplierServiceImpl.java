package course.springdata.cardealer.services.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dtos.export.SupplierExportDto;
import course.springdata.cardealer.domain.dtos.imports.SupplierImportDto;
import course.springdata.cardealer.domain.dtos.imports.SupplierImportRootDto;
import course.springdata.cardealer.domain.dtos.imports.SupplierSeedDto;
import course.springdata.cardealer.domain.entities.Supplier;
import course.springdata.cardealer.domain.repositories.SupplierRepository;
import course.springdata.cardealer.services.SupplierService;
import course.springdata.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final static String SUPPLIER_PATH = "src/main/resources/jsons/suppliers.json";
    private final static String SUPPLIER_PATH_XML = "src/main/resources/xmls/suppliers.xml";
    private final SupplierRepository supplierRepo;
    private ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepo, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.supplierRepo = supplierRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSupplier() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of(SUPPLIER_PATH)));

        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(content, SupplierSeedDto[].class);
        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
            this.supplierRepo.saveAndFlush(this.modelMapper.map(supplierSeedDto, Supplier.class));

        }
    }

    @Override
    public void seedSuppliersFromXml() throws JAXBException {
        SupplierImportRootDto supplierImportRootDto = this.xmlParser.parseXml(SupplierImportRootDto.class, SUPPLIER_PATH_XML);
        for (SupplierImportDto supplier : supplierImportRootDto.getSuppliers()) {
            this.supplierRepo.save(this.modelMapper.map(supplier, Supplier.class));
        }
    }

    @Override
    public String findSuppliersWhichDoNotImportFromAbroad() {
        Set<Supplier> suppliers = this.supplierRepo.findAllByImporterIsFalse();
        List<SupplierExportDto> supplierExportDtos = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            SupplierExportDto supplierExportDto = this.modelMapper.map(supplier, SupplierExportDto.class);
            supplierExportDtos.add(supplierExportDto);
        }
        return this.gson.toJson(supplierExportDtos);
    }
}
