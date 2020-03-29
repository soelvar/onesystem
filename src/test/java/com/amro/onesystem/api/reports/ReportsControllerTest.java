package com.amro.onesystem.api.reports;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.amro.onesystem.api.inputs.InputsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ReportsControllerTest {

  private static final String TOPIC_NAME = "aTopicName";
  public static final String TRANSACTION_MESSAGE = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";

  private InputsController controller;

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @Before
  public void setup() {
    controller = new InputsController(kafkaTemplate, TOPIC_NAME);
  }

  @Test
  public void should_post_transaction_to_kafka() {

    MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "file", "text/plain", TRANSACTION_MESSAGE
        .getBytes());

    controller.postInput(mockMultipartFile);

    verify(kafkaTemplate, times(1)).send(TOPIC_NAME, TRANSACTION_MESSAGE);
  }
}