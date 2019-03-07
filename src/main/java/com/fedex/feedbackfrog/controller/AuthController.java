package com.fedex.feedbackfrog.controller;


import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class AuthController {

  @RequestMapping(value="/detail")
  public Principal user(Principal principal) {
    LinkedHashMap asd;
    asd = (LinkedHashMap) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
    System.out.println(asd);
    System.out.println(principal.toString());
    return principal;
  }



}
