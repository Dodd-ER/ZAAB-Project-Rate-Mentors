package com.fedex.feedbackfrog.repository;

import com.fedex.feedbackfrog.model.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
  List<Review> getByTextContaining(String text);
  boolean existsByTextContaining(String text);
}
