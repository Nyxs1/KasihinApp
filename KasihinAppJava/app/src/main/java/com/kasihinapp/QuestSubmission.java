package com.kasihin.model;

import java.io.Serializable;

public class QuestSubmission implements Serializable {

    private int id;
    private int userId;
    private int questId;
    private String linkKonten;   // Link artikel atau video dari user
    private String status;       // 'pending', 'approved', 'rejected'
    private String submittedAt;  // waktu kirim konten

    // Constructor kosong
    public QuestSubmission() {}

    // Constructor lengkap
    public QuestSubmission(int id, int userId, int questId, String linkKonten, String status, String submittedAt) {
        this.id = id;
        this.userId = userId;
        this.questId = questId;
        this.linkKonten = linkKonten;
        this.status = status;
        this.submittedAt = submittedAt;
    }

    // Getter & Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public String getLinkKonten() {
        return linkKonten;
    }

    public void setLinkKonten(String linkKonten) {
        this.linkKonten = linkKonten;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }

    @Override
    public String toString() {
        return "QuestSubmission{" +
                "id=" + id +
                ", userId=" + userId +
                ", questId=" + questId +
                ", linkKonten='" + linkKonten + '\'' +
                ", status='" + status + '\'' +
                ", submittedAt='" + submittedAt + '\'' +
                '}';
    }
}
