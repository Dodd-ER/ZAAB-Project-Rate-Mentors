package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.UserDTO;
import com.fedex.feedbackfrog.model.entity.User;
import com.fedex.feedbackfrog.repository.ReviewRepository;
import com.fedex.feedbackfrog.repository.UserRepository;
import com.fedex.feedbackfrog.service.serviceInterface.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements CrudService<UserDTO> {

  private UserRepository userRepository;
  private ModelMapper mapper;
  private ReviewRepository reviewRepository;

  @Autowired
  public UserServiceImpl(ModelMapper mapper, UserRepository userRepository, ReviewRepository reviewRepository) {
    this.userRepository = userRepository;
    this.mapper = mapper;
    this.reviewRepository = reviewRepository;
  }

  private UserDTO convertUserToUserDTO (User user) {
    return mapper.map(user, UserDTO.class);
  }

  private List<UserDTO> convertEntityListToDtoList(List<User> userList) {
    List<UserDTO> dtoList = new ArrayList<>();
    for (User user : userList) {
      UserDTO userDTO = mapper.map(user, UserDTO.class);
      dtoList.add(userDTO);
    }
    return dtoList;
  }

  @Override
  public void save(UserDTO dto) {
    if (userRepository.findUserByName(dto.getName()) == null)
      userRepository.save(mapper.map(dto, User.class));
  }

  @Override
  public void deleteById(long id) {
    User deletedUser = new User();
    deletedUser.setName("deleted user");
    userRepository.findById(id).getSentReviews().forEach(review -> review.setReviewer(deletedUser));
    userRepository.deleteById(id);
  }

  @Override
  public List<UserDTO> getAll() {
    List<UserDTO> dtoList = new ArrayList<>();
    List<User> users = userRepository.findAll();
    for (User user : users) {
      UserDTO userDTO = convertUserToUserDTO(user);
      dtoList.add(userDTO);
    }
    return dtoList;
  }

  @Override
  public UserDTO getByName(String name) {
    return convertUserToUserDTO(userRepository.findUserByName(name));
  }

  @Override
  public UserDTO getById(long id) {
    return convertUserToUserDTO(userRepository.findById(id));
  }

  @Override
  public boolean existsByName(String name) {
    return userRepository.existsByName(name);
  }

  @Override
  public boolean existsById(long id) {
    return userRepository.existsById(id);
  }

  @Override
  public void updateById(long id, UserDTO dto) {
    User user = userRepository.findById(id);
    mapper.map(dto, user);
    userRepository.save(user);
  }

  @Override
  public boolean existsByText(String text) {
    return false;
  }

  @Override
  public List<UserDTO> getByTextContaining(String text) {
    return null;
  }
}
