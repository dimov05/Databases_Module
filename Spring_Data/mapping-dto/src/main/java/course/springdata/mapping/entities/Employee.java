package course.springdata.mapping.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    @NonNull
    private String firstName;
    @Column(name = "last_name")
    @NonNull
    private String lastName;
    @NonNull
    private double salary;
    @NonNull
    private LocalDate birthday;
    @NonNull
    @ManyToOne
    private Address address;
    private boolean onVacation;
    @ManyToOne(optional = true)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Employee manager;
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Employee> subordinates = new ArrayList<>();

}
