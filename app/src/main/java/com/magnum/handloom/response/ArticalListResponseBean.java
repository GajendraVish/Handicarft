package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticalListResponseBean {


    String error;
    String msg;
    @SerializedName("articals")
    private List<ArticalInfo> articals;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ArticalInfo> getArticals() {
        return articals;
    }

    public void setArticals(List<ArticalInfo> articals) {
        this.articals = articals;
    }
}
