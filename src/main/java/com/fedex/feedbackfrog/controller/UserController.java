package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.exception.GeneralException;
import com.fedex.feedbackfrog.model.dto.UserDTO;
import com.fedex.feedbackfrog.service.UserServiceImpl;
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
      return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    } else if (userService.existsByName(name)) {
      return new ResponseEntity<>(userService.getByName(name), HttpStatus.OK);
    } else {
      throw new GeneralException("User list is empty", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/{id}")
  public ResponseEntity getUserById(@PathVariable long id) throws Exception {
    if (userService.existsById(id)) {
      return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    } else throw new GeneralException("Cannot find user with given ID", HttpStatus.BAD_REQUEST);
  }

  //post -- to be modified based on google auth
  @PostMapping("/user")
  public ResponseEntity addNewUser(@RequestBody UserDTO userDTO) {
    userService.save(userDTO);
    return new ResponseEntity<>("User created", HttpStatus.CREATED);
  }

  @PutMapping ("/user/{id}")
  public ResponseEntity editUser (@PathVariable long id, @RequestBody UserDTO userDTO) throws Exception {
    if (userService.existsById(id)) {
      userService.updateById(id, userDTO);
      return new ResponseEntity<>("User edited", HttpStatus.OK);
    } else throw new GeneralException("Cannot find user with given ID", HttpStatus.NOT_FOUND);
  }


  @DeleteMapping("/user/{id}")
  public ResponseEntity deleteUser(@PathVariable long id) throws Exception {
    if (userService.existsById(id)) {
      userService.deleteById(id);
      return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    } else throw new GeneralException("Cannot find user with given ID", HttpStatus.NOT_FOUND);
  }
}
