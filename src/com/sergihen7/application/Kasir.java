package com.sergihen7.application;

import com.sergihen7.application.collections.BarangCollections;
import com.sergihen7.models.Barang;
import java.util.ArrayList;
import java.util.List;
import com.sergihen7.ext.KalkuKasir;

public class Kasir {

    private static List<Barang> listKeranjang;
    private static double totalPrice = 0;

    public static String format = "| %-51s |%n";

    public static String messages;

    public static void start() {

        Kasir.listKeranjang = new ArrayList<>();

        OUTER:
        while (true) {
            System.out.print("\033\143");

            if (messages != null) {
                System.out.format("+=====================================================+%n");
                System.out.format(format, messages);
                System.out.format("+=====================================================+%n");
            }
            messages = null;

            System.out.println("\nList Barang :");

            BarangCollections bc = new BarangCollections();
            bc.showBarang();

            System.out.println("[l] Lihat Keranjang");
            System.out.println("[p] Pembayaran");
            System.out.println("[e] Exit");
            System.out.print("Masukan Barang: ");
            String input = System.console().readLine();

            switch (input) {
                case "p" -> {
                    bayarPage();
                }
                case "P" -> {
                    bayarPage();
                }
                case "E" -> {
                    System.out.print("\033\143");
                    break OUTER;
                }
                case "e" -> {
                    System.out.print("\033\143");
                    break OUTER;
                }
                case "L" ->
                    Kasir.keranjangPage();
                case "l" ->
                    Kasir.keranjangPage();
                default -> {
                    try {
                        int index = Integer.parseInt(input);
                        _addKeranjang(index - 1, bc);
                    } catch (NumberFormatException e) {
                        messages = messages = "Invalid Input";
                    }
                }
            }

        }
    }

    public static void keranjangPage() {
        while (true) {
            System.out.print("\033\143");
            System.out.println("\nKeranjang :");

            _showKeranjang();

            System.out.println("\nPress Enter to back...");
            System.console().readLine();
            break;
        }

    }

    public static void _showKeranjang() {
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
        System.out.format(format, " ", "Total: ", totalPrice);
        System.out.format("+=====+=================+============+%n");
    }

    public static void _addKeranjang(int i, BarangCollections bc) {
        if (bc.listBarang.size() > i && i >= 0) {
            Barang b = bc.listBarang.get(i);

            totalPrice += b.price;
            listKeranjang.add(b);
            messages = "Barang " + b.name + " Ditambahkan Ke Keranjang";
        } else {
            messages = "Barang Tidak Tersedia";
        }
    }

    public static void bayarPage() {
        while (true) {
            System.out.print("\033\143");

            if (messages != null) {
                System.out.format("+=====================================================+%n");
                System.out.format(format, messages);
                System.out.format("+=====================================================+%n");
            }
            messages = null;

            _showKeranjang();
            System.out.print("Diskon: ");
            String diskon = System.console().readLine();

            System.out.print("Bayar: ");
            String uang = System.console().readLine();

            try {
                double bayar = Double.parseDouble(uang);
                double potongan = Double.parseDouble(diskon);
                KalkuKasir kalkukasir = new KalkuKasir();

                kalkukasir.setPrice(totalPrice);
                kalkukasir.setDiscount(potongan);
                kalkukasir.pay(bayar);

                double kembalian = kalkukasir.getResult();

                if (kembalian < 0) {
                    messages = "Uang Anda Kurang: " + kembalian;
                } else {
                    String leftAlignFormat = "| %-21s | %-13s |%n";
                    System.out.format("+=======================+===============+%n");

                    System.out.format(leftAlignFormat, "Total", "Rp " + totalPrice);
                    System.out.format(leftAlignFormat, "Diskon", potongan + "%");
                    System.out.format(leftAlignFormat, "Bayar", "Rp " + bayar);
                    System.out.format(leftAlignFormat, "Kembalian", "Rp " + kembalian);

                    System.out.format("+=======================+===============+%n");

                    System.out.print("\nPress enter to continue... ");
                    System.console().readLine();
                    clear();
                    break;
                }

            } catch (NumberFormatException e) {
                messages = "Invalid Input";
            }
        }
    }

    public static void clear() {
        listKeranjang.clear();
        totalPrice = 0;
        messages = null;
    }
}
