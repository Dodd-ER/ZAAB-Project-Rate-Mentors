package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.UserDTO;

import java.util.List;

public interface UserService {

  UserDTO findUserByName(String name);
  List<UserDTO> findAllUsers();
  UserDTO findUserById(long id);
  void saveUser(UserDTO userDTO);
  void deleteUser(long id);
  void editUser(long id, UserDTO userDTO);
  boolean checkExistenceById(long id);
  boolean checkExistenceByName(String name);
}
