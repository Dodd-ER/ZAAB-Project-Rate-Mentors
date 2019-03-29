package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
import com.fedex.feedbackfrog.service.MentorServiceImpl;
import com.fedex.feedbackfrog.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

  private MentorServiceImpl mentorService;
  private ReviewServiceImpl reviewService;

  @Autowired
  public ReviewController (MentorServiceImpl mentorService, ReviewServiceImpl reviewService) {
    this.mentorService = mentorService;
    this.reviewService = reviewService;
  }
  @GetMapping("/reviews/{mentorid}")
  public String getReviewByMentor(Model model, @PathVariable(name = "mentorid") long mentorId){
    model.addAttribute("reviews", reviewService.getByMentor(mentorId));
    return "review";
  }

  @GetMapping("/view/review/add")
  public String addReviewPage(Model model) {
    model.addAttribute("mentors", mentorService.getAll());
    model.addAttribute("review", new ReviewDTO_Post());
    return "addreview";
  }

  @PostMapping("/view/review/add")
  public String addNewReview(@ModelAttribute ReviewDTO_Post reviewDto ) {
    reviewService.save(reviewDto);
    return "redirect:/";
  }
}
