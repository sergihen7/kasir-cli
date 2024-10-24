package com.sergihen7.application;

public class Kalkulasi {

    private double total = 0f;
    private double diskon = 0f;
    private double bayar = 0f;

    public Kalkulasi total(double total) {
        this.total = total;
        return this;
    }

    public Kalkulasi diskon(double diskon) {
        this.diskon = diskon;
        return this;
    }

    public Kalkulasi bayar(double bayar) {
        this.bayar = bayar;
        return this;
    }

    public double result() {
        double pembayaran = total - (total * (diskon / 100));
        double kembalian = bayar - pembayaran;

        return kembalian;
    }
}
