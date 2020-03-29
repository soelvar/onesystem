package com.amro.onesystem.api.inputs;

import static com.amro.onesystem.api.inputs.InputsController.ONESYSTEM_INPUTS_URI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amro.onesystem.ApplicationWithoutKafkaIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;

public class InputsControllerIT extends ApplicationWithoutKafkaIT {

  @Value(value = "${kafka.topics.onesystemTopic.name}")
  private String topicName;

  @Value("classpath:files/Input.txt")
  private Resource inputTxtFile;


  @Test
  public void should_return_httpStatus_200_and_verify_kafka_message_was_received() throws Exception {
    MockMultipartFile file = new MockMultipartFile("file", "file", "multipart/form-data", inputTxtFile.getInputStream());

    MvcResult mvcResult = mockMvc
        .perform(multipart(ONESYSTEM_INPUTS_URI)
            .file(file)
            .accept(APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn();

    verify(kafkaTemplate, atLeast(2)).send(eq(topicName), anyString());
  }

  @Test
  public void should_return_httpStatus_400_when_no_file_supplied() throws Exception {
    MvcResult mvcResult = mockMvc.perform(multipart(ONESYSTEM_INPUTS_URI)
        .accept(APPLICATION_JSON_VALUE)
    )
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andReturn();
  }

  @Test
  public void should_return_httpStatus_500_when_illegalstate() throws Exception {
    doThrow(new IllegalStateException("test")).when(kafkaTemplate).send(any(), any());

    MockMultipartFile file = new MockMultipartFile("file", "file", "multipart/form-data", inputTxtFile.getInputStream());

    MvcResult mvcResult = mockMvc
        .perform(multipart(ONESYSTEM_INPUTS_URI)
            .file(file)
            .accept(APPLICATION_JSON_VALUE)
        )
        .andExpect(status().is5xxServerError())
        .andDo(print())
        .andReturn();
  }
}