package com.nisanth.restfulwebservices.user;

import com.nisanth.restfulwebservices.jpa.PostRepository;
import com.nisanth.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource
{
    @Autowired
    private UserDaoService service;
    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;
   /* private UserJpaResource(UserRepository repository)
    {
        this.repository=repository;
    }*/

    @GetMapping("/jpa/users")
   public List<User> retrieveAllUsers()
   {
       // get details from userdaoservice
       return repository.findAll();
   }

   // EntityModel
    // WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id)
    {
        Optional<User> user= repository.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id: " +id);

        }

        EntityModel<User> entityModel=EntityModel.of(user.get());
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping ("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        repository.deleteById(id);
    }

    @GetMapping ("/jpa/users/{id}/posts")
    public List<Post> retrivePostForUser(@PathVariable int id)
    {
        Optional<User> user= repository.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id: " +id);

        }
        return  user.get().getPosts();

    }
    @PostMapping ("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post)
    {
        Optional<User> user= repository.findById(id);
        if(user.isEmpty())

            throw new UserNotFoundException("id: " +id);
            post.setUser(user.get());
           Post savedPost= postRepository.save(post);
            URI location= ServletUriComponentsBuilder.
                    fromCurrentRequest().
                    path("/{id}").
                    buildAndExpand(savedPost.getId()).toUri();
            return  ResponseEntity.created(location).build();




    }
    @PostMapping("/jpa/post/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
       User savedUser= repository.save(user);
       // get id of newly created user
        URI location= ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
      return  ResponseEntity.created(location).build();
    }
}
