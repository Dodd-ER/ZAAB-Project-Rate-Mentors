package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
  void saveReview(ReviewDTO reviewDTO);
  List<ReviewDTO> getAllDtos();
  ReviewDTO getById(long id);
  void deleteById(long id);
  List<ReviewDTO> getReviewByTextContaining(String text);
  void updateReview(ReviewDTO reviewDTO, long id);
}
