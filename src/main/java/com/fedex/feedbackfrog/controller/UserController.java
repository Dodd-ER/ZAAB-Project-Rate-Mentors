package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.exception.GeneralException;
import com.fedex.feedbackfrog.service.UserServiceImpl;
import com.fedex.feedbackfrog.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  private UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping("/user")
  public ResponseEntity getUser(@RequestParam(required = false) String name) throws Exception {
    if (name == null) {
      return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    } else if (userService.findUserByName(name) != null) {
      return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
    } else {
      throw new GeneralException("User list is empty", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/{id}")
  public ResponseEntity getUserById(@PathVariable long id) throws Exception {
    if (userService.findUserById(id) != null) {
      return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    } else throw new GeneralException("Cannot find user with given ID", HttpStatus.BAD_REQUEST);
  }

  //post -- to be modified based on google auth
  @PostMapping("/user")
  public ResponseEntity addNewUser(@RequestBody UserDTO userDTO) {
    userService.saveUser(userDTO);
    return new ResponseEntity<>("User created", HttpStatus.CREATED);
  }

  @PutMapping ("/user/{id}")
  public ResponseEntity editUser (@PathVariable long id, @RequestBody UserDTO userDTO) {
    userService.editUser(id, userDTO);
    return new ResponseEntity<>("User edited", HttpStatus.OK);
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity deleteUser(@PathVariable long id) throws Exception {
    if (userService.findUserById(id) != null) {
      userService.deleteUser(id);
      return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    } else throw new GeneralException("Cannot find user with given ID", HttpStatus.NOT_FOUND);
  }
}
