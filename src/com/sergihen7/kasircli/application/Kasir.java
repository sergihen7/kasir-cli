package com.sergihen7.kasircli.application;

import java.util.ArrayList;
import com.sergihen7.ext.KalkuKasir;
import com.sergihen7.kasircli.application.collections.BarangCollections;
import com.sergihen7.kasircli.application.etc.Alert;
import com.sergihen7.kasircli.application.pages.Keranjang;
import com.sergihen7.kasircli.application.pages.Pembayaran;

public class Kasir {

    public static String format = "| %-51s |%n";

    public static String messages;

    private static void init() {
        Alert.format = format;
        Keranjang.listKeranjang = new ArrayList<>();
    }

    public static void start() {
        init();

        OUTER: while (true) {
            System.out.print("\033\143");

            if (messages != null) {
                Alert.out(messages);
            }
            messages = null;

            System.out.println("\nList Barang :");

            BarangCollections bc = new BarangCollections();
            bc.showBarang();

            System.out.println("[l] Lihat Keranjang");
            System.out.println("[p] Pembayaran");
            System.out.println("[q] Exit");
            System.out.print("Masukan Barang: ");
            String input = System.console().readLine();

            switch (input.toLowerCase()) {
                case "p" -> {
                    Pembayaran.show();
                }
                case "q" -> {
                    System.out.print("\033\143");
                    break OUTER;
                }
                case "l" ->
                    Keranjang.show();
                default -> {
                    try {
                        int index = Integer.parseInt(input);
                        Keranjang.addKeranjang(index - 1, bc);
                    } catch (NumberFormatException e) {
                        messages = "Invalid Input";
                    }
                }
            }

        }
    }

    public static void clear() {
        Keranjang.listKeranjang.clear();
        Keranjang.totalPrice = 0;
        Keranjang.discountPrice = 0;
        messages = null;
    }
}
