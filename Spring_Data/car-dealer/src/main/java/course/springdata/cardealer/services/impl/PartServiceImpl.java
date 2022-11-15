package course.springdata.cardealer.services.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dtos.imports.PartImportDto;
import course.springdata.cardealer.domain.dtos.imports.PartImportRootDto;
import course.springdata.cardealer.domain.dtos.imports.PartSeedDto;
import course.springdata.cardealer.domain.entities.Part;
import course.springdata.cardealer.domain.entities.Supplier;
import course.springdata.cardealer.domain.repositories.PartRepository;
import course.springdata.cardealer.domain.repositories.SupplierRepository;
import course.springdata.cardealer.services.PartService;
import course.springdata.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final static String PARTS_PATH = "src/main/resources/jsons/parts.json";
    private ModelMapper modelMapper;
    private final Gson gson;
    private PartRepository partRepo;
    private final SupplierRepository supplierRepo;
    private final XmlParser xmlParser;
    private final static String PARTS_PATH_XML = "src/main/resources/xmls/parts.xml";

    @Autowired
    public PartServiceImpl(ModelMapper modelMapper, Gson gson, PartRepository partRepo, SupplierRepository supplierRepo, XmlParser xmlParser) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.partRepo = partRepo;
        this.supplierRepo = supplierRepo;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedParts() throws Exception {
        String content = String.join("", Files.readAllLines(Path.of(PARTS_PATH)));

        PartSeedDto[] partSeedDtos = this.gson.fromJson(content, PartSeedDto[].class);
        for (PartSeedDto partSeedDto : partSeedDtos) {
            Part part = this.modelMapper.map(partSeedDto, Part.class);
            part.setSupplier(getRandomSupplier());

            this.partRepo.saveAndFlush(part);

        }
    }

    @Override
    public void seedPartsFromXml() throws Exception {
        PartImportRootDto partImportRootDto = this.xmlParser.parseXml(PartImportRootDto.class, PARTS_PATH_XML);
        for (PartImportDto partDto : partImportRootDto.getParts()) {
            Part part = this.modelMapper.map(partDto, Part.class);
            part.setSupplier(this.getRandomSupplier());
            this.partRepo.saveAndFlush(part);
        }
    }

    private Supplier getRandomSupplier() throws Exception {
        Random random = new Random();
        long randomIndex = (long) random.nextInt((int) this.supplierRepo.count()) + 1;
        Optional<Supplier> supplier = this.supplierRepo.findById(randomIndex);
        if (supplier.isPresent()) {
            return supplier.get();
        } else {
            throw new Exception("Supplier does not exist");
        }
    }
}
