package com.blablacar.cli;

import lombok.Builder;
import lombok.Value;

/**
 * The {@link com.blablacar.MowerApplication} options.
 */
@Value
@Builder
public class ApplicationOptions {

  /**
   * The mower instructions file path.
   */
  @Builder.Default
  private String filePath = "/tmp/mower.txt";

}
