package course.springdata.restmvc.init;

import course.springdata.restmvc.dao.WebPostRepository;
import course.springdata.restmvc.entity.WebPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private WebPostRepository webPostRepo;

    @Override
    public void run(String... args) throws Exception {
        if (webPostRepo.count() == 0) {
            webPostRepo.saveAll(List.of(
                    new WebPost("Reactive Spring",
                            "Web Flux is here...",
                            "Dimo Dimov",
                            Set.of("Java", "Spring", "React")),
                    new WebPost("Easy to Test",
                            "WebTestClient is easy...",
                            "Dimo Dimov",
                            Set.of("Spring", "web test client")),
                    new WebPost("New in Spring",
                            "Spring Boot is fancy...",
                            "Dimo Dimov",
                            Set.of("Java", "Spring", "Boot"))
            ));
        }

    }
}
