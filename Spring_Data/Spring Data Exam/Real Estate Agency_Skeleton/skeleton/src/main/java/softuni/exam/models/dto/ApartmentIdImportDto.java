package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentIdImportDto {
    @XmlElement(name = "id")
    private Long id;

    public ApartmentIdImportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
