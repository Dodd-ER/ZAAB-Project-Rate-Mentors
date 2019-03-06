package com.fedex.feedbackfrog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.feedbackfrog.model.SlackMessageModels.*;
import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class SlackMessageService {
  public void sendMessage(ReviewDTO dto){
    HttpHeaders header = new HttpHeaders();
    header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    header.setContentType(MediaType.APPLICATION_JSON);
    header.set("Authorization", "Bearer xoxp-566386912258-567820057110-568778354018-4283ed3dff60dec56bc44b24116cd16c");
    ObjectMapper mapper = new ObjectMapper();
    SlackMessage message = new SlackMessage("DGPQ41YSJ", Arrays.asList(
        new Block(new SlackText("*You have a new rating*: " + getEmoji(dto.rating.toString()))),
        new BlockWithImage(
            new SlackText(dto.text),
            new Accessory(
                getImageUrl(dto.rating.toString()),
                "frog"))
    ));
    String jsonString = null;
    try {
      jsonString = mapper.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    HttpEntity<String> entity = new HttpEntity<String>(jsonString, header);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.exchange("https://slack.com/api/chat.postMessage", HttpMethod.POST, entity, SlackMessage.class);
  }

  private String getImageUrl(String rating) {
    return rating.equals("PLUS")
        ? "https://i.pinimg.com/originals/90/4e/b3/904eb39f6e9536cfc8c205045efe22c6.jpg"
        : "https://i.redd.it/sywglt58ngg01.jpg";
  }

  private String getEmoji(String rating) {
    return rating.equals("PLUS") ? ":thumbsup:" : ":thumbsdown:";
  }

}