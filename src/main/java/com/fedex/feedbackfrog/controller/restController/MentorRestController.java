package com.fedex.feedbackfrog.controller.restController;

import com.fedex.feedbackfrog.exception.GeneralException;
import com.fedex.feedbackfrog.model.dto.MentorDTO;
import com.fedex.feedbackfrog.service.MentorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MentorRestController {

  private MentorServiceImpl mentorService;

  @Autowired
  public MentorRestController(MentorServiceImpl mentorService) {
    this.mentorService = mentorService;
  }

  @GetMapping("/mentor/{id}")
  public ResponseEntity getMentorById(@PathVariable(value = "id") long id) throws Exception {
    if (mentorService.existsById(id)) {
      return new ResponseEntity<>(mentorService.getById(id), HttpStatus.OK);
    } else {
      throw new GeneralException("Mentor not found", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/mentor")
  public ResponseEntity getMentor(@RequestParam(required = false) String name) throws Exception {
    if (name == null || name.isEmpty()) {
      return new ResponseEntity<>(mentorService.getAll(), HttpStatus.OK);
    } else if (mentorService.existsByName(name)) {
      return new ResponseEntity<>(mentorService.getByName(name), HttpStatus.OK);
    } else {
      throw new GeneralException("Mentor not found", HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/mentor")
  public ResponseEntity createNewMentor(@RequestBody MentorDTO mentorDTO) throws Exception {
    if (!mentorService.existsByName(mentorDTO.getName())) {
      mentorService.save(mentorDTO);
      return new ResponseEntity<>("Mentor created", HttpStatus.CREATED);
    } else {
      throw new GeneralException("Name already exists in the database", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/mentor/{id}")
  public ResponseEntity deleteMentor(@PathVariable (value = "id") long id) throws Exception {
    if (mentorService.existsById(id)) {
      mentorService.deleteById(id);
      return new ResponseEntity<>("Mentor deleted", HttpStatus.OK);
    } else {
      throw new GeneralException("Mentor not found", HttpStatus.BAD_REQUEST);
    }
  }
}
