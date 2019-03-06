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
      dtoList.add(convertUserToUserDTO(user));
    }
    return dtoList;
  }

  public UserDTO findUserById(long id) {
    return convertUserToUserDTO(userRepository.findById(id));
  }

  public void saveUser(UserDTO userDTO) {
    userRepository.save(mapper.map(userDTO, User.class));
  }

  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }
}
