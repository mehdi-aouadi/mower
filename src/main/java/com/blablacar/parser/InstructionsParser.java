package com.blablacar.parser;

import com.blablacar.base.Move;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Parser implementation : Parses a String to a Queue of moves
 *
 * @author Mehdi AOUADI
 */
public class InstructionsParser implements Parser<String, Queue<Move>> {

  @Override
  public Queue<Move> parse(String source) {
    Queue<Move> instructions = new LinkedList<>();

    for (char instruction : source.toCharArray()) {
      switch (instruction) {
        case 'G':
          instructions.add(Move.G);
          break;
        case 'D':
          instructions.add(Move.D);
          break;
        case 'A':
          instructions.add(Move.A);
          break;
        default:
          throw new IllegalArgumentException(
              "Error when parsing the instruction [Expecting: (G|D|A) n time'; Got : '" + source
                  + "']");
      }
    }

    return instructions;
  }

}
