package com.fedex.feedbackfrog.view;

import com.fedex.feedbackfrog.service.MentorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewMentorController {

  private MentorServiceImpl service;

  @Autowired
  public ViewMentorController (MentorServiceImpl mentorService) {
    this.service = mentorService;
  }

  @GetMapping ("/")
  public String main(Model model ) {
    model.addAttribute("mentors", service.getAll());
    return "main";
  }
}
