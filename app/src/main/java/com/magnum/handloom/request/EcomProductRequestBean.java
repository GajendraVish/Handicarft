package com.magnum.handloom.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EcomProductRequestBean implements Serializable {

    @SerializedName("user_id")
    private String user_id;
    @SerializedName("category_id")
    private String category_id;
    @SerializedName("page_no")
    private int page_no;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getPage_no() {
        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }
}
