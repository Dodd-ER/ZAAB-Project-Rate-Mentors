package com.fedex.feedbackfrog.model.dto;

import java.util.List;

public class UserDTO {
  private String name;
  private boolean isAdmin;
  private List<ReviewDTO> sentReviews;
}
