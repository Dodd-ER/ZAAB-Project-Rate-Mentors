package com.fedex.feedbackfrog.model.dto;

import java.util.List;

public class UserDTO {
  public String name;
  public boolean isAdmin;
  public List<ReviewDTO> sentReviews;

  public UserDTO() {
  }

  public UserDTO(String name, boolean isAdmin) {
    this.name = name;
    this.isAdmin = isAdmin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isAdmin() {
    return this.isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public List<ReviewDTO> getSentReviews() {
    return sentReviews;
  }

  public void setSentReviews(List<ReviewDTO> sentReviews) {
    this.sentReviews = sentReviews;
  }
}
