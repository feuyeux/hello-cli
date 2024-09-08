package org.feuyeux.cli;

import java.io.File;
import org.apache.commons.cli.Option;

public class SpaceProcessor {
  static Option buildSpaceOption() {
    return Option.builder("s")
        .longOpt("space")
        .desc("space count")
        .hasArg()
        .argName("space")
        .build();
  }

  static void execute() {
    File currentPath = new File(".");
    System.out.println("SpaceProcessor");
    long totalSpace = currentPath.getTotalSpace();
    long freeSpace = currentPath.getFreeSpace();
    long usableSpace = currentPath.getUsableSpace();
    // 拼接并打印4个变量信息
    System.out.printf("Total: %d, Free: %d, Usable: %d%n", totalSpace, freeSpace, usableSpace);
  }
}
