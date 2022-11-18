package entities.hospital_database;

import entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {
    private LocalDateTime date;
    private String comments;
    private Patient patient;
    private Diagnose diagnose;
    private Set<Medicament> medicaments;

    public Visitation() {
    }

    @Column(name = "date")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "comments", length = 2000)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @OneToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    @OneToMany()
    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
