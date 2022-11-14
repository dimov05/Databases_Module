package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {
    private String townName;
    private int population;
    private Set<Agent> agents;
    private Set<Apartment> apartments;

    public Town() {
    }

    public Town(String townName, int population) {
        this.townName = townName;
        this.population = population;
    }

    @OneToMany(mappedBy = "town")
    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    @OneToMany(mappedBy = "town")
    public Set<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Set<Agent> agents) {
        this.agents = agents;
    }

    @Column(name = "town_name", unique = true,nullable = false)
    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    @Column(name = "population",nullable = false)
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
