package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity {
    private ApartmentType apartmentType;
    private Double area;
    private Town town;
    private Set<Offer> offers;

    public Apartment() {
    }

    @OneToMany(mappedBy = "apartment")
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @ManyToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column(name = "apartment_type",nullable = false)
    @Enumerated(EnumType.STRING)
    public ApartmentType getApartmentType() {
        return apartmentType;
    }


    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    @Column(name = "area",nullable = false)
    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
