package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.Seller;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Optional<Seller> findByEmail(String email);
}
