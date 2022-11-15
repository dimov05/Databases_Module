package course.springdata.cardealer.domain.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportRootDto {
    @XmlElement(name = "car")
    private List<CarExportBaseDto> cars;

    public CarExportRootDto() {
    }

    public List<CarExportBaseDto> getCars() {
        return cars;
    }

    public void setCars(List<CarExportBaseDto> cars) {
        this.cars = cars;
    }
}
