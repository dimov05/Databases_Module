package course.springdata.cardealer.domain.repositories;

import course.springdata.cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Set<Supplier> findAllByImporterIsFalse();
}
