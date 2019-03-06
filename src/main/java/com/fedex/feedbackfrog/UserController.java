package com.fedex.feedbackfrog;

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
    if (!name.equals(null)) {
      return new ResponseEntity<>(userService.findUserByName(name), HttpStatus.OK);
    } return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
  }

  //get by id
  @GetMapping ("/user/id")
  public ResponseEntity getUserById(@PathVariable long id) {

    return new ResponseEntity(userService.findUserById(id), HttpStatus.OK);
  }

  //post

  //put
  @PutMapping
  public ResponseEntity editUser() {


    return new ResponseEntity(HttpStatus.OK);
  }

}
