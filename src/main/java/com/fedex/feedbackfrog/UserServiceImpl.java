package com.fedex.feedbackfrog;

import com.fedex.feedbackfrog.model.dto.UserDTO;
import com.fedex.feedbackfrog.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private ModelMapper mapper;

  @Autowired
  public UserServiceImpl(ModelMapper mapper, UserRepository userRepository) {
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  public UserDTO findUserByName(String name) {
    return convertUserToUserDTO(userRepository.findUserByName(name));
  }

  private UserDTO convertUserToUserDTO (User user) {
    return mapper.map(user, UserDTO.class);
  }

  public List<UserDTO> findAllUsers() {
    List<UserDTO> dtoList = new ArrayList<>();
    List<User> users = userRepository.findAll();
    for (User user : users) {
      UserDTO userDTO = convertUserToUserDTO(user);
      dtoList.add(userDTO);
    }
    return dtoList;
  }

  public UserDTO findUserById(long id) {
    return convertUserToUserDTO(userRepository.findById(id));
  }

  public void saveUser(UserDTO userDTO) {
    if (userRepository.findUserByName(userDTO.getName()) == null)
      userRepository.save(mapper.map(userDTO, User.class));
  }

  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }

  public void editUser(long id, UserDTO userDTO) {
    User user = userRepository.findById(id);
    mapper.map(userDTO, user);
    userRepository.save(user);
  }

  private List<UserDTO> convertEntityListToDtoList(List<User> userList) {
    List<UserDTO> dtoList = new ArrayList<>();
    for (User user : userList) {
    UserDTO userDTO = mapper.map(user, UserDTO.class);
    dtoList.add(userDTO);
    }
    return dtoList;
  }
}
