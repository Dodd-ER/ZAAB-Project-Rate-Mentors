package com.fedex.feedbackfrog;

import com.fedex.feedbackfrog.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findUserByName(String name);

  User findById(long id);
}
