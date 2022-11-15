package course.springdata.cardealer.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CarService {
    void seedCars() throws Exception;
    String findByToyota();

    void seedCarsFromXml() throws Exception;

    void carsAndParts() throws JAXBException;
}
