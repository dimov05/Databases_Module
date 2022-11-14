package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Apartment;

// TODO:
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
