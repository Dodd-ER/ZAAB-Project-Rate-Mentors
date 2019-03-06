package com.fedex.feedbackfrog;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {

  @RequestMapping(value="/user")
  public Principal user(Principal principal) {
    String asd = principal.toString();
    System.out.println(asd);
    return principal;
  }
}
