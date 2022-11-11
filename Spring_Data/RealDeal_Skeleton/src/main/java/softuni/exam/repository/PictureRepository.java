package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entities.Picture;

import java.util.Optional;

//ToDo
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    Optional<Picture> findByName(String name);
}
