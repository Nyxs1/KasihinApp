package com.kasihinapp.models; // DIPERBARUI

import com.google.gson.annotations.SerializedName;

/**
 * Ini adalah kelas Model (POJO) untuk merepresentasikan data pengguna.
 * Strukturnya cocok dengan tabel 'users' di database dan respons JSON dari API.
 * Anotasi @SerializedName digunakan oleh GSON/Retrofit untuk mapping.
 */
public class User {

    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    @SerializedName("role")
    private String role;

    @SerializedName("poin")
    private int poin;

    @SerializedName("created_at")
    private String createdAt;

    // Constructor, Getters, dan Setters
    public User(int id, String nama, String email, String role, int poin, String createdAt) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.role = role;
        this.poin = poin;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPoin() {
        return poin;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
