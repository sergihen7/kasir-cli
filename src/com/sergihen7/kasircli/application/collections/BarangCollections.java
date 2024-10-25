package com.sergihen7.kasircli.application.collections;

import com.sergihen7.kasircli.models.Barang;
import java.util.ArrayList;
import java.util.List;

public class BarangCollections {

    public List<Barang> listBarang;

    public BarangCollections() {
        this.listBarang = new ArrayList<>();
        this.listBarang.add(new Barang(1, "Buku", 10000));
        this.listBarang.add(new Barang(2, "Pensil", 5000));
        this.listBarang.add(new Barang(3, "Penghapus", 3000));
        this.listBarang.add(new Barang(4, "Penggaris", 2000));
        this.listBarang.add(new Barang(5, "Spidol", 3000));
        this.listBarang.add(new Barang(6, "Jangka", 2000));
    }

    public List<Barang> getAllBarang() {
        return this.listBarang;
    }

    public void showBarang() {
        int i = 1;

        String format = "| %-3s | %-15s | %-10s |%n";
        System.out.format("+=====+=================+============+%n");
        for (Barang b : this.listBarang) {
            System.out.format(format, i++, b.name, b.price);
        }
        System.out.format("+=====+=================+============+%n");
    }

}
