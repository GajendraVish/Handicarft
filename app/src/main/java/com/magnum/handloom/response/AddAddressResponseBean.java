package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddAddressResponseBean implements Serializable {
    @SerializedName("error")
    private String error;
    @SerializedName("msg")
    private String msg;

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
}
