package com.fedex.feedbackfrog.model.SlackMessageModels;

public class Block {
  private String type;
  private SlackText text;

  public Block(SlackText text) {
    this.type = "section";
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public SlackText getText() {
    return text;
  }

  public void setText(SlackText text) {
    this.text = text;
  }
}
