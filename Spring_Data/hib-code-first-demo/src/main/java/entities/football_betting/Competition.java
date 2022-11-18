package entities.football_betting;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {
    private String name;
    private Competition competition;

    public Competition() {

    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "competition_type", referencedColumnName = "name")
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
