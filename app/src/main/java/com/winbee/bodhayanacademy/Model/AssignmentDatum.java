package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentDatum implements Serializable {

    @SerializedName("assignment_id")
    @Expose
    private String assignment_id;
    @SerializedName("content_name")
    @Expose
    private String content_name;
    @SerializedName("bucketId")
    @Expose
    private String bucketId;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("faculty")
    @Expose
    private String faculty;
    @SerializedName("start_date")
    @Expose
    private String start_date;
    @SerializedName("deadline_date")
    @Expose
    private String deadline_date;


    public AssignmentDatum(String assignment_id, String content_name, String bucketId, String subject, String topic, String faculty, String start_date, String deadline_date) {
        this.assignment_id = assignment_id;
        this.content_name = content_name;
        this.bucketId = bucketId;
        this.subject = subject;
        this.topic = topic;
        this.faculty = faculty;
        this.start_date = start_date;
        this.deadline_date = deadline_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

}