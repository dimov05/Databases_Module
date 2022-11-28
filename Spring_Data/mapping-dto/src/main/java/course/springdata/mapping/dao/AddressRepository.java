package course.springdata.mapping.dao;

import course.springdata.mapping.entities.Address;
import course.springdata.mapping.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
