package com.kasihin.model;

import java.io.Serializable;

public class Donation implements Serializable {

    private int id;
    private int dariUserId;     // ID user yang mendonasi
    private int keCampaignId;   // ID campaign yang menerima
    private int jumlah;         // Jumlah poin yang didonasikan
    private String tanggal;     // Timestamp dari database

    // Constructor kosong
    public Donation() {}

    // Constructor lengkap
    public Donation(int id, int dariUserId, int keCampaignId, int jumlah, String tanggal) {
        this.id = id;
        this.dariUserId = dariUserId;
        this.keCampaignId = keCampaignId;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    // Getter & Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDariUserId() {
        return dariUserId;
    }

    public void setDariUserId(int dariUserId) {
        this.dariUserId = dariUserId;
    }

    public int getKeCampaignId() {
        return keCampaignId;
    }

    public void setKeCampaignId(int keCampaignId) {
        this.keCampaignId = keCampaignId;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", dariUserId=" + dariUserId +
                ", keCampaignId=" + keCampaignId +
                ", jumlah=" + jumlah +
                ", tanggal='" + tanggal + '\'' +
                '}';
    }
}

