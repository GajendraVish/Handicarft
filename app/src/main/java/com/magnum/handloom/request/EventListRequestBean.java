package com.magnum.handloom.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventListRequestBean implements Serializable {

    @SerializedName("user_id")
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
