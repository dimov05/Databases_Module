package course.springdata.cardealer.utils;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException;

    <O> void exportXml(O object, Class<O> objectClass, String filePath) throws JAXBException;
}
