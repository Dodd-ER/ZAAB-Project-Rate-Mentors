package com.fedex.feedbackfrog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.feedbackfrog.FeedbackfrogApplication;
import com.fedex.feedbackfrog.model.dto.UserDTO;
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
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void get_User_By_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/user/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.name", is("Bea")))
        .andExpect(jsonPath("$.isAdmin", is(true)))
        .andExpect(jsonPath("$.admin", is(true)))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_User_By_Non_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/user/10"))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.error", is("Cannot find user with given ID")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_All_User_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/user"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].name", is("Bea")))
        .andExpect(jsonPath("$[0].isAdmin", is(true)))
        .andExpect(jsonPath("$[0].admin", is(true)))
        .andExpect(jsonPath("$[1].name", is("Adel")))
        .andExpect(jsonPath("$[1].isAdmin", is(false)))
        .andExpect(jsonPath("$[1].admin", is(false)))
        .andExpect(jsonPath("$[2].name", is("Andor")))
        .andExpect(jsonPath("$[2].isAdmin", is(false)))
        .andExpect(jsonPath("$[2].admin", is(false)))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_User_By_Valid_Name_EndPoint_Test() throws Exception {

    mockMvc.perform(get("/api/user?name=Andor"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.name", is("Andor")))
        .andExpect(jsonPath("$.isAdmin", is(false)))
        .andExpect(jsonPath("$.admin", is(false)))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_User_By_Non_Valid_Name_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/user?name=NotValidName"))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.error", is("User not found")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void post_User_With_New_Name_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new UserDTO("Dodo", false))))
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void post_User_With_Already_Existing_Name_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new UserDTO("Adel", false))))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error", is("Name already exists in the database")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void put_User_By_Valid_Id_EndPoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new UserDTO("BeaUpdated", true))))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void put_User_By_Non_Valid_Id_EndPoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/user/10")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new UserDTO("NonExistedUserUpdated", true))))
        .andExpect(jsonPath("$.error", is("Cannot find user with given ID")))
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void delete_User_By_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/user/1"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void delete_Mentor_By_Non_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/user/10"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error", is("Cannot find user with given ID")))
        .andDo(print())
        .andReturn();
  }

}
