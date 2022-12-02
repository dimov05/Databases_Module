package course.springdata.advanced.repositories;

import course.springdata.advanced.entities.Label;
import course.springdata.advanced.entities.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
