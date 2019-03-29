package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
import com.fedex.feedbackfrog.model.entity.Review;
import com.fedex.feedbackfrog.repository.MentorRepository;
import com.fedex.feedbackfrog.repository.ReviewRepository;
import com.fedex.feedbackfrog.repository.UserRepository;
import com.fedex.feedbackfrog.service.serviceInterface.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements CreateService<ReviewDTO_Post>,
                                          ReadService<ReviewDTO>,
                                          UpdateService<ReviewDTO>,
                                          DeleteService<ReviewDTO> {

  private ReviewRepository reviewRepository;
  private UserRepository userRepository;
  private MentorRepository mentorRepository;
  private ModelMapper mapper;

  @Autowired
  public ReviewServiceImpl(ReviewRepository repository, ModelMapper mapper, UserRepository userRepository, MentorRepository mentorRepository) {
    this.reviewRepository = repository;
    this.mapper = mapper;
    this.userRepository = userRepository;
    this.mentorRepository = mentorRepository;
  }

  @Override
  public void save(ReviewDTO_Post dto) {
    if (dto != null){
      Review review = mapper.map(dto, Review.class);
      review.setReviewer(userRepository.findUserByName(dto.getReviewer().getName()));
      review.setMentor(mentorRepository.findByName(dto.getMentor().getName()));
      review.getMentor().setPoints(dto.rating.toString().equals("PLUS") ? 1 : -1);
      reviewRepository.save(review);
    }
  }

  @Override
  public List<ReviewDTO> getAll() {
    List<ReviewDTO> reviewDTOList = new ArrayList<>();
    List<Review> reviewList = reviewRepository.findAll();

    for (Review review : reviewList) {
      reviewDTOList.add(mapper.map(review, ReviewDTO.class));
    }

    return reviewDTOList;
  }

  @Override
  public ReviewDTO getById(long id) {
    return mapper.map(reviewRepository.findById(id), ReviewDTO.class);
  }

  @Override
  public boolean existsById(long id) {
    return reviewRepository.existsById(id);
  }

  @Override
  public void updateById(long id, ReviewDTO dto) {
    Review review = reviewRepository.findById(id);
    mapper.map(dto, review);
    reviewRepository.save(review);
  }

  @Override
  public void deleteById(long id) {
    reviewRepository.findById(id).setMentor(null);
    reviewRepository.findById(id).setReviewer(null);
    reviewRepository.deleteById(id);
  }

  public List<ReviewDTO> getByTextContaining(String text) {
    List<ReviewDTO> reviewDTOList = new ArrayList<>();
    List<Review> reviewList = reviewRepository.getByTextContaining(text);

    for (Review review : reviewList) {
      reviewDTOList.add(mapper.map(review, ReviewDTO.class));
    }

    return reviewDTOList;
  }

  public List<ReviewDTO> getByMentor(long id){
    List<ReviewDTO> reviewDTOList = new ArrayList<>();
    List<Review> reviewList = reviewRepository
        .findAll()
        .stream()
        .filter(review -> review.getMentor().getId() == id)
        .collect(Collectors.toList());

    for (Review review : reviewList) {
      reviewDTOList.add(mapper.map(review, ReviewDTO.class));
    }

    return reviewDTOList;
  }

  public boolean existsByContainingText(String text) {
    return reviewRepository.existsByTextContaining(text);
  }
}
