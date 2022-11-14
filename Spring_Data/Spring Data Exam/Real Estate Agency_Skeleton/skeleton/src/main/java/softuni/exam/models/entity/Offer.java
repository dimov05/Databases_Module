package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    private BigDecimal price;
    private LocalDate publishedOn;
    private Agent agent;
    private Apartment apartment;

    public Offer() {
    }

    public Offer(BigDecimal price, LocalDate publishedOn) {
        this.price = price;
        this.publishedOn = publishedOn;
    }

    @ManyToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Column(name = "price",nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "published_on",nullable = false)
    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }
}
