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

  @GetMapping("/user")
  public ResponseEntity getUser(@RequestParam(required = false) String name) {
    if (name == null) {
      return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
    return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
  }

  @GetMapping ("/user/{id}")
  public ResponseEntity getUserById(@PathVariable long id) {
    return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
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
  public ResponseEntity deleteUser(@PathVariable long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
  }
}
