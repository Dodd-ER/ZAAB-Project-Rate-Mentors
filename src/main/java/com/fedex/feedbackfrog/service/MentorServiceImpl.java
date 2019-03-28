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
  private ModelMapper mapper;

  @Autowired
  public MentorServiceImpl(MentorRepository mentorRepository, ModelMapper modelMapper) {
    this.mentorRepository = mentorRepository;
    this.mapper = modelMapper;
  }

  @Override
  public void save(MentorDTO dto) {
    if (!mentorRepository.existsByName(dto.getName())){
      mentorRepository.save(mapper.map(dto, Mentor.class));
    }
  }

  @Override
  public List<MentorDTO> getAll() {
    List<MentorDTO> mentorDTOs = new ArrayList<>();
    List<Mentor> mentors = mentorRepository.findAll();

    for (Mentor mentor : mentors) {
      mentorDTOs.add(mapper.map(mentor, MentorDTO.class));
    }

    return mentorDTOs;
  }

  @Override
  public MentorDTO getById(long id) {
    return mapper.map(mentorRepository.findById(id), MentorDTO.class);
  }

  @Override
  public void updateById(long id, MentorDTO dto) {
    Mentor mentor = mentorRepository.findById(id);
    mapper.map(dto, mentor);
    mentorRepository.save(mentor);
  }

  @Override
  public void deleteById(long id) {
    mentorRepository.deleteById(id);
  }

  @Override
  public boolean existsById(long id) {
    return mentorRepository.existsById(id);
  }

  public MentorDTO getByName(String name) {
    return mapper.map(mentorRepository.findByName(name), MentorDTO.class);
  }

  public boolean existsByName(String name) {
    return mentorRepository.existsByName(name);
  }

}
