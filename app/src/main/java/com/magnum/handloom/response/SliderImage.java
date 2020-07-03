package com.magnum.handloom.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderImage {

    String error;
    String msg;
    @SerializedName("data")
    private List<SliderImageResponse> imagedata;


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

    public List<SliderImageResponse> getImagedata() {
        return imagedata;
    }

    public void setImagedata(List<SliderImageResponse> imagedata) {
        this.imagedata = imagedata;
    }
}
