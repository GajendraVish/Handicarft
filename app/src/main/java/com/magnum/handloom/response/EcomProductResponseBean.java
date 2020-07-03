package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EcomProductResponseBean implements Serializable{

    @SerializedName("error")
    private String error;

    @SerializedName("msg")
    private String msg;

    @SerializedName("product_info")
    private List<Product> categorieInfo;

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

    public List<Product> getCategorieInfo() {
        return categorieInfo;
    }

    public void setCategorieInfo(List<Product> categorieInfo) {
        this.categorieInfo = categorieInfo;
    }
}
