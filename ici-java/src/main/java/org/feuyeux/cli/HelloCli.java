package org.feuyeux.cli;

import static org.feuyeux.cli.SpaceProcessor.buildSpaceOption;
import static org.feuyeux.cli.StatusProcessor.buildStatusOption;
import static org.feuyeux.cli.TimeProcessor.buildTimeOption;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class HelloCli {

  public static void main(String[] args) {
    CommandLineParser parser = new DefaultParser();
    Options options = buildOptions();
    try {
      CommandLine commandLine = parser.parse(options, args);

      HelpFormatter hf = new HelpFormatter();
      hf.setWidth(110);
      if (commandLine.hasOption("h")) {
        hf.printHelp("ici", options, true);
      }
      if (commandLine.hasOption("s")) {
        SpaceProcessor.execute();
      }

    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
      new HelpFormatter().printHelp("cli", options);
    }
  }

  private static Options buildOptions() {
    Options options = new Options();
    options
        .addOption(Option.builder("h").desc("help for ici").longOpt("help").build())
        .addOption(buildSpaceOption())
        .addOption(buildTimeOption())
        .addOption(buildStatusOption());
    return options;
  }
}
