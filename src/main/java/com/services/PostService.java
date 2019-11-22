package com.services;

import com.models.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "https://jsonplaceholder.typicode.com", name = "app")
public interface PostService {

    @GetMapping("posts")
    List<Post> posts();

    @GetMapping("posts/{id}")
    Post postById(@PathVariable("id") int id);

    @PostMapping("posts")
    Post post(Post post);
}
