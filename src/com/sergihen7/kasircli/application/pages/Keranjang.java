package com.sergihen7.kasircli.application.pages;

import java.util.List;

import com.sergihen7.kasircli.application.Kasir;
import com.sergihen7.kasircli.application.collections.BarangCollections;
import com.sergihen7.kasircli.application.models.Barang;

public class Keranjang {

  public static List<Barang> listKeranjang;
  public static double totalPrice = 0;
  public static double discountPrice = 0;

  public static void show() {
    while (true) {
      System.out.print("\033\143");
      System.out.println("\nKeranjang :");

      table();

      System.out.println("\nPress Enter to back...");
      System.console().readLine();
      break;
    }

  }

  public static void addKeranjang(int i, BarangCollections bc) {
    if (bc.listBarang.size() > i && i >= 0) {
      Barang b = bc.listBarang.get(i);

      totalPrice += b.price;
      discountPrice = totalPrice;
      listKeranjang.add(b);
      Kasir.messages = "Barang " + b.name + " Ditambahkan Ke Keranjang";
    } else {
      Kasir.messages = "Barang Tidak Tersedia";
    }
  }

  public static void table() {
    int i = 1;
    String format = "| %-3s | %-15s | %-10s |%n";
    System.out.format("+=====+=================+============+%n");
    if (listKeranjang != null && listKeranjang.size() > 0) {
      for (Barang b : listKeranjang) {
        System.out.format(format, i++, b.name, b.price);
      }
    } else {
      System.out.format(format, " ", " ", " ");
    }
    System.out.format("+=====+=================+============+%n");
    System.out.format(format, " ", "Total: ", "Rp " + totalPrice);
    System.out.format(format, " ", "Diskon: ", Pembayaran.diskon + "%");
    System.out.format(format, " ", "Akhir: ", "Rp " + discountPrice);
    System.out.format("+=====+=================+============+%n");
  }

}
