package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DummyModel implements Serializable {

@SerializedName("OrderID")
@Expose
private String orderID;
@SerializedName("PaymentID")
@Expose
private String paymentID;
@SerializedName("Success")
@Expose
private Boolean success;
@SerializedName("Message")
@Expose
private String message;

public String getOrderID() {
return orderID;
}

public void setOrderID(String orderID) {
this.orderID = orderID;
}

public String getPaymentID() {
return paymentID;
}

public void setPaymentID(String paymentID) {
this.paymentID = paymentID;
}

public Boolean getSuccess() {
return success;
}

public void setSuccess(Boolean success) {
this.success = success;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}