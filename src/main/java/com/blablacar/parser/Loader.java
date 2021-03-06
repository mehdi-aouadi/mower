package com.blablacar.parser;

import com.blablacar.base.Lawn;
import com.blablacar.base.Move;
import com.blablacar.mower.Commander;
import com.blablacar.mower.Mower;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a Loader : Loads the application input and transforms it to a
 * Commander
 *
 * @author Mehdi AOUADI
 */

public class Loader {

  private static final String LINE_SEPRATOR = "\n";
  private static Logger logger = LoggerFactory.getLogger(Loader.class);

  public Commander fromFile(File file) {
    try {
      return fromLines(Files.readLines(file, Charsets.UTF_8));
    } catch (IOException e) {
      logger.error("Cannot open the specified file at path : {} \n {}", file.getAbsolutePath(), e.toString());
      return null;
    }
  }

  public Commander fromText(final String text) {
    return fromLines(Splitter.on(LINE_SEPRATOR).omitEmptyStrings().splitToList(text));
  }

  public Commander fromLines(List<String> pLines) {
    checkArgument(pLines.size() > 2,
        "Error when loading the Commander: Missing information in the file. Expecting at least 3 lines.");
    Iterator<String> lines = pLines.iterator();
    Parser<String, Lawn> lawnParser = new LawnParser();
    Lawn lawn = lawnParser.parse(lines.next());
    Parser<MowerText, Mower> mowerParser = new MowerParser(lawn);
    List<Mower> mowers = Lists.newArrayList();
    while (lines.hasNext()) {
      MowerText mowerText = MowerText.builder().mowerPosition(lines.next()).mowerInstructions(lines.next()).build();
      Mower mower = mowerParser.parse(mowerText);
      mowers.add(mower);
    }
    return new Commander(lawn, mowers);
  }
}
