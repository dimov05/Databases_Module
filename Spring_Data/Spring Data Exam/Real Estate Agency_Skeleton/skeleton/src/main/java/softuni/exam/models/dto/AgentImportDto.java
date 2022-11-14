package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AgentImportDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String town;
    @Expose
    private String email;

    public AgentImportDto() {
    }

    @Length(min = 2)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 2)
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Length(min = 2)
    @NotNull
    public String getTownName() {
        return town;
    }

    public void setTownName(String townName) {
        this.town = townName;
    }

    @Pattern(regexp = "^(\\w+@\\w+)(.\\w+){2,}$")
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AgentImportDto setTown(String town) {
        this.town = town;
        return this;
    }
}
