package com.nisanth.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource
{

    private UserDaoService service;
    private UserResource(UserDaoService service)
    {
        this.service=service;
    }

    @GetMapping("/users")
   public List<User> retrieveAllUsers()
   {
       // get details from userdaoservice
       return service.findAll();
   }

   // EntityModel
    // WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id)
    {
        User user= service.findOne(id);
        if(user==null)
        {
            throw new UserNotFoundException("id: " +id);

        }

      EntityModel<User> entityModel=EntityModel.of(user);
     WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
       service.deleteById(id);
    }

    @PostMapping("/post/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
       User savedUser= service.save(user);
       // get id of newly created user
        URI location= ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
      return  ResponseEntity.created(location).build();
    }
}
