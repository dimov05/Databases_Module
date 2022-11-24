package course.springdata.jsondemo.services;

import course.springdata.jsondemo.entities.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post addPost(Post post);

    Post updatePost(Post post);

    Post deletePost(Post post);

    long getPostsCount();
}
