package com.fedex.feedbackfrog;


import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class AuthController {

  @RequestMapping(value="/user")
  public Principal user(Principal principal) {
    LinkedHashMap asd = new LinkedHashMap();
    asd = (LinkedHashMap) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
    System.out.println(asd);
    System.out.println(principal.toString());

    return principal;
  }



}
