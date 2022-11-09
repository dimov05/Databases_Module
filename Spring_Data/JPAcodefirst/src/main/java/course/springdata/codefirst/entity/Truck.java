package course.springdata.codefirst.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle {
    @Column(name = "load_capacity")
    private Double loadCapacity;

    public Truck(String model, BigDecimal price, String fuelType, Double loadCapacity) {
        super(model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public Truck(Long id, String model, BigDecimal price, String fuelType, Double loadCapacity) {
        super(id, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public Truck() {
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return "Car{" + super.toString() + ", " +
                "loadCapacity=" + loadCapacity +
                '}';
    }
}
