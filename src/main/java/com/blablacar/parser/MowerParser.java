package com.blablacar.parser;

import com.blablacar.base.Lawn;
import com.blablacar.base.Orientation;
import com.blablacar.mower.Mower;
import com.google.common.base.Splitter;

import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a Parser implementation for mowers : Parse a String to a Mower
 *
 * @author Mehdi AOUADI
 */
public class MowerParser implements Parser<String, Mower> {

  private static final String SEPARATOR = " ";
  private static final String PATTERN = "^\\d+ \\d+ [N|E|W|S]$";

  private final Lawn lawn;

  public MowerParser(Lawn lawn) {
    this.lawn = lawn;
  }

  @Override
  public Mower parse(String source) {
    checkArgument(source.matches(PATTERN),
        "Error when parsing the mower [Expecting: Positive integer 'X Y' and 'Orientation'; Got: '"
            + source + "']");
    List<String> fields = Splitter.on(SEPARATOR).splitToList(source);
    int x = Integer.parseInt(fields.get(0));
    int y = Integer.parseInt(fields.get(1));
    Orientation orientation = Orientation.valueOf(fields.get(2));
    return new Mower(UUID.randomUUID(), lawn.cellAt(x, y), orientation);
  }
}