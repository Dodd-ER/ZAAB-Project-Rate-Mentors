package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.MentorDTO;

import java.util.List;

public interface MentorService {
  List<MentorDTO> findAllMentor();
  MentorDTO findMentorByName(String name);
  MentorDTO findMentorById(long id);
  void saveNewMentor(MentorDTO mentorDTO);
  void editMentor(long id, MentorDTO mentorDTO);
  void deleteMentorById(long id);
  boolean isMentorExistsByName(String name);
  boolean isMentorExistsById(long id);
}
