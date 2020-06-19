package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubmitAssignment implements Serializable {

@SerializedName("submitted")
@Expose
private Boolean submitted;
@SerializedName("org_id")
@Expose
private String org_id;
@SerializedName("user_id")
@Expose
private String user_id;
@SerializedName("assignment_id")
@Expose
private String assignment_id;
@SerializedName("course_id")
@Expose
private String course_id;
@SerializedName("attachment_type")
@Expose
private Integer attachment_type;
@SerializedName("content_name")
@Expose
private Object content_name;
@SerializedName("content_url")
@Expose
private Object content_url;
@SerializedName("description")
@Expose
private String description;

    public SubmitAssignment(Boolean submitted, String org_id, String user_id, String assignment_id, String course_id, Integer attachment_type, Object content_name, Object content_url, String description) {
        this.submitted = submitted;
        this.org_id = org_id;
        this.user_id = user_id;
        this.assignment_id = assignment_id;
        this.course_id = course_id;
        this.attachment_type = attachment_type;
        this.content_name = content_name;
        this.content_url = content_url;
        this.description = description;
    }

    public Boolean getSubmitted() {
return submitted;
}

public void setSubmitted(Boolean submitted) {
this.submitted = submitted;
}

public String getOrg_id() {
return org_id;
}

public void setOrg_id(String org_id) {
this.org_id = org_id;
}

public String getUser_id() {
return user_id;
}

public void setUser_id(String user_id) {
this.user_id = user_id;
}

public String getAssignment_id() {
return assignment_id;
}

public void setAssignment_id(String assignment_id) {
this.assignment_id = assignment_id;
}

public String getCourse_id() {
return course_id;
}

public void setCourse_id(String course_id) {
this.course_id = course_id;
}

public Integer getAttachment_type() {
return attachment_type;
}

public void setAttachment_type(Integer attachment_type) {
this.attachment_type = attachment_type;
}

public Object getContent_name() {
return content_name;
}

public void setContent_name(Object content_name) {
this.content_name = content_name;
}

public Object getContent_url() {
return content_url;
}

public void setContent_url(Object content_url) {
this.content_url = content_url;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

}