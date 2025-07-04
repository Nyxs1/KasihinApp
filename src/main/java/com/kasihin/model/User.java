package com.kasihin.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String nama;
    private String email;
    private String password;
    private String role;      // 'person' (donatur) atau 'influencer'
    private int poin;         // default 1000
    private String createdAt; // format waktu dari database (timestamp)

    // Constructor kosong (wajib untuk parsing JSON atau penggunaan model dinamis)
    public User() {
    }

    // Constructor lengkap
    public User(int id, String nama, String email, String password, String role, int poin, String createdAt) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.role = role;
        this.poin = poin;
        this.createdAt = createdAt;
    }

    // Getter & Setter

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    // Optional: override toString() for debug/logging purposes
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", poin=" + poin +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
