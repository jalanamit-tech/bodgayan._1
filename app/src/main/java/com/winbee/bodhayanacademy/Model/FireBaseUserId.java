package com.winbee.bodhayanacademy.Model;

public class FireBaseUserId {
    String uid;
    String name,email,mobile;

    public FireBaseUserId() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{"+
                "uid='"+ uid +'\''+
                ", name='"+ name + '\''+
                ", email='"+ email +'\''+
                '}';

    }
}
