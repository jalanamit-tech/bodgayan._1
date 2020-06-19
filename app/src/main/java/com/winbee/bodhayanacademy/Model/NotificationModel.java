package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NotificationModel implements Serializable {

@SerializedName("Sender")
@Expose
private String sender;
@SerializedName("Message")
@Expose
private String message;
@SerializedName("priority")
@Expose
private String priority;
@SerializedName("action")
@Expose
private String action;
@SerializedName("logDetails")
@Expose
private String logDetails;

public String getSender() {
return sender;
}

public void setSender(String sender) {
this.sender = sender;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getPriority() {
return priority;
}

public void setPriority(String priority) {
this.priority = priority;
}

public String getAction() {
return action;
}

public void setAction(String action) {
this.action = action;
}

public String getLogDetails() {
return logDetails;
}

public void setLogDetails(String logDetails) {
this.logDetails = logDetails;
}

}