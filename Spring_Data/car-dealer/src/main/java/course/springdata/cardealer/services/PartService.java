package course.springdata.cardealer.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PartService {
    void seedParts() throws Exception;
    void seedPartsFromXml() throws Exception;
}
