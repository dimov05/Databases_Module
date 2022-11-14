package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferRootImportDto {
    @XmlElement(name = "offer")
    private List<OfferImportDto> offers;

    public OfferRootImportDto() {
    }

    public List<OfferImportDto> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferImportDto> offers) {
        this.offers = offers;
    }
}
