package com.kasihin.model;

import java.io.Serializable;

public class Campaign implements Serializable {

    private int id;
    private String namaCampaign;
    private String deskripsi;
    private String tipe;       // 'non-profit' atau 'perusahaan'
    private String gambar;     // URL/filename dari gambar campaign
    private int target;        // Target donasi (dalam poin)
    private int terkumpul;     // Donasi yang sudah masuk (dalam poin)
    private int userId;        // ID user pembuat campaign
    private String createdAt;  // Timestamp dari database

    // Constructor kosong
    public Campaign() {}

    // Constructor lengkap
    public Campaign(int id, String namaCampaign, String deskripsi, String tipe,
                    String gambar, int target, int terkumpul, int userId, String createdAt) {
        this.id = id;
        this.namaCampaign = namaCampaign;
        this.deskripsi = deskripsi;
        this.tipe = tipe;
        this.gambar = gambar;
        this.target = target;
        this.terkumpul = terkumpul;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    // Getter & Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaCampaign() {
        return namaCampaign;
    }

    public void setNamaCampaign(String namaCampaign) {
        this.namaCampaign = namaCampaign;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTerkumpul() {
        return terkumpul;
    }

    public void setTerkumpul(int terkumpul) {
        this.terkumpul = terkumpul;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", namaCampaign='" + namaCampaign + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", tipe='" + tipe + '\'' +
                ", gambar='" + gambar + '\'' +
                ", target=" + target +
                ", terkumpul=" + terkumpul +
                ", userId=" + userId +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
