package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
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

  @GetMapping("/")
  public ResponseEntity getByKeyword(@RequestParam(value = "text",required = false) String text){
    return new ResponseEntity<>(service.getReviewByTextContaining(text), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity saveReview(@RequestBody ReviewDTO_Post reviewDTO){
    service.saveReview(reviewDTO);
    slackMessageService.sendMessage(reviewDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateReview(@RequestBody ReviewDTO reviewDTO, @PathVariable long id) {
    service.updateReview(reviewDTO, id);
    return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteByID(@PathVariable long id){
    service.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
