package course.springdata.cardealer.services;

import javax.xml.bind.JAXBException;

public interface SaleService {
    void seedSales();

    void salesDiscounts() throws JAXBException;
}
