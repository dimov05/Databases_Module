package course.springdata.cardealer.services.impl;

import com.google.gson.Gson;
import course.springdata.cardealer.domain.dtos.export.*;
import course.springdata.cardealer.domain.dtos.imports.CarImportDto;
import course.springdata.cardealer.domain.dtos.imports.CarImportRootDto;
import course.springdata.cardealer.domain.dtos.imports.CarSeedDto;
import course.springdata.cardealer.domain.entities.Car;
import course.springdata.cardealer.domain.entities.Part;
import course.springdata.cardealer.domain.repositories.CarRepository;
import course.springdata.cardealer.domain.repositories.PartRepository;
import course.springdata.cardealer.services.CarService;
import course.springdata.cardealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final static String CARS_PATH = "src/main/resources/jsons/cars.json";
    private final static String CARS_PATH_XML = "src/main/resources/xmls/cars.xml";
    private final static String CARS_PARTS_XML = "src/main/resources/exported/cars-and-parts.xml";
    private final CarRepository carRepo;
    private final PartRepository partRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public CarServiceImpl(CarRepository carRepo, PartRepository partRepo, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.carRepo = carRepo;
        this.partRepo = partRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    @Transactional
    public void seedCars() throws Exception {
        String content = String.join("", Files.readAllLines(Path.of(CARS_PATH)));

        CarSeedDto[] carSeedDtos = this.gson.fromJson(content, CarSeedDto[].class);
        for (CarSeedDto carSeedDto : carSeedDtos) {
            Car car = this.modelMapper.map(carSeedDto, Car.class);
            car.setParts(getRandomParts());
            this.carRepo.saveAndFlush(car);
        }
    }

    @Override
    public String findByToyota() {
        Set<Car> toyotas = this.carRepo.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarExportDto> carExportDtos = new ArrayList<>();
        for (Car car : toyotas) {
            CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);
            carExportDtos.add(carExportDto);
        }
        return this.gson.toJson(carExportDtos);
    }

    @Override
    public void seedCarsFromXml() throws Exception {
        CarImportRootDto carImportRootDto = this.xmlParser.parseXml(CarImportRootDto.class, CARS_PATH_XML);
        for (CarImportDto carImportDto : carImportRootDto.getCars()) {
            Car car = this.modelMapper.map(carImportDto, Car.class);
            car.setParts(this.getRandomParts());
            this.carRepo.saveAndFlush(car);
        }
    }

    @Override
    public void carsAndParts() throws JAXBException {
        List<Car> all = this.carRepo.findAll();
        CarExportRootDto carRootDto = new CarExportRootDto();
        List<CarExportBaseDto> carExportDtos = new ArrayList<>();

        for (Car car : all) {
            CarExportBaseDto carExportDto = this.modelMapper.map(car, CarExportBaseDto.class);
            PartExportRootDto partRootDto = new PartExportRootDto();
            List<PartExportDto> partExportDtos = new ArrayList<>();

            for (Part part : car.getParts()) {
                PartExportDto partDto = this.modelMapper.map(part, PartExportDto.class);
                partExportDtos.add(partDto);
            }
            partRootDto.setParts(partExportDtos);
            carExportDto.setParts(partRootDto);
            carExportDtos.add(carExportDto);
        }
        carRootDto.setCars(carExportDtos);

        this.xmlParser.exportXml(carRootDto, CarExportRootDto.class, CARS_PARTS_XML);
    }

    private Set<Part> getRandomParts() throws Exception {
        Set<Part> parts = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Part part = getRandomPart();
            parts.add(part);
        }
        return parts;
    }

    private Part getRandomPart() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.partRepo.count()) + 1;
        Optional<Part> part = this.partRepo.findById(index);
        if (part.isPresent()) {
            return part.get();
        } else {
            throw new Exception("Invalid part Id when generating random part");
        }
    }
}
