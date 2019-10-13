package com.blablacar.parser;

import com.blablacar.base.Lawn;
import com.blablacar.base.Move;
import com.blablacar.base.Orientation;
import com.blablacar.mower.Mower;
import com.google.common.base.Splitter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a Parser implementation for mowers : Parse a String to a Mower
 *
 * @author Mehdi AOUADI
 */
public class MowerParser implements Parser<MowerText, Mower> {

  private static final String SEPARATOR = " ";
  private static final String PATTERN = "^\\d+ \\d+ [N|E|W|S]$";

  private final Lawn lawn;

  public MowerParser(Lawn lawn) {
    this.lawn = lawn;
  }

  @Override
  public Mower parse(MowerText source) {
    checkArgument(source.mowerPosition.matches(PATTERN),
        "Error when parsing the mower [Expecting: Positive integer 'X Y' and 'Orientation'; Got: '"
            + source + "']");
    List<String> fields = Splitter.on(SEPARATOR).splitToList(source.mowerPosition);
    int x = Integer.parseInt(fields.get(0));
    int y = Integer.parseInt(fields.get(1));
    Orientation orientation = Orientation.valueOf(fields.get(2));
    Queue<Move> instructions = parseInstructions(source.mowerInstructions);
    return new Mower(UUID.randomUUID(), lawn.cellAt(x, y), orientation, instructions);
  }

  private Queue<Move> parseInstructions(String instructionsText) {
    Queue<Move> instructions = new LinkedList<>();
    for (char instruction : instructionsText.toCharArray()) {
      switch (instruction) {
        case 'L':
          instructions.add(Move.L);
          break;
        case 'R':
          instructions.add(Move.R);
          break;
        case 'F':
          instructions.add(Move.F);
          break;
        default:
          throw new IllegalArgumentException(
              "Error when parsing the instruction [Expecting: (L|R|F) n time'; Got : '" + instructionsText
                  + "']");
      }
    }
    return instructions;
  }
}