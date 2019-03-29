package com.fedex.feedbackfrog.controller.restController;

import com.fedex.feedbackfrog.exception.GeneralException;
import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
import com.fedex.feedbackfrog.service.ReviewServiceImpl;
import com.fedex.feedbackfrog.service.SlackMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {

  private ReviewServiceImpl service;
  private SlackMessageService slackMessageService;

  @Autowired
  public ReviewRestController(ReviewServiceImpl service, SlackMessageService slackMessageService) {
    this.service = service;
    this.slackMessageService = slackMessageService;
  }

  @GetMapping("/{id}")
  public ResponseEntity getReviewsById(@PathVariable long id) throws GeneralException {
    if (service.existsById(id)){
      return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    } else throw new GeneralException("Cannot find review with given ID", HttpStatus.BAD_REQUEST);
  }

  @GetMapping
  public ResponseEntity getReviews(@RequestParam(value = "text",required = false) String text) throws GeneralException {
    if (text == null || text.isEmpty()){
      return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    } else if (service.existsByContainingText(text)){
      return new ResponseEntity<>(service.getByTextContaining(text), HttpStatus.OK);
    } throw new GeneralException("Review(s) not found", HttpStatus.NOT_FOUND);
  }

  @PostMapping
  public ResponseEntity saveReview(@RequestBody ReviewDTO_Post reviewDTO) throws GeneralException{
    service.save(reviewDTO);
    slackMessageService.sendMessage(reviewDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateReview(@RequestBody ReviewDTO reviewDTO, @PathVariable long id) {
    service.updateById(id, reviewDTO);
    return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteByID(@PathVariable long id) throws GeneralException {
    if (service.existsById(id)){
      service.deleteById(id);
      return new ResponseEntity<>("Review successfully deleted", HttpStatus.OK);
    } else throw new GeneralException("No such review", HttpStatus.BAD_REQUEST);
  }
}
