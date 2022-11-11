package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.exam.models.entities.Car;

import java.util.Set;

//ToDo
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT c FROM Car c ORDER BY c.pictures.size DESC, c.make")
    Set<Car> exportCars();
}
