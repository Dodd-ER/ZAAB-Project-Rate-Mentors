package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.service.MentorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MentorController {

  private MentorServiceImpl mentorService;

  @Autowired
  public MentorController(MentorServiceImpl mentorService) {
    this.mentorService = mentorService;
  }

  @GetMapping("/mentor")
  public List<MentorDto> getMentor() {
    return this.mentorService.findAllMentor();
  }
}
