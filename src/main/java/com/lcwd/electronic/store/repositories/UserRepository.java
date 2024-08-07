package com.lcwd.electronic.store.repositories;

import com.lcwd.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);
    //represent user is getting or not so we use optinal class
    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findByNameContaining(String keywords);
}
