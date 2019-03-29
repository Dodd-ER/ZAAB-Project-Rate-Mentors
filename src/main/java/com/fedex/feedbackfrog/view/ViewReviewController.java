package com.fedex.feedbackfrog.view;

import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.service.MentorServiceImpl;
import com.fedex.feedbackfrog.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewReviewController {

  private MentorServiceImpl mentorService;
  private ReviewServiceImpl reviewService;

  @Autowired
  public ViewReviewController (MentorServiceImpl mentorService, ReviewServiceImpl reviewService) {
    this.mentorService = mentorService;
    this.reviewService = reviewService;
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

    return "rerirect:/";
  }
}
