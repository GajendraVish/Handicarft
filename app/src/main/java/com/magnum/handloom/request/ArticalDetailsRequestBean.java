package com.magnum.handloom.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticalDetailsRequestBean {

    @SerializedName("artical_id")
    private String artical_id;

    public String getArtical_id() {
        return artical_id;
    }

    public void setArtical_id(String artical_id) {
        this.artical_id = artical_id;
    }
}
