package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.model.entity.User;
import com.fedex.feedbackfrog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class AuthController {

  private UserService userService;

  @Autowired
  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value="/detail")
  public Principal user(Principal principal) {
    LinkedHashMap asd;
    asd = (LinkedHashMap) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
    System.out.println(asd);
//    userService.setUserField(asd.getOrDefault("id", null).toString(),
//        asd.getOrDefault("name", null).toString(),
//        asd.getOrDefault("email",null).toString());
    System.out.println(principal.toString());
 //   userService.saveUser(((OAuth2Authentication) principal).getUserAuthentication().getDetails().);
    return principal;
  }
}
