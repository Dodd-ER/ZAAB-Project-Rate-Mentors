package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.exception.GeneralException;
import com.fedex.feedbackfrog.model.dto.MentorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MentorController {

  private MentorService mentorService;

  @Autowired
  public MentorController(MentorService mentorService) {
    this.mentorService = mentorService;
  }

  @GetMapping("/mentor/{id}")
  public ResponseEntity getMentorById(@PathVariable(value = "id") long id) throws Exception {
    if (this.mentorService.isMentorExistsById(id)) {
      return new ResponseEntity<>(this.mentorService.findMentorById(id), HttpStatus.OK);
    } else {
      throw new GeneralException("Mentor not found", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/mentor")
  public ResponseEntity getMentor(@RequestParam(required = false) String name) throws Exception {
    if (name == null || name.contentEquals("")) {
      return new ResponseEntity<>(this.mentorService.findAllMentor(), HttpStatus.OK);
    } else if (this.mentorService.findMentorByName(name) != null) {
      return new ResponseEntity<>(this.mentorService.findMentorByName(name), HttpStatus.OK);
    } else {
      throw new GeneralException("Mentor not found", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/mentor")
  public ResponseEntity createNewMentor(@RequestBody MentorDTO mentorDTO) throws Exception {
    if (!this.mentorService.isMentorExistsByName(mentorDTO.getName())) {
      this.mentorService.saveNewMentor(mentorDTO);
      return new ResponseEntity<>("Mentor created", HttpStatus.OK);
    } else {
      throw new GeneralException("Name already exists in the database", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/mentor/{id}")
  public ResponseEntity deleteMentor(@PathVariable (value = "id") long id) throws Exception {
    if (this.mentorService.isMentorExistsById(id)) {
      this.mentorService.deleteMentorById(id);
      return new ResponseEntity<>("Mentor deleted", HttpStatus.OK);
    } else {
      throw new GeneralException("Mentor not found", HttpStatus.BAD_REQUEST);
    }
  }
}
