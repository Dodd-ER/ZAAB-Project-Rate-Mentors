package com.fedex.feedbackfrog.model.dto;

import java.util.List;

public class UserGetDTO extends UserDTO {
  long id;
  public String name;
  public boolean isAdmin;
  public List<ReviewDTO> sentReviews;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
