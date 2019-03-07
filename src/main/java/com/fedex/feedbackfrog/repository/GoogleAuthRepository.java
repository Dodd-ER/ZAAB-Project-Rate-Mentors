package com.fedex.feedbackfrog.repository;

import com.fedex.feedbackfrog.model.entity.GoogleAuthModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleAuthRepository extends CrudRepository<GoogleAuthModel, Long> {
}
