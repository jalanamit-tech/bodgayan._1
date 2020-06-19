package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UrlNewQuestion implements Serializable {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("question")
    @Expose
    private String question;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
//public class UrlNewQuestion implements Serializable {
//
//    @SerializedName("title")
//    @Expose
//    private String title;
//    @SerializedName("question")
//    @Expose
//    private String question;
//    @SerializedName("userid")
//    @Expose
//    private String userid;
//    @SerializedName("DocumentId")
//    @Expose
//    private String documentId;
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(String question) {
//        this.question = question;
//    }
//
//    public String getUserid() {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }
//
//    public String getDocumentId() {
//        return documentId;
//    }
//
//    public void setDocumentId(String documentId) {
//        this.documentId = documentId;
//    }
//
//}