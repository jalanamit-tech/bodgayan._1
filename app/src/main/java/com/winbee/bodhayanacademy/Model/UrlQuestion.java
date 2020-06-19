package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UrlQuestion implements Serializable {

    @SerializedName("file_name")
    @Expose
    private String file_name;
    @SerializedName("file_name_to_show")
    @Expose
    private String file_name_to_show;
    @SerializedName("DocumentId")
    @Expose
    private String documentId;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_name_to_show() {
        return file_name_to_show;
    }

    public void setFile_name_to_show(String file_name_to_show) {
        this.file_name_to_show = file_name_to_show;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

}