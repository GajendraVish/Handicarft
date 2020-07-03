package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Articaletails {

    String error;
    String msg;
    @SerializedName("articals_info")
    private ArticalInfo articals_info;

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

    public ArticalInfo getArticals_info() {
        return articals_info;
    }

    public void setArticals_info(ArticalInfo articals_info) {
        this.articals_info = articals_info;
    }
}
