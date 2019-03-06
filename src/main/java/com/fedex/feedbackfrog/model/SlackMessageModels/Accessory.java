package com.fedex.feedbackfrog.model.SlackMessageModels;

public class Accessory {
  private String type;
  private String image_url;
  private String alt_text;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public String getAlt_text() {
    return alt_text;
  }

  public void setAlt_text(String alt_text) {
    this.alt_text = alt_text;
  }

  public Accessory(String image_url, String alt_text) {
    this.type = "image";
    this.image_url = image_url;
    this.alt_text = alt_text;
  }
}
