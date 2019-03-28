package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.exception.GeneralException;
import com.fedex.feedbackfrog.service.UserService;
import com.fedex.feedbackfrog.service.UserServiceImpl;
import com.fedex.feedbackfrog.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user")
  public ResponseEntity getUser(@RequestParam(required = false) String name) throws Exception {
    if (name == null) {
      return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    } else if (userService.checkExistenceByName(name)) {
      return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
    } else {
      throw new GeneralException("User not found", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/{id}")
  public ResponseEntity getUserById(@PathVariable long id) throws Exception {
    if (userService.checkExistenceById(id)) {
      return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    } else {
      throw new GeneralException("Cannot find user with given ID", HttpStatus.NOT_FOUND);
    }
  }

  //post -- to be modified based on google auth
  @PostMapping("/user")
  public ResponseEntity addNewUser(@RequestBody UserDTO userDTO) throws Exception{
    if (!userService.checkExistenceByName(userDTO.getName())) {
      userService.saveUser(userDTO);
      return new ResponseEntity<>("User created", HttpStatus.CREATED);
    } else {
      throw new GeneralException("Name already exists in the database", HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping ("/user/{id}")
  public ResponseEntity editUser (@PathVariable long id, @RequestBody UserDTO userDTO) throws Exception {
    if (userService.checkExistenceById(id)) {
      userService.editUser(id, userDTO);
      return new ResponseEntity<>("User edited", HttpStatus.OK);
    } else {
      throw new GeneralException("Cannot find user with given ID", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity deleteUser(@PathVariable long id) throws Exception {
    if (userService.checkExistenceById(id)) {
      userService.deleteUser(id);
      return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    } else {
      throw new GeneralException("Cannot find user with given ID", HttpStatus.NOT_FOUND);
    }
  }
}
