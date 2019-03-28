package com.fedex.feedbackfrog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = FeedbackfrogApplication.class)
@ContextConfiguration
@TestPropertySource("/application-test.properties")
@ActiveProfiles("test")
public class FeedbackfrogApplicationTests {

  @Test
  public void contextLoads() {
  }
}
