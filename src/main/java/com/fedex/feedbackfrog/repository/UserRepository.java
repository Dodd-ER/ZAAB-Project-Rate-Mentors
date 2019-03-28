package com.fedex.feedbackfrog.repository;

import com.fedex.feedbackfrog.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Long> {

  ArrayList<User> findAll();

  User findUserByName(String name);

  User findById(long id);

  void deleteById(long id);

  boolean existsByName(String name);
}
