package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.model.entity.Review;
import com.fedex.feedbackfrog.repository.ReviewRepository;
import com.fedex.feedbackfrog.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

  private ReviewRepository repository;
  private ModelMapper mapper;
  private UserRepository userRepository;

  @Autowired
  public ReviewServiceImpl(ReviewRepository repository, ModelMapper mapper, UserRepository userRepository) {
    this.repository = repository;
    this.mapper = mapper;
    this.userRepository = userRepository;
  }

  @Override
  public void saveReview(ReviewDTO reviewDTO) {
    if (reviewDTO != null){
      Review review = mapper.map(reviewDTO, Review.class);
      review.setReviewer(userRepository.findUserByName(reviewDTO.getReviewer().getName()));
      repository.save(review);
    }
  }

  @Override
  public List<ReviewDTO> getAllDtos() {
    List<ReviewDTO> reviewDTOList = new ArrayList<>();
    List<Review> reviewList = new ArrayList<>();
    repository.findAll().forEach(reviewList::add);

    for (Review review : reviewList) {
     reviewDTOList.add(mapper.map(review, ReviewDTO.class));
    }

    return reviewDTOList;
  }

  @Override
  public ReviewDTO getById(long id) {
    return mapper.map(repository.findById(id), ReviewDTO.class);
  }

  @Override
  public void deleteById(long id) {
    repository.deleteById(id);
  }

  @Override
  public List<ReviewDTO> getReviewByTextContaining(String text) {
    List<ReviewDTO> reviewDTOList = new ArrayList<>();
    List<Review> reviewList = repository.getByTextContaining(text);
    for (Review review : reviewList) {
      reviewDTOList.add(mapper.map(review, ReviewDTO.class));
    }
    return reviewDTOList;
  }
}
