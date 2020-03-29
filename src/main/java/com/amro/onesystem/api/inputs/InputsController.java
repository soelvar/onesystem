package com.amro.onesystem.api.inputs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Validated
@Slf4j
@RestController
@RequestMapping(value = InputsController.ONESYSTEM_INPUTS_URI)
public class InputsController {

  public static final String ONESYSTEM_INPUTS_URI = "/onesystem/inputs";
  private final KafkaTemplate<String, String> kafkaTemplate;

  private final String topicName;

  @Autowired
  public InputsController(@NotNull final KafkaTemplate<String, String> kafkaTemplate,
      @NotBlank @Value(value = "${kafka.topics.onesystemTopic.name}") final String topicName) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicName = topicName;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public void postInput(@NotNull @RequestPart(value = "file") final MultipartFile multipartFile) {
    log.info("Start - receiving Input file to process");

    try {
      InputStream inputStream = multipartFile.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      while (reader.ready()) {
        String line = reader.readLine();
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topicName, line);
      }

    } catch (Exception e) {
      log.error("Unable to post file for processing!", e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to post input!");
    }

    log.info("Finished - receiving Input file to process");
  }
}
