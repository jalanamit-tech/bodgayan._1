package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentToSubmit implements Serializable {

    @SerializedName("Purchased")
    @Expose
    private Integer purchased;
    @SerializedName("Assignment")
    @Expose
    private Integer assignment;

   private AssignmentDatum[] AssignmentData;
    @SerializedName("UserId")
    @Expose
    private String userId;

    public AssignmentToSubmit(Integer purchased, Integer assignment, AssignmentDatum[] assignmentData, String userId) {
        this.purchased = purchased;
        this.assignment = assignment;
        AssignmentData = assignmentData;
        this.userId = userId;
    }

    public Integer getPurchased() {
        return purchased;
    }

    public void setPurchased(Integer purchased) {
        this.purchased = purchased;
    }

    public Integer getAssignment() {
        return assignment;
    }

    public void setAssignment(Integer assignment) {
        this.assignment = assignment;
    }
     public AssignmentDatum[] getData(){return AssignmentData;}
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}