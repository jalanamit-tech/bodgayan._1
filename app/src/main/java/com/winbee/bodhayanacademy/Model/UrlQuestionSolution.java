package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UrlQuestionSolution implements Serializable {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("documentId")
    @Expose
    private String documentId;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}