package com.fedex.feedbackfrog.model.dto;

public class UserForReviewListDTO {
  public String name;

  public UserForReviewListDTO() {}

  public UserForReviewListDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
