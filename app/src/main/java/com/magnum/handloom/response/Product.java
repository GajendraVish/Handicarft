package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

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
    private double ecomm_product_price;
    @SerializedName("ecomm_product_offer_price")
    private double ecomm_product_offer_price;
    @SerializedName("ecomm_product_added_by")
    private String ecomm_product_added_by;
    @SerializedName("ecomm_product_status")
    private String ecomm_product_status;

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

    public double getEcomm_product_price() {
        return ecomm_product_price;
    }

    public void setEcomm_product_price(double ecomm_product_price) {
        this.ecomm_product_price = ecomm_product_price;
    }

    public double getEcomm_product_offer_price() {
        return ecomm_product_offer_price;
    }

    public void setEcomm_product_offer_price(double ecomm_product_offer_price) {
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