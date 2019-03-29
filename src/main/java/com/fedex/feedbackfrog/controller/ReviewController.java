package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewController {

  private ReviewServiceImpl reviewService;

  @Autowired
  public ReviewController(ReviewServiceImpl reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/reviews/{mentorid}")
  public String getReviewByMentor(Model model, @PathVariable(name = "mentorid") long mentorId){
    model.addAttribute("reviews", reviewService.getByMentor(mentorId));
    return "review";
  }
}
