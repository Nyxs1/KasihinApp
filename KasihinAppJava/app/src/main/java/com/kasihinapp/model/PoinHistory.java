package com.kasihinapp.model;

import java.io.Serializable;

public class PoinHistory implements Serializable {

    private int id;
    private int userId;
    private String sumber;
    private int jumlah;
    private String tanggal; // timestamp

    public PoinHistory() {}

    public PoinHistory(int id, int userId, String sumber, int jumlah, String tanggal) {
        this.id = id;
        this.userId = userId;
        this.sumber = sumber;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getSumber() { return sumber; }
    public void setSumber(String sumber) { this.sumber = sumber; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    @Override
    public String toString() {
        return "PoinHistory{" +
                "id=" + id +
                ", userId=" + userId +
                ", sumber='" + sumber + '\'' +
                ", jumlah=" + jumlah +
                ", tanggal='" + tanggal + '\'' +
                '}';
    }
}
