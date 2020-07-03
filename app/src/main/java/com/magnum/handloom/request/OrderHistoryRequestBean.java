package com.magnum.handloom.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderHistoryRequestBean  implements Serializable {

    @SerializedName("user_id")
    private String user_id;
    @SerializedName("page_no")
    private String page_no;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }
}
