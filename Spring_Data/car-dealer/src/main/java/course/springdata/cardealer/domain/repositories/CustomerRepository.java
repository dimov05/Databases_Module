package course.springdata.cardealer.domain.repositories;

import course.springdata.cardealer.domain.entities.Customer;
import course.springdata.cardealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Set<Customer> getAllByOrderByBirthDateAscYoungDriverAsc();

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate, c.youngDriver DESC")
    Set<Customer> findAllAndSort();
}
