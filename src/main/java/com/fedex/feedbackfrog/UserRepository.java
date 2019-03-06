package com.fedex.feedbackfrog;

import com.fedex.feedbackfrog.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findAll();

  User findUserByName(String name);

  User findById(long id);

  void deleteById(long id);
}
