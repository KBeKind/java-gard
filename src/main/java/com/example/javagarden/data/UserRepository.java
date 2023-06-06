package com.example.javagarden.data;

import com.example.javagarden.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByUsername(String username);

    Optional<User> findByUsername(String username);

    Boolean existByUserName(String username);



}