package course.springdata.codefirst.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {
    private int seats;
    @OneToOne(mappedBy = "car")
    private PlateNumber plate;

    public Car(String model, BigDecimal price, String fuelType, int seats) {
        super(model, price, fuelType);
        this.seats = seats;
    }

    public Car(Long id, String model, BigDecimal price, String fuelType, int seats) {
        super(id, model, price, fuelType);
        this.seats = seats;
    }

    public Car() {
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public PlateNumber getPlate() {
        return plate;
    }

    public void setPlate(PlateNumber plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "Car{" + super.toString() + ", " +
                "seats=" + seats +
                ", plate=" + plate +
                '}';
    }
}
