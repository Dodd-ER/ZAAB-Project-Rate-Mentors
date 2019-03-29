package com.fedex.feedbackfrog.view;

import com.fedex.feedbackfrog.model.dto.MentorDTO;
import com.fedex.feedbackfrog.service.MentorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewMentorController {

  private MentorServiceImpl service;

  @Autowired
  public ViewMentorController (MentorServiceImpl mentorService) {
    this.service = mentorService;
  }

  @GetMapping ("/")
  public String main(Model model) {
    List<MentorDTO> mentors = service.getAll();
    model.addAttribute("mentors", mentors);
    return "main";
  }
}
