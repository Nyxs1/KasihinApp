package com.kasihinapp.model;

import java.io.Serializable;

public class Quest implements Serializable {

    private int id;
    private String nama;
    private String deskripsi;
    private String jenis; // enum: blog/video
    private String deadline; // date
    private int poinReward;
    private String createdAt;

    public Quest() {}

    public Quest(int id, String nama, String deskripsi, String jenis, String deadline, int poinReward, String createdAt) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.jenis = jenis;
        this.deadline = deadline;
        this.poinReward = poinReward;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }

    public int getPoinReward() { return poinReward; }
    public void setPoinReward(int poinReward) { this.poinReward = poinReward; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", jenis='" + jenis + '\'' +
                ", deadline='" + deadline + '\'' +
                ", poinReward=" + poinReward +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
