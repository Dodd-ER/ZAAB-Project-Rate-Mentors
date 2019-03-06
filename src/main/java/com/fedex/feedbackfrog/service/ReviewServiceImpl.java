package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.model.entity.Review;
import com.fedex.feedbackfrog.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

  private ReviewRepository repository;
  private ModelMapper mapper;

  @Autowired
  public ReviewServiceImpl(ReviewRepository repository, ModelMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public void saveReview(ReviewDTO reviewDTO) {
    if (reviewDTO != null){
      repository.save(mapper.map(reviewDTO, Review.class));
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
}
