package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentImportDto;
import softuni.exam.models.dto.ApartmentRootImportDto;
import softuni.exam.models.entity.Apartment;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    public static final String APARTMENTS_PATH = "C:\\Users\\PC\\Desktop\\Databases_Module\\Spring_Data\\Spring Data Exam\\Real Estate Agency_Skeleton\\skeleton\\src\\main\\resources\\files\\xml\\apartments.xml";
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;
    private final ValidatorUtil validatorUtil;
@Autowired
    public ApartmentServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, ApartmentRepository apartmentRepository, TownRepository townRepository, ValidatorUtil validatorUtil) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(APARTMENTS_PATH)));
    }

    @Override
    public String importApartments() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        ApartmentRootImportDto apartmentRootImportDto = this.xmlParser.parseXml(ApartmentRootImportDto.class, APARTMENTS_PATH);
        for (ApartmentImportDto apartmentImportDto : apartmentRootImportDto.getApartments()) {
            if (validatorUtil.isValid(apartmentImportDto)) {
                Apartment apartment = this.modelMapper.map(apartmentImportDto, Apartment.class);
                apartment.setTown(this.townRepository.findByTownName(apartmentImportDto.getTown()));
                this.apartmentRepository.saveAndFlush(apartment);

                sb.append(String.format("Successfully imported apartment %s - %.2f",
                                apartment.getApartmentType(), apartment.getArea()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid apartment").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
