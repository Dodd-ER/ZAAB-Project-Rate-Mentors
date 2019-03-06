package com.fedex.feedbackfrog.model.SlackMessageModels;

public class SlackText {
  private String type;
  private String text;

  public SlackText(String text) {
    this.type = "mrkdwn";
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
