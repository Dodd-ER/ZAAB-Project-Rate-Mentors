package com.fedex.feedbackfrog.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Mentor {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  private int points;
  private String slackAlias;

  @OneToMany(mappedBy = "mentor")
  private List<Review> receivedReviews;

  public Mentor () {}

  public Mentor(String name, int points) {
    this.name = name;
    this.points = points;
  }

  public Mentor(String name, int points, List<Review> receivedReviews) {
    this.name = name;
    this.points = points;
    this.receivedReviews = receivedReviews;
  }

  public String getSlackAlias() {
    return slackAlias;
  }

  public void setSlackAlias(String slackAlias) {
    this.slackAlias = slackAlias;
  }

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

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public List<Review> getReceivedReviews() {
    return receivedReviews;
  }

  public void setReceivedReviews(List<Review> receivedReviews) {
    this.receivedReviews = receivedReviews;
  }
}
