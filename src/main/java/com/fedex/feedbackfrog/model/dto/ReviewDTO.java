package com.fedex.feedbackfrog.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.model.entity.User;

public class ReviewDTO {
  public String text;
  public boolean isAnonym;
  public Rating rating;
  public enum Rating {
    PLUS,
    MINUS
  }

  public UserForReviewListDTO reviewer;
  public MentorDTO mentor;

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

  public MentorDTO getMentor() {
    return mentor;
  }

  public void setMentor(MentorDTO mentor) {
    this.mentor = mentor;
  }
}
