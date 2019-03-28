package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.UserDTO;
import com.fedex.feedbackfrog.model.entity.Review;
import com.fedex.feedbackfrog.model.entity.User;
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

  @Autowired
  public UserServiceImpl(ModelMapper mapper, UserRepository userRepository) {
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  public void save(UserDTO dto) {
    if (!userRepository.existsByName(dto.getName())){
      userRepository.save(mapper.map(dto, User.class));
    }
  }

  @Override
  public List<UserDTO> getAll() {
    List<UserDTO> dtoList = new ArrayList<>();
    List<User> users = userRepository.findAll();

    for (User user : users) {
      dtoList.add(mapper.map(user, UserDTO.class));
    }

    return dtoList;
  }

  @Override
  public UserDTO getById(long id) {
    return mapper.map(userRepository.findById(id), UserDTO.class);
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
  public void deleteById(long id) {
    userRepository.findById(id).getSentReviews().forEach(review -> review.setReviewer(null));
    userRepository.deleteById(id);
  }

  public UserDTO getByName(String name) {
    return mapper.map(userRepository.findUserByName(name), UserDTO.class);
  }

  public boolean existsByName(String name) {
    return userRepository.existsByName(name);
  }
}
