package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchasedMainModel {

    @SerializedName("User_id")
    @Expose
    private String user_id;
    @SerializedName("Purchased")
    @Expose
    private Boolean purchased;
    @SerializedName("CourseData")
    @Expose
   private CourseDatum[] courseData ;

    public PurchasedMainModel(String user_id, Boolean purchased, CourseDatum[] courseData) {
        this.user_id = user_id;
        this.purchased = purchased;
        this.courseData = courseData;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public CourseDatum[] getData(){return courseData;}





}