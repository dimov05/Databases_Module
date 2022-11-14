package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentImportDto {
    @XmlElement(name = "apartmentType")
    private ApartmentType apartmentType;
    @XmlElement(name = "area")
    private double area;
    @XmlElement(name = "town")
    private String town;

    public ApartmentImportDto() {
    }

    @NotNull
    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }


    @DecimalMin("40.00")
    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTownName(String town) {
        this.town = town;
    }

    public ApartmentImportDto setTown(String town) {
        this.town = town;
        return this;
    }
}
