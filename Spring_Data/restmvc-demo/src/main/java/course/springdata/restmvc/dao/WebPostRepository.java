package course.springdata.restmvc.dao;

import course.springdata.restmvc.entity.WebPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebPostRepository extends JpaRepository<WebPost, Long> {
}
