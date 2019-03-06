package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.model.dto.MentorDTO;
import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.repository.MentorRepository;
import com.fedex.feedbackfrog.service.MentorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MentorServiceTest {

  @MockBean
  private MentorServiceImpl mMockService;
  private ModelMapper mMockModelMapper;

  @Mock
  private MentorRepository mMockRepository;

  private Mentor blanka = new Mentor("Blanka", 100);
  private Mentor gabor = new Mentor("Gábor", 100);

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    mMockService = new MentorServiceImpl(mMockRepository, mMockModelMapper);
  }

  @Test
  public void findAllMentor_Test() {
    Mockito.when(mMockRepository.findAll()).thenReturn(Arrays.asList(blanka, gabor));
    assertEquals(mMockService.findAllMentor(), Arrays.asList(blanka, gabor));
  }

  @Test
  public void findMentorByName_Test() {
    Mockito.when(mMockRepository.findByName("Blanka")).thenReturn(blanka);
    assertEquals(mMockService.findMentorByName("Blanka"), mMockModelMapper.map(blanka, MentorDTO.class));
  }

  @Test
  public void findMentorById_Test() {
    Mockito.when(mMockRepository.findById(1)).thenReturn(blanka);
    assertEquals(mMockService.findMentorById(1), blanka);
  }

//  @Test
//  public void saveNewMentor_Test(MentorDTO mentorDTO) {
//
//  }
//
//  @Test
//  public void deleteMentorById(long id){
//
//  }
}
