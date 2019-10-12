package com.blablacar.parser;

import com.blablacar.base.Lawn;
import com.google.common.base.Splitter;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents an implementation of Parser : Parses a String to a Lawn
 *
 * @author Mehdi AOUADI
 */
public class LawnParser implements Parser<String, Lawn> {

  private static final String SEPARATOR = " ";
  private static final String PATTERN = "^\\d+\\s\\d+$";

  @Override
  public Lawn parse(String source) {

    checkArgument(source.matches(PATTERN), "Error when parsing the lawn infos [Expecting:Postive integers 'Width Height'; Got : '"
        + source + "']");
    List<String> fields = Splitter.on(SEPARATOR).splitToList(source);
    //Notice the +1 : the lawn goes from 0:0 cell to width:height cell
    return new Lawn(Integer.valueOf(fields.get(0)) + 1, Integer.valueOf(fields.get(1)) + 1);

  }
}