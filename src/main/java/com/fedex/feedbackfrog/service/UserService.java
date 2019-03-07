package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.UserDTO;
import com.fedex.feedbackfrog.model.entity.User;

import java.util.List;

public interface UserService {

  UserDTO findUserByName(String name);

  List<UserDTO> findAllUsers();

  UserDTO findUserById(long id);

  void saveUser(UserDTO userDTO);

  void deleteUser(long id);

  void editUser(long id, UserDTO userDTO);

  void saveUser(User user);

  User setUserField(String id, String name, String emailAddress);
}
