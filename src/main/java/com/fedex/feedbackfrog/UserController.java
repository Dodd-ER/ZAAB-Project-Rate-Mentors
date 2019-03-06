package com.fedex.feedbackfrog;

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

  //get
  @GetMapping("/user")
  public ResponseEntity getUser(@RequestParam(required = false) String name) {
    if (!name.equals(null)|| !name.equals("")) {
      return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
    } return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
  }

  //get by id
  @GetMapping ("/user/{id}")
  public ResponseEntity getUserById(@PathVariable long id) {
    return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
  }

  //post -- to be modified based on google auth
  @PostMapping("/user")
  public ResponseEntity addNewUser(UserDTO userDTO) {
    userService.saveUser(userDTO);
    return new ResponseEntity<>("User created", HttpStatus.CREATED);
  }

  //put -- todo

  //delete
  @DeleteMapping("/user/{id}")
  public ResponseEntity deleteUser(@PathVariable long id) {
    userService.deleteUser(id);
    return new ResponseEntity(HttpStatus.OK);
  }
}
