package com.winbee.bodhayanacademy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class AfterPaymentModel implements Serializable {


        @SerializedName("submitted")
        @Expose
        private Boolean submitted;
        @SerializedName("org_id")
        @Expose
        private String org_id;
        @SerializedName("user_id")
        @Expose
        private String user_id;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("course_id")
        @Expose
        private String course_id;
        @SerializedName("org_order_id")
        @Expose
        private String org_order_id;
        @SerializedName("gt_order_id")
        @Expose
        private String gt_order_id;
        @SerializedName("gt_payment_id")
        @Expose
        private String gt_payment_id;
        @SerializedName("subscription_tenure")
        @Expose
        private Integer subscription_tenure;
        @SerializedName("access_mode")
        @Expose
        private Integer access_mode;
        @SerializedName("subscription_start")
        @Expose
        private String subscription_start;
        @SerializedName("Message")
        @Expose
        private String message;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getOrg_order_id() {
            return org_order_id;
        }

        public void setOrg_order_id(String org_order_id) {
            this.org_order_id = org_order_id;
        }

        public String getGt_order_id() {
            return gt_order_id;
        }

        public void setGt_order_id(String gt_order_id) {
            this.gt_order_id = gt_order_id;
        }

        public String getGt_payment_id() {
            return gt_payment_id;
        }

        public void setGt_payment_id(String gt_payment_id) {
            this.gt_payment_id = gt_payment_id;
        }

        public Integer getSubscription_tenure() {
            return subscription_tenure;
        }

        public void setSubscription_tenure(Integer subscription_tenure) {
            this.subscription_tenure = subscription_tenure;
        }

        public Integer getAccess_mode() {
            return access_mode;
        }

        public void setAccess_mode(Integer access_mode) {
            this.access_mode = access_mode;
        }

        public String getSubscription_start() {
            return subscription_start;
        }

        public void setSubscription_start(String subscription_start) {
            this.subscription_start = subscription_start;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
