package course.springdata.restmvc.web;

import course.springdata.restmvc.dao.WebPostRepository;
import course.springdata.restmvc.entity.WebPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/posts")
public class WebPostController {
    @Autowired
    private WebPostRepository webPostRepo;

    @GetMapping
    public Collection<WebPost> getAllWebPosts() {
        return webPostRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebPost addWebPost(@RequestBody WebPost webPost) {
        return webPostRepo.save(webPost);
    }

    @GetMapping("/{id}")
    public WebPost getWebPost(@PathVariable Long id) {
        return webPostRepo.findById(id).orElseThrow();
    }
}
