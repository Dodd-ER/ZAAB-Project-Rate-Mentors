package com.fedex.feedbackfrog.model.dto;

public class ReviewDTO_Post {
  public String text;
  public boolean isAnonym;
  public ReviewDTO.Rating rating;
  public enum Rating {
    PLUS,
    MINUS
  }

  public UserForReviewListDTO reviewer;
  public MentorForPostingReviewDTO mentor;

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

  public ReviewDTO.Rating getRating() {
    return rating;
  }

  public void setRating(ReviewDTO.Rating rating) {
    this.rating = rating;
  }

  public UserForReviewListDTO getReviewer() {
    return reviewer;
  }

  public void setReviewer(UserForReviewListDTO reviewer) {
    this.reviewer = reviewer;
  }

  public MentorForPostingReviewDTO getMentor() {
    return mentor;
  }

  public void setMentor(MentorForPostingReviewDTO mentor) {
    this.mentor = mentor;
  }
}
