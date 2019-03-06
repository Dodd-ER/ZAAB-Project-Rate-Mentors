package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

  private ReviewRepository repository;

  public ReviewServiceImpl(ReviewRepository repository) {
    this.repository = repository;
  }

  @Override
  public void saveReview(ReviewDTO reviewDTO) {

  }

  @Override
  public List<ReviewDTO> getAllDtos() {
    return null;
  }

  @Override
  public ReviewDTO getById(long id) {
    return null;
  }

  @Override
  public void deleteById(long id) {

  }
}
