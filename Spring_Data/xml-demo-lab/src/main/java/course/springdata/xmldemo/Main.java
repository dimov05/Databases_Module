package course.springdata.xmldemo;

import course.springdata.xmldemo.model.Address;
import course.springdata.xmldemo.model.Person;
import course.springdata.xmldemo.model.PhoneNumber;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address(1L, "Bulgaria", "Plovdiv", "Sixth september, 50");
        Person person1 = new Person(1L, "Petar", "Petrov", address1,
                Set.of(new PhoneNumber("+359 877 779092"), new PhoneNumber("+359 878 123456")));

        // 1. Create JAXBContext
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            // 2. Create marshaller
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // 3 . Marshal POJO to XML
            marshaller.marshal(person1, new File("person.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
