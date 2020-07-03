package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderHistoryResponseBean implements Serializable {

    @SerializedName("error")
    private String error;

    @SerializedName("msg")
    private String msg;

    @SerializedName("order_history")
    private List<OrderHistory> order_history;

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

    public List<OrderHistory> getOrder_history() {
        return order_history;
    }

    public void setOrder_history(List<OrderHistory> order_history) {
        this.order_history = order_history;
    }
}

