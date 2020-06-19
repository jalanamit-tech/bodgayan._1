package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SIACDetailsDataModel {
    @Expose
    @SerializedName("BucketID")
    private String BucketID;
    @Expose
    @SerializedName("PaperID")
    private String PaperID;
    @Expose
    @SerializedName("PaperName")
    private String PaperName;
    @Expose
    @SerializedName("PaperSection_Encode")
    private String PaperSection_Encode;
    @Expose
    @SerializedName("IsNegativeMarking_encode")
    private String IsNegativeMarking_encode;
    @Expose
    @SerializedName("time")
    private String time;
    @Expose
    @SerializedName("IsOpen")
    private String IsOpen;
    @Expose
    @SerializedName("OpenDate")
    private String OpenDate;
    @Expose
    @SerializedName("IsNegativeMarking_decode")
    private String IsNegativeMarking_decode;
    @Expose
    @SerializedName("IsPremium_encode")
    private String IsPremium_encode;
    @Expose
    @SerializedName("IsPremium_decode")
    private String IsPremium_decode;
    @Expose
    @SerializedName("Description")
    private String Description;

    public SIACDetailsDataModel(String bucketID, String paperID, String paperName, String paperSection_Encode, String isNegativeMarking_encode, String time, String isOpen, String openDate, String isNegativeMarking_decode, String isPremium_encode, String isPremium_decode, String description) {
        BucketID = bucketID;
        PaperID = paperID;
        PaperName = paperName;
        PaperSection_Encode = paperSection_Encode;
        IsNegativeMarking_encode = isNegativeMarking_encode;
        this.time = time;
        IsOpen = isOpen;
        OpenDate = openDate;
        IsNegativeMarking_decode = isNegativeMarking_decode;
        IsPremium_encode = isPremium_encode;
        IsPremium_decode = isPremium_decode;
        Description = description;
    }

    public String getBucketID() {
        return BucketID;
    }

    public String getPaperID() {
        return PaperID;
    }

    public String getPaperName() {
        return PaperName;
    }

    public String getPaperSection_Encode() {
        return PaperSection_Encode;
    }

    public String getIsNegativeMarking_encode() {
        return IsNegativeMarking_encode;
    }

    public String getTime() {
        return time;
    }

    public String getIsOpen() {
        return IsOpen;
    }

    public String getOpenDate() {
        return OpenDate;
    }

    public String getIsNegativeMarking_decode() {
        return IsNegativeMarking_decode;
    }

    public String getIsPremium_encode() {
        return IsPremium_encode;
    }

    public String getIsPremium_decode() {
        return IsPremium_decode;
    }

    public String getDescription() {
        return Description;
    }
}
