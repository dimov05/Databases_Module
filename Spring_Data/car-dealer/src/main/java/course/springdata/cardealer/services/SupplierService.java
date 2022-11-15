package course.springdata.cardealer.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SupplierService {
    void seedSupplier() throws IOException;
    void seedSuppliersFromXml() throws JAXBException;

    String findSuppliersWhichDoNotImportFromAbroad();
}
