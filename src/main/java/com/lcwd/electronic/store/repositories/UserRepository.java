package com.lcwd.electronic.store.repositories;

import com.lcwd.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

// UserRepository capability dont have thats why we extends the JpaRepository
// Jpaprovide have all method that we perform db operation like sorting,find
// JpaRepository extends PagingAndSortingRepository extends CrudRepository vs Repository(Head)
// now we can use anywhere through autowire, this implementation done runtime dynamically through proxy class

}
