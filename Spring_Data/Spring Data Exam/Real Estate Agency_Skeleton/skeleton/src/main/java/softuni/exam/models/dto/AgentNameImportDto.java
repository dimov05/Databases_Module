package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agent")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentNameImportDto {
    @XmlElement(name = "name")
    private String name;

    public AgentNameImportDto() {
    }

    public String getName() {
        return name;
    }

    public AgentNameImportDto setName(String name) {
        this.name = name;
        return this;
    }
}
