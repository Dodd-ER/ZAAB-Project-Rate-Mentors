package com.fedex.feedbackfrog;



import com.sun.deploy.net.HttpResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  public static String json = " ";

  @RequestMapping(value="/user")
  public Principal user(Principal principal) {
    LinkedHashMap asd = new LinkedHashMap();
    asd = (LinkedHashMap) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
    System.out.println(asd);
    System.out.println(principal.toString());

    return principal;
  }



}
