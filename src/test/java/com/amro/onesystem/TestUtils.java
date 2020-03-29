package com.amro.onesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;

public class TestUtils {

  public static String toString(final Resource resource) throws IOException {
    try (InputStream inputStream = resource.getInputStream()) {
      return toString(inputStream);

    }
  }

  public static String toString(final InputStream inputStream) {
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        .lines().collect(Collectors.joining("\n"));

  }
}
