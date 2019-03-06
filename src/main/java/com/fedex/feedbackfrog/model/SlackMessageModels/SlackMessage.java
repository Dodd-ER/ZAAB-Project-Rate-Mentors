package com.fedex.feedbackfrog.model.SlackMessageModels;

import java.util.List;

public class SlackMessage {
  private String channel;
  private List<Block> blocks;

  public SlackMessage(String channel, List<Block> blocks) {
    this.channel = channel;
    this.blocks = blocks;
  }

  public SlackMessage() {
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public List<Block> getBlocks() {
    return blocks;
  }

  public void setBlocks(List<Block> blocks) {
    this.blocks = blocks;
  }
}
