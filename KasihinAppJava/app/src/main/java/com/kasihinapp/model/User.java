package com.kasihinapp.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String nama;
    private String email;
    private String password;
    private String role; // enum
    private int poin; // default 1000
    private String createdAt; // timestamp or date

    public User() {}

    public User(int id, String nama, String email, String password, String role, int poin, String createdAt) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.role = role;
        this.poin = poin;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getPoin() { return poin; }
    public void setPoin(int poin) { this.poin = poin; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", poin=" + poin +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
