package com.fedex.feedbackfrog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.feedbackfrog.exception.GeneralException;
import com.fedex.feedbackfrog.model.SlackMessageModels.*;
import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class SlackMessageService {

  @Value("${SLACKTOKEN}")
  private String slackToken;

  public void sendMessage(ReviewDTO_Post dto) throws GeneralException {
    HttpEntity<String> entity = new HttpEntity<String>(getMessageBodyAsJson(dto), getMessageHeader());
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.exchange("https://slack.com/api/chat.postMessage", HttpMethod.POST, entity, SlackMessage.class);
  }

  private HttpHeaders getMessageHeader(){
    HttpHeaders header = new HttpHeaders();
    header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    header.setContentType(MediaType.APPLICATION_JSON);
    header.set("Authorization", "Bearer " + slackToken);

    return header;
  }

  private SlackMessage getMessageBody(ReviewDTO_Post dto){
    SlackMessage message = new SlackMessage(dto.mentor.slackAlias, Arrays.asList(
        new Block(new SlackText("*You have a new rating*: " + getEmoji(dto.rating.toString()))),
        new BlockWithImage(
            new SlackText(dto.text),
            new Accessory(
                getImageUrl(dto.rating.toString()),
                "frog"))
    ));

    return message;
  }

  private String getMessageBodyAsJson(ReviewDTO_Post dto) throws GeneralException{
    ObjectMapper mapper = new ObjectMapper();

    String jsonString = null;
    try {
      jsonString = mapper.writeValueAsString(getMessageBody(dto));
    } catch (JsonProcessingException e) {
      throw new GeneralException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    return jsonString;
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
