package com.fedex.feedbackfrog.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Review {
  @Id
  @GeneratedValue
  private long id;
  private String text;
  private boolean isAnonym;
  private Rating rating;
  private enum Rating {
    PLUS,
    MINUS
  }

  @ManyToOne(cascade = CascadeType.ALL)
  private User reviewer;

  @ManyToOne(cascade = CascadeType.ALL)
  private Mentor mentor;

  public Review () {}

  public Review(String text, boolean isAnonym, User reviewer, Mentor mentor) {
    this.text = text;
    this.isAnonym = isAnonym;
    this.reviewer = reviewer;
    this.mentor = mentor;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isAnonym() {
    return isAnonym;
  }

  public void setAnonym(boolean anonym) {
    isAnonym = anonym;
  }

  public User getReviewer() {
    return reviewer;
  }

  public void setReviewer(User reviewer) {
    this.reviewer = reviewer;
  }

  public Mentor getMentor() {
    return mentor;
  }

  public void setMentor(Mentor mentor) {
    this.mentor = mentor;
  }

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }
}
