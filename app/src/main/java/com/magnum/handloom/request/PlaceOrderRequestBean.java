package com.magnum.handloom.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlaceOrderRequestBean implements Serializable {

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("address_id")
    private String address_id;

    @SerializedName("products_id")
    private String products_id;

    @SerializedName("products_qty")
    private String products_qty;

    @SerializedName("products_amount")
    private String products_amount;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getProducts_qty() {
        return products_qty;
    }

    public void setProducts_qty(String products_qty) {
        this.products_qty = products_qty;
    }

    public String getProducts_amount() {
        return products_amount;
    }

    public void setProducts_amount(String products_amount) {
        this.products_amount = products_amount;
    }
}

