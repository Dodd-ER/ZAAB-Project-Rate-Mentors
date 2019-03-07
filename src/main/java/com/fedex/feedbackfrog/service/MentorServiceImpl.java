package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.MentorDTO;
import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.repository.MentorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentorServiceImpl implements MentorService{

  private MentorRepository mentorRepository;
  private ModelMapper modelMapper;

  @Autowired
  public MentorServiceImpl(MentorRepository mentorRepository, ModelMapper modelMapper) {
    this.mentorRepository = mentorRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<MentorDTO> findAllMentor() {
    List<MentorDTO> mentorDTOs = new ArrayList<>();
    List<Mentor> mentors = this.mentorRepository.findAll();
    for (Mentor mentor : mentors) {
      MentorDTO mentorDTO = modelMapper.map(mentor, MentorDTO.class);
      mentorDTOs.add(mentorDTO);
    }
    return mentorDTOs;
  }

  @Override
  public MentorDTO findMentorByName(String name) {
    return modelMapper.map(this.mentorRepository.findByName(name), MentorDTO.class);
  }

  @Override
  public MentorDTO findMentorById(long id) {
    return modelMapper.map(this.mentorRepository.findById(id), MentorDTO.class);
  }

  @Override
  public void saveNewMentor(MentorDTO mentorDTO) {
    this.mentorRepository.save(modelMapper.map(mentorDTO, Mentor.class));
  }

  @Override
  public void deleteMentorById(long id) {
    this.mentorRepository.deleteById(id);
  }

  @Override
  public boolean isMentorExistsByName(String name) {
    return this.mentorRepository.existsByName(name);
  }

  @Override
  public boolean isMentorExistsById(long id) {
    return this.mentorRepository.existsById(id);
  }
}
