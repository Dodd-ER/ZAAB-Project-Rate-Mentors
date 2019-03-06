package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService{

  private MentorRepository mentorRepository;

  @Autowired
  public MentorServiceImpl(MentorRepository mentorRepository) {
    this.mentorRepository = mentorRepository;
  }

  @Override
  public List<Mentor> findAllMentor() {
    return this.mentorRepository.findAll();
  }

  @Override
  public Mentor findMentorByName(String name) {
    return this.mentorRepository.findByName(name);
  }

  @Override
  public Mentor findMentorById(long id) {
    return this.mentorRepository.findById(id).orElse(null);
  }
}
