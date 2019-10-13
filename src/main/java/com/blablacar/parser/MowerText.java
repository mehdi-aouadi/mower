package com.blablacar.parser;

import lombok.Builder;

/**
 * A two line text representation of a {@link com.blablacar.mower.Mower}
 * The first line is the mower {@link com.blablacar.base.Position} and {@link com.blablacar.base.Orientation}
 * The second line represents the mower instructions. A list of ({@link com.blablacar.base.Move}) values
 */

@Builder
public class MowerText {
  /**
   * A text representation of the mower {@link com.blablacar.base.Position} and {@link com.blablacar.base.Orientation}
   */
  String mowerPosition;
  /**
   * A text representation of the mower instructions. A list of ({@link com.blablacar.base.Move}) values
   */
  String mowerInstructions;
}
