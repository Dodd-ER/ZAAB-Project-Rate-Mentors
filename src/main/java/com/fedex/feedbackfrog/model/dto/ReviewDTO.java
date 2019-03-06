package com.fedex.feedbackfrog.model.dto;

import com.fedex.feedbackfrog.model.entity.Mentor;
import com.fedex.feedbackfrog.model.entity.User;

public class ReviewDTO {
  public String text;
  public boolean isAnonym;
  public enum rating {
    PLUS,
    MINUS
  }
  public User reviewer;
  public Mentor mentor;

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
}
