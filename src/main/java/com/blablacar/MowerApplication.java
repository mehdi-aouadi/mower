package com.blablacar;

import com.blablacar.cli.ApplicationOptions;
import com.blablacar.mower.Commander;
import com.blablacar.parser.Loader;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static com.blablacar.cli.CliUtils.parseArguments;
import static com.blablacar.cli.CliUtils.validateArguments;
import static com.blablacar.cli.CliUtils.printApplicationHelp;

/**
 * Hello world!
 *
 */
public class MowerApplication {

    private static Logger logger = LoggerFactory.getLogger(MowerApplication.class);

    public static void main( String[] args ) {
        ApplicationOptions applicationOptions = ApplicationOptions.builder().build();
        try {
            CommandLine commandLine = parseArguments(args);
            if(commandLine.hasOption("h")) {
                printApplicationHelp();
                System.exit(1);
            }
            try {
                applicationOptions = validateArguments(commandLine);
            } catch (Exception exception) {
                logger.error("Error validating the options values.", exception);
                System.out.println("Invalid parameter(s) value(s).");
                printApplicationHelp();
                System.exit(1);
            }
            logger.info("Initializing a Mower Application with the following parameters {}", applicationOptions);
        } catch (ParseException exception) {
            logger.error("Error when parsing application parameters.", exception);
            System.out.println("Error when parsing application parameters.");
            printApplicationHelp();
            System.exit(1);
        }
        Commander commander = new Loader().fromFile(new File(applicationOptions.getFilePath()));
        commander.startMowing();
        System.out.println("------------------------------------------");
        System.out.println("Final mowers positions: ");
        commander.getMowers().stream().forEach(mower -> {
            System.out.println(mower.getCell().getPosition().getColumn() +
                " " +
                mower.getCell().getPosition().getRow() +
                " " +
                mower.getOrientation().toString()
                );
        });
        System.out.println("------------------------------------------");
    }
}
