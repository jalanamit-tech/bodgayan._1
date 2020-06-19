package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectionDetailsDataModel {
    @Expose
    @SerializedName("CoachingID")
    private String CoachingID;
    @Expose
    @SerializedName("BucketID")
    private String BucketID;
    @Expose
    @SerializedName("BucketName")
    private String BucketName;
    @Expose
    @SerializedName("BucketInfo")
    private String BucketInfo;
    @Expose
    @SerializedName("LogData")
    private String LogData;
    @Expose
    @SerializedName("Status")
    private String Status;
    @Expose
    @SerializedName("TotalTest")
    private String TotalTest;

    public SectionDetailsDataModel(String coachingID, String bucketID, String bucketName, String bucketInfo, String logData, String status, String totalTest) {
        CoachingID = coachingID;
        BucketID = bucketID;
        BucketName = bucketName;
        BucketInfo = bucketInfo;
        LogData = logData;
        Status = status;
        TotalTest = totalTest;
    }

    public String getCoachingID() {
        return CoachingID;
    }

    public String getBucketID() {
        return BucketID;
    }

    public String getBucketName() {
        return BucketName;
    }

    public String getBucketInfo() {
        return BucketInfo;
    }

    public String getLogData() {
        return LogData;
    }

    public String getStatus() {
        return Status;
    }

    public String getTotalTest() {
        return TotalTest;
    }
}
