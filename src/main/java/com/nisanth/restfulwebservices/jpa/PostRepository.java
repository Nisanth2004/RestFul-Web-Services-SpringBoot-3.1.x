package com.nisanth.restfulwebservices.jpa;

import com.nisanth.restfulwebservices.user.Post;
import com.nisanth.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
