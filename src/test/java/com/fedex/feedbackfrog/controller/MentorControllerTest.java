package com.fedex.feedbackfrog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.feedbackfrog.FeedbackfrogApplication;
import com.fedex.feedbackfrog.model.dto.MentorDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@Transactional
public class MentorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void get_Mentor_By_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/mentor/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.name", is("Ikarasz")))
        .andExpect(jsonPath("$.points", is(100)))
        .andExpect(jsonPath("$.slackAlias", is("Ika")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_Mentor_By_Non_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/mentor/10"))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.error", is("Mentor not found")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_All_Mentor_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/mentor"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].name", is("Ikarasz")))
        .andExpect(jsonPath("$[0].points", is(100)))
        .andExpect(jsonPath("$[0].slackAlias", is("Ika")))
        .andExpect(jsonPath("$[1].name", is("Blanka")))
        .andExpect(jsonPath("$[1].points", is(100)))
        .andExpect(jsonPath("$[1].slackAlias", is("Bla")))
        .andExpect(jsonPath("$[2].name", is("Gabor")))
        .andExpect(jsonPath("$[2].points", is(100)))
        .andExpect(jsonPath("$[2].slackAlias", is("Gab")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_Mentor_By_Name_EndPoint_Test() throws Exception {

    mockMvc.perform(get("/mentor?name=Ikarasz"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.name", is("Ikarasz")))
        .andExpect(jsonPath("$.points", is(100)))
        .andExpect(jsonPath("$.slackAlias", is("Ika")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_Mentor_By_Non_Valid_Name_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/mentor?name=NotValidName"))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.error", is("Mentor not found")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void post_Mentor_With_New_Name_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.post("/mentor")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new MentorDTO("Barna", 100))))
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void post_Mentor_With_Already_Existing_Name_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.post("/mentor")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new MentorDTO("Ikarasz", 100))))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Name already exists in the database")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void delete_Mentor_By_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/mentor/1"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void delete_Mentor_By_Non_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/mentor/10"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Mentor not found")))
        .andDo(print())
        .andReturn();
  }
}
