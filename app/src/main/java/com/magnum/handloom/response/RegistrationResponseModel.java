package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponseModel {

    @SerializedName("error")
    private String error;

    @SerializedName("msg")
    private String msg;

    @SerializedName("user_info")
    private UserInformation userBean ;

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

    public UserInformation getUserBean() {
        return userBean;
    }

    public void setUserBean(UserInformation userBean) {
        this.userBean = userBean;
    }
}

