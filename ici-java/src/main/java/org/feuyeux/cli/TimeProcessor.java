package org.feuyeux.cli;

import org.apache.commons.cli.Option;

public class TimeProcessor {
  public static Option buildTimeOption() {
    return Option.builder("t").longOpt("time").desc("time count").hasArg().argName("time").build();
  }
}
