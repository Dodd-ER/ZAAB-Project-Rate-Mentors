package com.fedex.feedbackfrog.model.dto;

import java.util.List;

public class MentorDTO {
  String name;
  int points;
  List<ReviewDTO> receivedReviews;

  public MentorDTO() {
  }

  public MentorDTO(String name, int points) {
    this.name = name;
    this.points = points;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public List<ReviewDTO> getReceivedReviews() {
    return receivedReviews;
  }

  public void setReceivedReviews(List<ReviewDTO> receivedReviews) {
    this.receivedReviews = receivedReviews;
  }
}
