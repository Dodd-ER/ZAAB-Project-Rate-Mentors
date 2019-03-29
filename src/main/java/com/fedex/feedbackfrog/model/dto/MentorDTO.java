package com.fedex.feedbackfrog.model.dto;

import java.util.List;

public class MentorDTO {
  long id;
  String name;
  int points;
  List<ReviewDTO> receivedReviews;
  String slackAlias;
  String image;

  public MentorDTO() {
  }

  public MentorDTO(String name, int points) {
    this.name = name;
    this.points = points;
  }

  public MentorDTO(String name, int points, List<ReviewDTO> receivedReviews, String slackAlias) {
    this.name = name;
    this.points = points;
    this.receivedReviews = receivedReviews;
    this.slackAlias = slackAlias;
  }

  public String getSlackAlias() {
    return slackAlias;
  }

  public void setSlackAlias(String slackAlias) {
    this.slackAlias = slackAlias;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
