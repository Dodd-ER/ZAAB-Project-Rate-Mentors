package com.fedex.feedbackfrog.controller;

import com.fedex.feedbackfrog.FeedbackfrogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = FeedbackfrogApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql"),
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
})
@ContextConfiguration
@ActiveProfiles("test")
public class MentorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void get_All_Mentor_Endpoint_Test() throws Exception {

    MvcResult mvcResult = mockMvc.perform(
        MockMvcRequestBuilders.get("/mentor")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].name", is("Ikarasz")))
        .andExpect(jsonPath("$[0].points", is(100)))
        .andExpect(jsonPath("$[1].name", is("Blanka")))
        .andExpect(jsonPath("$[1].points", is(100)))
        .andExpect(jsonPath("$[2].name", is("Gabor")))
        .andExpect(jsonPath("$[2].points", is(100)))
        .andDo(print())
        .andReturn();
  }
}
