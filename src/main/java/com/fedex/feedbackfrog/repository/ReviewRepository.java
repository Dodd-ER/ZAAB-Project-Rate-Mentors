package com.fedex.feedbackfrog.repository;

import com.fedex.feedbackfrog.model.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
