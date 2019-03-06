package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.FeedbackfrogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = FeedbackfrogApplication.class
)
@AutoConfigureMockMvc
@ContextConfiguration
public class MentorControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void get_mentor_by_id_when_id_is_not_valid_Test() throws Exception{

    mockMvc.perform(
        MockMvcRequestBuilders.post("/mentor/2")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name", is("error")))
        .andExpect(jsonPath("$.points", is("Username and Access token are missing!")))
        .andDo(print())
        .andReturn();
  }
}
