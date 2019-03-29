package com.fedex.feedbackfrog.model.dto;

public class ReviewDTO {
  public String text;
  public boolean isAnonym;
  public Rating rating;
  public enum Rating {
    PLUS,
    MINUS
  }

  public UserForReviewListDTO reviewer;
  public MentorForReviewListDTO mentor;

  public ReviewDTO() {
  }

  public ReviewDTO(String text, boolean isAnonym, Rating rating) {
    this.text = text;
    this.isAnonym = isAnonym;
    this.rating = rating;
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

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public UserForReviewListDTO getReviewer() {
    return reviewer;
  }

  public void setReviewer(UserForReviewListDTO reviewer) {
    this.reviewer = reviewer;
  }

  public MentorForReviewListDTO getMentor() {
    return mentor;
  }

  public void setMentor(MentorForReviewListDTO mentor) {
    this.mentor = mentor;
  }
}
