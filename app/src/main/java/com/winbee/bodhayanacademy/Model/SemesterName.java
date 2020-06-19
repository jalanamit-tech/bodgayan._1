package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SemesterName implements Serializable {

    @SerializedName("Bucket_ID")
    @Expose
    private String bucket_ID;
    @SerializedName("Bucket_Name")
    @Expose
    private String bucket_Name;
    @SerializedName("Total_Video")
    @Expose
    private String total_Video;
    @SerializedName("Total_Document")
    @Expose
    private Integer total_Document;
    @SerializedName("Child_Link")
    @Expose
    private String child_Link;

    public String getBucket_ID() {
        return bucket_ID;
    }

    public void setBucket_ID(String bucket_ID) {
        this.bucket_ID = bucket_ID;
    }

    public String getBucket_Name() {
        return bucket_Name;
    }

    public void setBucket_Name(String bucket_Name) {
        this.bucket_Name = bucket_Name;
    }

    public String getTotal_Video() {
        return total_Video;
    }

    public void setTotal_Video(String total_Video) {
        this.total_Video = total_Video;
    }

    public Integer getTotal_Document() {
        return total_Document;
    }

    public void setTotal_Document(Integer total_Document) {
        this.total_Document = total_Document;
    }

    public String getChild_Link() {
        return child_Link;
    }

    public void setChild_Link(String child_Link) {
        this.child_Link = child_Link;
    }

}