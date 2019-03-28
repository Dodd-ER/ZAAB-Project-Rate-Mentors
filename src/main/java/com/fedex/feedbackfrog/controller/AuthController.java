package com.fedex.feedbackfrog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

  @GetMapping("/login")
  public String mainPage() {
    return "main";
  }
}
