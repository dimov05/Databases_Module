package softuni.exam.models.dto;

import softuni.exam.config.LocalDateAdapter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDto {
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "agent")
    private AgentNameImportDto name;
    @XmlElement(name = "apartment")
    private ApartmentIdImportDto apartmentId;
    @XmlElement(name = "publishedOn")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate publishedOnDate;

    public OfferImportDto() {
    }

    @DecimalMin("0")
    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public AgentNameImportDto getName() {
        return name;
    }

    public void setName(AgentNameImportDto name) {
        this.name = name;
    }

    @NotNull
    public ApartmentIdImportDto getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(ApartmentIdImportDto apartmentId) {
        this.apartmentId = apartmentId;
    }


    public LocalDate getPublishedOnDate() {
        return publishedOnDate;
    }

    public void setPublishedOnDate(LocalDate publishedOnDate) {
        this.publishedOnDate = publishedOnDate;
    }
}
