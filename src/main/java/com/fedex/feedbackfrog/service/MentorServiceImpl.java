package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.MentorDTO;
import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.repository.MentorRepository;
import com.fedex.feedbackfrog.service.serviceInterface.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentorServiceImpl implements CrudService<MentorDTO> {

  private MentorRepository mentorRepository;
  private ModelMapper modelMapper;

  @Autowired
  public MentorServiceImpl(MentorRepository mentorRepository, ModelMapper modelMapper) {
    this.mentorRepository = mentorRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public void save(MentorDTO dto) {
    this.mentorRepository.save(modelMapper.map(dto, Mentor.class));
  }

  @Override
  public void deleteById(long id) {
    this.mentorRepository.deleteById(id);
  }

  @Override
  public List<MentorDTO> getAll() {
    List<MentorDTO> mentorDTOs = new ArrayList<>();
    List<Mentor> mentors = this.mentorRepository.findAll();
    for (Mentor mentor : mentors) {
      MentorDTO mentorDTO = modelMapper.map(mentor, MentorDTO.class);
      mentorDTOs.add(mentorDTO);
    }
    return mentorDTOs;
  }

  @Override
  public List<MentorDTO> getByTextContaining(String text) {
    return null;
  }

  @Override
  public MentorDTO getByName(String name) {
    return modelMapper.map(this.mentorRepository.findByName(name), MentorDTO.class);
  }

  @Override
  public MentorDTO getById(long id) {
    return modelMapper.map(this.mentorRepository.findById(id), MentorDTO.class);
  }

  @Override
  public boolean existsByName(String name) {
    return this.mentorRepository.existsByName(name);
  }

  @Override
  public boolean existsById(long id) {
    return this.mentorRepository.existsById(id);
  }

  @Override
  public void editById(long id, MentorDTO dto) {

  }

  @Override
  public boolean existsByText(String text) {
    return false;
  }
}
