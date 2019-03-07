package com.fedex.feedbackfrog.model.dto;

public class MentorForPostingReviewDTO {
  public String name;
  public String slackAlias;

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
}
