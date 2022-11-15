package course.springdata.cardealer.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CustomerService {
    void seedCustomers() throws IOException;

    void seedCustomersFromXml() throws IOException, JAXBException;

    String orderedCustomer();

    void exportOrdered() throws JAXBException;
}
