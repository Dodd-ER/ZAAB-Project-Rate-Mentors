package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.service.ReviewService;
import com.fedex.feedbackfrog.service.SlackMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

  private ReviewService service;
  private SlackMessageService slackMessageService;

  @Autowired
  public ReviewController(ReviewService service, SlackMessageService slackMessageService) {
    this.service = service;
    this.slackMessageService = slackMessageService;
  }

  @GetMapping("/{id}")
  public ResponseEntity getReviewsById(@PathVariable long id){
    return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity getAllReviews(){
    return new ResponseEntity<>(service.getAllDtos(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteByID(@PathVariable long id){
    service.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity saveReview(@RequestBody ReviewDTO reviewDTO){
    service.saveReview(reviewDTO);
    slackMessageService.sendMessage();
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
