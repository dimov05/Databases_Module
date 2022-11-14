package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;

import java.util.List;

// TODO:
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("SELECT o FROM Offer o WHERE o.apartment.apartmentType = 'three_rooms' ORDER BY o.apartment.area DESC,o.price ASC")
    List<Offer> findAllByApartmentByApartmentTypeThreeRoomsOrderByApartmentAreaDescPriceAsc();

}
