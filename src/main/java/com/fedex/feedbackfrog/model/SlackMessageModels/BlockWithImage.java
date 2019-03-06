package com.fedex.feedbackfrog.model.SlackMessageModels;

public class BlockWithImage extends Block {
  private Accessory accessory;

  public BlockWithImage(SlackText text, Accessory accessory) {
    super(text);
    this.accessory = accessory;
  }

  public Accessory getAccessory() {
    return accessory;
  }

  public void setAccessory(Accessory accessory) {
    this.accessory = accessory;
  }
}
