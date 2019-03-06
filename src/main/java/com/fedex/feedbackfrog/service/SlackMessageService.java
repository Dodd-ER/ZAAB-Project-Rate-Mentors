package com.fedex.feedbackfrog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.feedbackfrog.model.SlackMessageModels.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class SlackMessageService {
  public void sendMessage(){
    //authorizáció SlacAPIhoz + json as content type a headerben
    HttpHeaders header = new HttpHeaders();
    header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    header.setContentType(MediaType.APPLICATION_JSON);
    header.set("Authorization", "Bearer xoxp-566386912258-567820057110-566399192978-06841050f4f64213ea782b12c34ee0c2");

    //Slackmessage-t átmappelni JSONná
    ObjectMapper mapper = new ObjectMapper();
    SlackMessage message = new SlackMessage("DGPQ41YSJ", Arrays.asList(
        new Block(new SlackText("You have a new *rating*")),
        new Block(new SlackText("*VAN KIBASZOTT BÉKÁNK*")),
        new BlockWithImage(
            new SlackText("The image should be next to this"),
            new Accessory(
                "http://thetango.net/wp-content/uploads/2016/11/TheTango-ComedyWildlife-1101201608-620x386.png",
                "happy frog"))
    ));

    String jsonString = null;
    try {
      jsonString = mapper.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    //jsonString bodyba, header settings headerbe
    HttpEntity<String> entity = new HttpEntity<String>(jsonString, header);

    //RestTemplate-tel meghívni a SlackApi URL-t, Post methoddal, beállított http header&body, SlackMessage.class(valahonnan tudja, hogy ezzel kommunikálunk)
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<SlackMessage> respEntity = restTemplate.exchange("https://slack.com/api/chat.postMessage", HttpMethod.POST, entity, SlackMessage.class);
  }

}
