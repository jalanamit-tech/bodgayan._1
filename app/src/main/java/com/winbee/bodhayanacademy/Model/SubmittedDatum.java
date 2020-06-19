package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubmittedDatum implements Serializable {
    @SerializedName("Assignment_id")
    @Expose
    private String assignment_id;
    @SerializedName("TeacherContent")
    @Expose
    private String teacherContent;
    @SerializedName("StudentContent")
    @Expose
    private String studentContent;
    @SerializedName("BucketId")
    @Expose
    private String bucketId;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Topic")
    @Expose
    private String topic;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("IsDead")
    @Expose
    private String isDead;
    @SerializedName("DeadDate")
    @Expose
    private String deadDate;

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getTeacherContent() {
        return teacherContent;
    }

    public void setTeacherContent(String teacherContent) {
        this.teacherContent = teacherContent;
    }

    public String getStudentContent() {
        return studentContent;
    }

    public void setStudentContent(String studentContent) {
        this.studentContent = studentContent;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsDead() {
        return isDead;
    }

    public void setIsDead(String isDead) {
        this.isDead = isDead;
    }

    public String getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(String deadDate) {
        this.deadDate = deadDate;
    }

}