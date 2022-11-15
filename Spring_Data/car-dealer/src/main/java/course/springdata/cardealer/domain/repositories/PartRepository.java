package course.springdata.cardealer.domain.repositories;

import course.springdata.cardealer.domain.entities.Part;
import course.springdata.cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
