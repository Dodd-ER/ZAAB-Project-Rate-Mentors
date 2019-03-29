package com.fedex.feedbackfrog.model.dto;

public class MentorForReviewListDTO {
  public String name;

  public  MentorForReviewListDTO() {}

  public MentorForReviewListDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
