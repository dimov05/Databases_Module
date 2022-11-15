package course.springdata.cardealer.domain.repositories;

import course.springdata.cardealer.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
