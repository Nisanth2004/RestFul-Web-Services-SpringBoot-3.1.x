package com.nisanth.restfulwebservices.jpa;

import com.nisanth.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
