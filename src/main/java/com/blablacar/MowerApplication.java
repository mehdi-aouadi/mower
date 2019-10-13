package com.blablacar;

import com.blablacar.cli.ApplicationOptions;
import com.blablacar.mower.Commander;
import com.blablacar.parser.Loader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.io.File;

import static com.blablacar.cli.CliUtils.parseArguments;
import static com.blablacar.cli.CliUtils.validateArguments;
import static com.blablacar.cli.CliUtils.printApplicationHelp;

/**
 * Hello world!
 *
 */
@Slf4j
public class MowerApplication {

    public static void main( String[] args ) {
        ApplicationOptions applicationOptions = ApplicationOptions.builder().build();
        try {
            CommandLine commandLine = parseArguments(args);
            try {
                applicationOptions = validateArguments(commandLine);
            } catch (Exception exception) {
                log.error("Error validating the options values.", exception);
                System.out.println("Invalid parameter(s) value(s).");
                printApplicationHelp();
                System.exit(1);
            }
            log.info("Initializing a Mower Application with the following parameters {}", applicationOptions);
        } catch (ParseException exception) {
            log.error("Error when parsing application parameters.", exception);
            System.out.println("Error when parsing application parameters.");
            printApplicationHelp();
            System.exit(1);
        }
        Commander commander = new Loader().fromFile(new File(applicationOptions.getFilePath()));
    }
}
