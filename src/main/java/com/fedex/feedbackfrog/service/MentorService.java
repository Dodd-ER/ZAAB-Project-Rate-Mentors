package com.fedex.feedbackfrog.service;

import com.fedex.feedbackfrog.model.entity.Mentor;

import java.util.List;

public interface MentorService {
  List<Mentor> findAllMentor();
}
