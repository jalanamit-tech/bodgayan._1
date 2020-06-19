package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CourseDatum  implements Serializable{

    @SerializedName("Bucket_ID")
    @Expose
    private String bucket_ID;
    @SerializedName("BucketName")
    @Expose
    private String bucketName;
    @SerializedName("Child_Link")
    @Expose
    private String child_Link;
    @SerializedName("Total_Video")
    @Expose
    private String total_Video;
    @SerializedName("Total_Document")
    @Expose
    private Integer total_Document;
    @SerializedName("Bucket_Image")
    @Expose
    private String bucket_Image;

    public String getBucket_ID() {
        return bucket_ID;
    }

    public void setBucket_ID(String bucket_ID) {
        this.bucket_ID = bucket_ID;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getChild_Link() {
        return child_Link;
    }

    public void setChild_Link(String child_Link) {
        this.child_Link = child_Link;
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

    public String getBucket_Image() {
        return bucket_Image;
    }

    public void setBucket_Image(String bucket_Image) {
        this.bucket_Image = bucket_Image;
    }

}