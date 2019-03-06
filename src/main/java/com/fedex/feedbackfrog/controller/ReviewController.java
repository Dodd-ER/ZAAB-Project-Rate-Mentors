package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

  private ReviewService service;

  @Autowired
  public ReviewController(ReviewService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public ResponseEntity getReviewsById(@PathVariable long id){
    return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity deleteByID(@PathVariable long id){
    service.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity saveReview(@RequestBody ReviewDTO reviewDTO){
    service.saveReview(reviewDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
