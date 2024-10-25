package com.sergihen7.kasircli.application.etc;

public class Alert {
    public static String format = "| %-51s |%n";

    public static void out(String message) {
      System.out.format("+=====================================================+%n");
      System.out.format(format, message);
      System.out.format("+=====================================================+%n");
    }
}
