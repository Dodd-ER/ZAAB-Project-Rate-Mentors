package com.fedex.feedbackfrog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
public class FeedbackfrogApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeedbackfrogApplication.class, args);
  }
}
