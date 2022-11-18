package entities.football_betting;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {
    private String name;
    private Country country;

    public Town() {

    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
