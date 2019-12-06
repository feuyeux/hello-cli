package org.feuyeux.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Hello {

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption(getVersionOption())
            .addOption(getHelpOption())
            .addOption(getCpuOption());

        try {
            CommandLine commandLine = parser.parse(options, args);

            HelpFormatter hf = new HelpFormatter();
            hf.setWidth(110);
            if (commandLine.hasOption("h")) {
                hf.printHelp("hello-cli", options, true);
            }
            if (commandLine.hasOption("v")) {
                System.out.println("hello-cli 1.0.0");
            }
            String value = commandLine.getOptionValue("cpu");
            if ("cores".equals(value)) {
                System.out.println("Available processors (cores): " +
                    Runtime.getRuntime().availableProcessors());
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            new HelpFormatter().printHelp("cli", options);
        }
    }

    private static Option getHelpOption() {
        return Option.builder("h").desc("help for hello cli in java")
            .longOpt("help")
            .build();
    }

    private static Option getVersionOption() {
        return Option.builder("v").desc("hello cli version")
            .longOpt("version")
            .build();
    }

    private static Option getCpuOption() {
        return Option.builder().desc("cpu count")
            .longOpt("cpu")
            .hasArg()
            .build();
    }
}
