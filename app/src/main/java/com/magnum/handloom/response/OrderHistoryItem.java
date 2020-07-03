package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderHistoryItem implements Serializable {

    @SerializedName("ecomm_order_item_id")
    private String ecomm_order_item_id;
    @SerializedName("ecomm_order_order_id")
    private String ecomm_order_order_id;
    @SerializedName("ecomm_order_item_product_id")
    private String ecomm_order_item_product_id;
    @SerializedName("ecomm_order_item_qty")
    private String ecomm_order_item_qty;
    @SerializedName("ecomm_order_item_amount")
    private String ecomm_order_item_amount;
    @SerializedName("ecomm_order_item_date")
    private String ecomm_order_item_date;
    @SerializedName("ecomm_product_id")
    private String ecomm_product_id;
    @SerializedName("ecomm_product_category_id")
    private String ecomm_product_category_id;
    @SerializedName("ecomm_product_name")
    private String ecomm_product_name;
    @SerializedName("ecomm_product_description")
    private String ecomm_product_description;
    @SerializedName("ecomm_product_image1")
    private String ecomm_product_image1;
    @SerializedName("ecomm_product_image2")
    private String ecomm_product_image2;
    @SerializedName("ecomm_product_image3")
    private String ecomm_product_image3;
    @SerializedName("ecomm_product_image4")
    private String ecomm_product_image4;
    @SerializedName("ecomm_product_image5")
    private String ecomm_product_image5;
    @SerializedName("ecomm_product_price")
    private String ecomm_product_price;
    @SerializedName("ecomm_product_offer_price")
    private String ecomm_product_offer_price;
    @SerializedName("ecomm_product_added_by")
    private String ecomm_product_added_by;
    @SerializedName("ecomm_product_status")
    private String ecomm_product_status;

    public String getEcomm_order_item_id() {
        return ecomm_order_item_id;
    }

    public void setEcomm_order_item_id(String ecomm_order_item_id) {
        this.ecomm_order_item_id = ecomm_order_item_id;
    }

    public String getEcomm_order_order_id() {
        return ecomm_order_order_id;
    }

    public void setEcomm_order_order_id(String ecomm_order_order_id) {
        this.ecomm_order_order_id = ecomm_order_order_id;
    }

    public String getEcomm_order_item_product_id() {
        return ecomm_order_item_product_id;
    }

    public void setEcomm_order_item_product_id(String ecomm_order_item_product_id) {
        this.ecomm_order_item_product_id = ecomm_order_item_product_id;
    }

    public String getEcomm_order_item_qty() {
        return ecomm_order_item_qty;
    }

    public void setEcomm_order_item_qty(String ecomm_order_item_qty) {
        this.ecomm_order_item_qty = ecomm_order_item_qty;
    }

    public String getEcomm_order_item_amount() {
        return ecomm_order_item_amount;
    }

    public void setEcomm_order_item_amount(String ecomm_order_item_amount) {
        this.ecomm_order_item_amount = ecomm_order_item_amount;
    }

    public String getEcomm_order_item_date() {
        return ecomm_order_item_date;
    }

    public void setEcomm_order_item_date(String ecomm_order_item_date) {
        this.ecomm_order_item_date = ecomm_order_item_date;
    }

    public String getEcomm_product_id() {
        return ecomm_product_id;
    }

    public void setEcomm_product_id(String ecomm_product_id) {
        this.ecomm_product_id = ecomm_product_id;
    }

    public String getEcomm_product_category_id() {
        return ecomm_product_category_id;
    }

    public void setEcomm_product_category_id(String ecomm_product_category_id) {
        this.ecomm_product_category_id = ecomm_product_category_id;
    }

    public String getEcomm_product_name() {
        return ecomm_product_name;
    }

    public void setEcomm_product_name(String ecomm_product_name) {
        this.ecomm_product_name = ecomm_product_name;
    }

    public String getEcomm_product_description() {
        return ecomm_product_description;
    }

    public void setEcomm_product_description(String ecomm_product_description) {
        this.ecomm_product_description = ecomm_product_description;
    }

    public String getEcomm_product_image1() {
        return ecomm_product_image1;
    }

    public void setEcomm_product_image1(String ecomm_product_image1) {
        this.ecomm_product_image1 = ecomm_product_image1;
    }

    public String getEcomm_product_image2() {
        return ecomm_product_image2;
    }

    public void setEcomm_product_image2(String ecomm_product_image2) {
        this.ecomm_product_image2 = ecomm_product_image2;
    }

    public String getEcomm_product_image3() {
        return ecomm_product_image3;
    }

    public void setEcomm_product_image3(String ecomm_product_image3) {
        this.ecomm_product_image3 = ecomm_product_image3;
    }

    public String getEcomm_product_image4() {
        return ecomm_product_image4;
    }

    public void setEcomm_product_image4(String ecomm_product_image4) {
        this.ecomm_product_image4 = ecomm_product_image4;
    }

    public String getEcomm_product_image5() {
        return ecomm_product_image5;
    }

    public void setEcomm_product_image5(String ecomm_product_image5) {
        this.ecomm_product_image5 = ecomm_product_image5;
    }

    public String getEcomm_product_price() {
        return ecomm_product_price;
    }

    public void setEcomm_product_price(String ecomm_product_price) {
        this.ecomm_product_price = ecomm_product_price;
    }

    public String getEcomm_product_offer_price() {
        return ecomm_product_offer_price;
    }

    public void setEcomm_product_offer_price(String ecomm_product_offer_price) {
        this.ecomm_product_offer_price = ecomm_product_offer_price;
    }

    public String getEcomm_product_added_by() {
        return ecomm_product_added_by;
    }

    public void setEcomm_product_added_by(String ecomm_product_added_by) {
        this.ecomm_product_added_by = ecomm_product_added_by;
    }

    public String getEcomm_product_status() {
        return ecomm_product_status;
    }

    public void setEcomm_product_status(String ecomm_product_status) {
        this.ecomm_product_status = ecomm_product_status;
    }
}

