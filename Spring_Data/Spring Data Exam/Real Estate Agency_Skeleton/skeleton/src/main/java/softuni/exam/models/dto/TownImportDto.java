package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TownImportDto {
    @Expose
    private String townName;
    @Expose
    private int population;

    public TownImportDto() {
    }

    @Length(min = 2)
    @NotNull
    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @Min(value = 0)
    @NotNull
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
