package com.fedex.feedbackfrog.repository;

import com.fedex.feedbackfrog.model.entity.Mentor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends CrudRepository<Mentor, Long> {
}
