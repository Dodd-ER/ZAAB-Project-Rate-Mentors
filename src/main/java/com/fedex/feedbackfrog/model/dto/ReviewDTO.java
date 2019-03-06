package com.fedex.feedbackfrog.model.dto;

import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.model.entity.User;

public class ReviewDTO {
  String text;
  boolean isAnonym;
  enum rating {
    PLUS,
    MINUS
  }
  User reviewer;
  Mentor mentor;
}
