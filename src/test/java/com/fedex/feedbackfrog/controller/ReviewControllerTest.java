package com.fedex.feedbackfrog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.feedbackfrog.FeedbackfrogApplication;
import com.fedex.feedbackfrog.model.dto.MentorForPostingReviewDTO;
import com.fedex.feedbackfrog.model.dto.ReviewDTO;
import com.fedex.feedbackfrog.model.dto.ReviewDTO_Post;
import com.fedex.feedbackfrog.model.dto.UserForReviewListDTO;
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

import static com.fedex.feedbackfrog.model.dto.ReviewDTO.Rating.PLUS;
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
public class ReviewControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void get_Review_By_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/review/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.text", is("Like your face!")))
        .andExpect(jsonPath("$.isAnonym", is(false)))
        .andExpect(jsonPath("$.rating", is("PLUS")))
        .andExpect(jsonPath("$.anonym", is(false)))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_Review_By_Non_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/review/10"))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.error", is("Cannot find review with given ID")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_All_Review_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/review"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].text", is("Like your face!")))
        .andExpect(jsonPath("$[0].isAnonym", is(false)))
        .andExpect(jsonPath("$[0].rating", is("PLUS")))
        .andExpect(jsonPath("$[0].anonym", is(false)))
        .andExpect(jsonPath("$[1].text", is("Like your enthusiasm!")))
        .andExpect(jsonPath("$[1].isAnonym", is(false)))
        .andExpect(jsonPath("$[1].rating", is("PLUS")))
        .andExpect(jsonPath("$[1].anonym", is(false)))
        .andExpect(jsonPath("$[2].text", is("Like your keyboard!")))
        .andExpect(jsonPath("$[2].isAnonym", is(false)))
        .andExpect(jsonPath("$[2].rating", is("PLUS")))
        .andExpect(jsonPath("$[2].anonym", is(false)))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_Review_By_Valid_Text_EndPoint_Test() throws Exception {

    mockMvc.perform(get("/api/review?text=Like your keyboard!"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].text", is("Like your keyboard!")))
        .andExpect(jsonPath("$[0].isAnonym", is(false)))
        .andExpect(jsonPath("$[0].rating", is("PLUS")))
        .andExpect(jsonPath("$[0].anonym", is(false)))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void get_Review_By_Non_Valid_Text_Endpoint_Test() throws Exception {

    mockMvc.perform(get("/api/review?text=NotValidText"))
        .andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.error", is("Review(s) not found")))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void post_New_Review_Endpoint_Test() throws Exception {
    UserForReviewListDTO andor = new UserForReviewListDTO("Andor");
    MentorForPostingReviewDTO blanka = new MentorForPostingReviewDTO("Blanka", "slackalias");

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/review")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new ReviewDTO_Post("like", false, ReviewDTO_Post.RatingEnum.PLUS, andor, blanka))))
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void put_Review_By_Valid_Id_EndPoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/review/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new ReviewDTO("LikeUpdated", false, PLUS))))
        .andExpect(status().isIAmATeapot())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void put_Review_By_Non_Valid_Id_EndPoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/review/10")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(new ObjectMapper().writeValueAsString(
                new ReviewDTO("LikeUpdated", false, PLUS))))
        .andExpect(jsonPath("$.error", is("Cannot find review with given ID")))
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void delete_Review_By_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/review/1"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void delete_Review_By_Non_Valid_Id_Endpoint_Test() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/review/10"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error", is("No such review")))
        .andDo(print())
        .andReturn();
  }
}
