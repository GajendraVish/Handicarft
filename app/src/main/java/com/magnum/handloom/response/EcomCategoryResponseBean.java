package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EcomCategoryResponseBean implements Serializable{

    @SerializedName("error")
    private String error;

    @SerializedName("msg")
    private String msg;

    @SerializedName("categorie_info")
    private List<CategorieEcomInfo> categorieInfo;

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

    public List<CategorieEcomInfo> getCategorieInfo() {
        return categorieInfo;
    }

    public void setCategorieInfo(List<CategorieEcomInfo> categorieInfo) {
        this.categorieInfo = categorieInfo;
    }
}
