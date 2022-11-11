package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.Offer;

//ToDo
public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
