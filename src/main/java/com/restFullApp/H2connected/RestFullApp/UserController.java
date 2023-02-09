package com.restFullApp.H2connected.RestFullApp;

import com.restFullApp.H2connected.RestFullApp.post.Post;
import com.restFullApp.H2connected.RestFullApp.post.PostJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserJpaResource service;

    @Autowired
    private PostJpaRepository postService;

    public void setRepo(UserJpaResource repo, PostJpaRepository postJpaRepository) {
        this.postService = postJpaRepository;
        this.service = repo;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Buuuuna";
    }
    @GetMapping("/users")
    public List<User> allUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user = service.findById((long) id);

        if (user.isEmpty())
            throw new UserNotFoundException("Userul cu id = " + id + " nu a fost gasit");

        EntityModel<User> userEntityModel =  EntityModel.of(user.get());

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allUsers());
        userEntityModel.add(link.withRel("all-Users"));

        return userEntityModel;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        service.deleteById((long) id);
        return "S-a sters sefule, ce mai cauti pe site ???";
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrievePostsForaUser(@PathVariable int id){
        Optional<User> user = service.findById((long) id);

        if (user.isEmpty())
            throw new UserNotFoundException("Userul cu id = " + id + " nu a fost gasit");
        return user.get().getPosts();

    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<User> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> user = service.findById((long) id);

        if (user.isEmpty())
            throw new UserNotFoundException("Userul cu id = " + id + " nu a fost gasit");

        post.setUser(user.get());
        Post savedPost = postService.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts/{id_post}")
    public Post retrieveOnePostsForaUser(@PathVariable int id,@PathVariable int id_post){
        Optional<User> user = service.findById((long) id);

        if (user.isEmpty())
            throw new UserNotFoundException("Userul cu id = " + id + " nu a fost gasit");

        Optional<Post> post = postService.findById(id_post);
        if (post.isEmpty())
            throw new UserNotFoundException("Userul cu id = " + id + " nu a fost gasit");
        return post.get();

    }
}
