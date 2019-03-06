package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
import com.fedex.feedbackfrog.model.entity.Review;
import com.fedex.feedbackfrog.repository.MentorRepository;
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
  private UserRepository userRepository;
  private MentorRepository mentorRepository;
  private ModelMapper mapper;

  @Autowired
  public ReviewServiceImpl(ReviewRepository repository, ModelMapper mapper, UserRepository userRepository, MentorRepository mentorRepository) {
    this.repository = repository;
    this.mapper = mapper;
    this.userRepository = userRepository;
    this.mentorRepository = mentorRepository;
  }

  @Override
  public void saveReview(ReviewDTO_Post reviewDTO) {
    if (reviewDTO != null){
      Review review = mapper.map(reviewDTO, Review.class);
      review.setReviewer(userRepository.findUserByName(reviewDTO.getReviewer().getName()));
      review.setMentor(mentorRepository.findByName(reviewDTO.getMentor().getName()));
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

  @Override
  public void updateReview(ReviewDTO reviewDTO, long id) {
    Review review = repository.findById(id).orElse(null);
    mapper.map(reviewDTO, review);
    repository.save(review);
  }
}
