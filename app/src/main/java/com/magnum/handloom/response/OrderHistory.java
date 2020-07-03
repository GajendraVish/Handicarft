package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderHistory implements Serializable {

    @SerializedName("order_id")
    private String order_id;

    @SerializedName("order_item")
    private List<OrderHistoryItem> orderHistoryItems;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public List<OrderHistoryItem> getOrderHistoryItems() {
        return orderHistoryItems;
    }

    public void setOrderHistoryItems(List<OrderHistoryItem> orderHistoryItems) {
        this.orderHistoryItems = orderHistoryItems;
    }
}
