package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.service.MentorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MentorController {

  private MentorServiceImpl mentorService;

  @Autowired
  public MentorController(MentorServiceImpl mentorService) {
    this.mentorService = mentorService;
  }

  @GetMapping("/mentor/{id}")
  public ResponseEntity getMentorById(@PathVariable(value = "id") long id) {
    if (this.mentorService.findMentorById(id) != null) {
      return new ResponseEntity<>(this.mentorService.findMentorById(id), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/mentor")
  public ResponseEntity getMentor(@RequestParam(required = false) String name) {
    if (name == null || name.contentEquals("")) {
      return new ResponseEntity<>(this.mentorService.findAllMentor(), HttpStatus.OK);
    } else if (this.mentorService.findMentorByName(name) != null) {
      return new ResponseEntity<>(this.mentorService.findMentorByName(name), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
