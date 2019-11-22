package com.controllers;


import com.models.Post;
import com.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class PostController {
    @Autowired
    PostService postService;

    @Cacheable(value = "posts")
    @GetMapping("posts")
    public List<Post> posts() {
        System.out.println("from api  posts()");
        return postService.posts();
    }

    @Cacheable(value = "posts-id")
    @GetMapping("posts/{id}")
    public Post postById(@PathVariable("id") int id) {
        System.out.println("from api postById(@PathVariable(\"id\") int id)");
        return postService.postById(id);
    }

    //modification
  /*  @Caching(
            put = {@CachePut(value = "posts-id", key = "#post.id")}
            , evict = {@CacheEvict(value = "posts")}
    )*/
    @CacheEvict(value = "posts")
    @PostMapping("posts")
    public Post posts(@RequestBody Post post) {
        System.out.println("from api posts(@RequestBody Post post)");
        return postService.post(post);
    }


/*    class UserRepository {
        @CachePut(value = "userCache", key = "#result.username")
        @Transactional(readOnly = true)
        User getByUsername( String username );

        @CacheEvict(value = “userCache”, key = "#p0.username"),
        @Transactional
        void save( User user );
    }*/

    /*@Caching(
            put = {
                    @CachePut(value = "userCache", key = "'username:' + #result.username", condition = "#result != null"),
                    @CachePut(value = "userCache", key = "#result.id", condition = "#result != null")
            }
    )
    @Transactional(readOnly = true)
    public User getById( long id ) {
	   ...
    }*/

    /*@Cacheable(value = "reservationsCache", key = "#restaurand.id")
    public synchronized List<Reservation> getReservationsForRestaurant( Restaurant restaurant ) {
    }*/
}
