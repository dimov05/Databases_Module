package course.springdata.jsondemo.repositories;

import course.springdata.jsondemo.entities.Post;
import course.springdata.jsondemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByUsername(String username);
}
