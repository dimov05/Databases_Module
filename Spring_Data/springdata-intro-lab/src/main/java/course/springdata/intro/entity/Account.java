package course.springdata.intro.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private BigDecimal balance;

    @ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    @NonNull
    private User user;

    public Account(BigDecimal bigDecimal) {
    }
}
