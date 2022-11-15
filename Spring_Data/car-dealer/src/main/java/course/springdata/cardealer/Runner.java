package course.springdata.cardealer;

import course.springdata.cardealer.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public Runner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.supplierService.seedSupplier();
        //this.partService.seedParts();
        //this.carService.seedCars();
        //this.customerService.seedCustomers();
        //this.saleService.seedSales();

        //System.out.println(this.customerService.orderedCustomer());
        //System.out.println(this.carService.findByToyota());
        //System.out.println(this.supplierService.findSuppliersWhichDoNotImportFromAbroad());

        // XML Proccessing exercise
//        this.supplierService.seedSuppliersFromXml();
//        this.partService.seedPartsFromXml();
//        this.carService.seedCarsFromXml();
//        this.customerService.seedCustomersFromXml();
        //this.customerService.exportOrdered();
        //this.carService.carsAndParts();
        //this.saleService.salesDiscounts();
    }
}
