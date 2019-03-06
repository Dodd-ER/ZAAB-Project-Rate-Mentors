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

  @Autowired
  UserRepository userRepository;

  @Autowired
  ModelMapper mapper;

  public UserDTO findUserByName(String name) {
    return convertUserToUserDTO(userRepository.findUserByName(name));
  }

  private UserDTO convertUserToUserDTO (User user) {
    return mapper.map(user, UserDTO.class);
  }

  public List<UserDTO> findAllUsers() {
    return mapper.map(userRepository.findAll(), ArrayList.class);
  }

  public UserDTO findUserById(long id) {
    return convertUserToUserDTO(userRepository.findById(id));
  }

}
