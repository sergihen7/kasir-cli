package com.sergihen7.kasircli.application.pages;

import com.sergihen7.ext.KalkuKasir;
import com.sergihen7.kasircli.application.Kasir;
import com.sergihen7.kasircli.application.etc.Alert;

public class Pembayaran {
  public static double diskon = 0;
  public static double uang = 0;

  public static void show() {
    OUTER: while (true) {
      System.out.print("\033\143");

      if (Kasir.messages != null) {
        Alert.out(Kasir.messages);
      }
      Kasir.messages = null;

      Keranjang.table();

      System.out.println("[b] Bayar");
      System.out.println("[d] Diskon");
      System.out.println("[q] Back");

      String cmd = System.console().readLine();
      switch (cmd.toLowerCase()) {
        case "b" -> {
          placeBayar();
          break OUTER;
        }
        case "d" -> {
          placeDiskon();
          break;
        }
        case "q" -> {
          break OUTER;
        }
      }
    }
  }

  private static void placeDiskon() {
    while (true) {
      try {
        System.out.print("\033\143");

        if (Kasir.messages != null) {
          Alert.out(Kasir.messages);
        }
        Kasir.messages = null;

        Keranjang.table();

        System.out.print("Masukan Diskon: ");
        String diskon = System.console().readLine();

        double d = Double.parseDouble(diskon);

        if (d > 100)
          Kasir.messages = "Diskon tidak boleh lebih dari 100%";

        if (d < 0)
          Kasir.messages = "Diskon tidak boleh kurang dari 0%";

        Pembayaran.diskon = d;

        KalkuKasir kalkukasir = new KalkuKasir();
        kalkukasir.setPrice(Keranjang.totalPrice);
        kalkukasir.setDiscount(Pembayaran.diskon);
        Keranjang.discountPrice = kalkukasir.getPriceAfter();

        break;
      } catch (NumberFormatException e) {
        Kasir.messages = "Invalid Input";
      }
    }
  }

  private static void placeBayar() {
    while (true) {
      try {
        System.out.print("\033\143");

        if (Kasir.messages != null) {
          Alert.out(Kasir.messages);
        }

        Keranjang.table();

        Kasir.messages = null;

        System.out.print("Masukan Uang: ");
        String uang = System.console().readLine();

        if (uang.equals("q")) {
          break;
        }

        Pembayaran.uang = Double.parseDouble(uang);

        KalkuKasir kalkukasir = new KalkuKasir();
        kalkukasir.setPrice(Keranjang.discountPrice);
        kalkukasir.pay(Pembayaran.uang);

        double kembalian = kalkukasir.getResult();

        if (kembalian < 0) {
          Kasir.messages = "Uang Anda Kurang: " + kembalian * -1;
        } else {
          String leftAlignFormat = "| %-21s | %-13s |%n";
          System.out.format("+=======================+===============+%n");

          System.out.format(leftAlignFormat, "Total", "Rp " + Keranjang.discountPrice);
          System.out.format(leftAlignFormat, "Diskon", Pembayaran.diskon + "%");
          System.out.format(leftAlignFormat, "Bayar", "Rp " + Pembayaran.uang);
          System.out.format(leftAlignFormat, "Kembalian", "Rp " + kembalian);

          System.out.format("+=======================+===============+%n");

          System.out.println("\nPress enter to continue... ");
          System.console().readLine();
          Kasir.clear();
          break;
        }
      } catch (NumberFormatException e) {
        Kasir.messages = "Invalid Input";
      }
    }
  }
}
