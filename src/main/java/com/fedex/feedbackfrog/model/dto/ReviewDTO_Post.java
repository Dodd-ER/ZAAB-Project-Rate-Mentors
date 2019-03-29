package com.fedex.feedbackfrog.model.dto;

public class ReviewDTO_Post {
  public String text;
  public boolean isAnonym;
  public RatingEnum rating;
  public enum RatingEnum {
    PLUS,
    MINUS
  }

  public UserForReviewListDTO reviewer;
  public MentorForPostingReviewDTO mentor;

  public ReviewDTO_Post() {
  }

  public ReviewDTO_Post(String text,
                        boolean isAnonym,
                        RatingEnum rating,
                        UserForReviewListDTO reviewer,
                        MentorForPostingReviewDTO mentor) {
    this.text = text;
    this.isAnonym = isAnonym;
    this.rating = rating;
    this.reviewer = reviewer;
    this.mentor = mentor;
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

  public RatingEnum getRating() {
    return rating;
  }

  public void setRating(RatingEnum rating) {
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
