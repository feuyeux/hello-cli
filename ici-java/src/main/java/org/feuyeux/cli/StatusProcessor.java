package org.feuyeux.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

public class StatusProcessor {
  public static Option buildStatusOption() {
    return Option.builder("st")
        .longOpt("status")
        .desc("status count")
        .hasArg()
        .argName("status")
        .build();
  }

  static void execute(CommandLine commandLine) {
    String value = commandLine.getOptionValue("cpu");
    if ("cores".equals(value)) {
      System.out.println(
          "Available processors (cores): " + Runtime.getRuntime().availableProcessors());
    } else if ("memory".equals(value)) {
      System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
    } else if ("maxMemory".equals(value)) {
      System.out.println(
          "Maximum memory (bytes): "
              + (Runtime.getRuntime().maxMemory() == Long.MAX_VALUE
                  ? "no limit"
                  : Runtime.getRuntime().maxMemory()));
    } else if ("totalMemory".equals(value)) {
      System.out.println(
          "Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());
    }
  }
}
