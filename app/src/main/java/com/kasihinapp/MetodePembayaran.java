package com.kasihinapp;

public class MetodePembayaran {
    private int logoResId;
    private String nama;
    private String deskripsi;

    public MetodePembayaran(int logoResId, String nama, String deskripsi) {
        this.logoResId = logoResId;
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    public int getLogoResId() { return logoResId; }
    public String getNama() { return nama; }
    public String getDeskripsi() { return deskripsi; }
}