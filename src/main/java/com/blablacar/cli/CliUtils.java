package com.blablacar.cli;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.nio.file.Paths;


@Slf4j
public class CliUtils {

  static final String FILE_PATH_LONG_OPTION = "file";
  static final String FILE_PATH_SHORT_OPTION = "f";

  /**
   * Validates the {@link com.blablacar.MowerApplication} options.
   * @param commandLine The {@link CommandLine} created from the user command line.
   * @return an {@link ApplicationOptions} with the valid option value
   *         and the default values otherwise.
   */
  public static ApplicationOptions validateArguments(CommandLine commandLine) {
    ApplicationOptions defaults = ApplicationOptions.builder().build();
    String filePath = commandLine.getOptionValue(FILE_PATH_LONG_OPTION);
    if (filePath != null) {
      if (!Paths.get(filePath).toFile().isFile()) {
        log.error("{} no such file.", filePath);
        System.out.println(String.format("%s no such file", filePath));
        throw new IllegalArgumentException(filePath + " no such file.");
      }
    } else {
      filePath = defaults.getFilePath();
    }

    return ApplicationOptions.builder()
        .filePath(filePath)
        .build();
  }

  /**
   * Parses an {@link String} array of options to a {@link CommandLine}.
   * @param args a {@link String} array of option values.
   * @return a {@link CommandLine} created with the option values.
   * @throws ParseException if there are any problems encountered
   *      while parsing the command line tokens
   */
  public static CommandLine parseArguments(String[] args) throws ParseException {
    Options options = getOptions();
    CommandLineParser parser = new DefaultParser();
    options.addOption("h", "help", false, "show help.");
    return parser.parse(options, args);
  }

  @VisibleForTesting
  static Options getOptions() {
    ApplicationOptions defaults = ApplicationOptions.builder().build();
    Options options = new Options();
    options.addOption(FILE_PATH_SHORT_OPTION, FILE_PATH_LONG_OPTION, true,
        "The mower instructions file absolute path, default " + defaults.getFilePath());
    return options;
  }

  /**
   * Prints the application usage help manual.
   */
  public static void printApplicationHelp() {
    Options options = getOptions();
    HelpFormatter formatter = new HelpFormatter();
    formatter.setWidth(150);
    formatter.printHelp("./mower.sh", options, true);
  }

}
