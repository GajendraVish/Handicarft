package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategorieEcomInfo implements Serializable {

    @SerializedName("ecomm_category_id")
    private String ecomm_category_id;
    @SerializedName("ecomm_category_name")
    private String ecomm_category_name;
    @SerializedName("ecomm_category_added_by")
    private String ecomm_category_added_by;
    @SerializedName("ecomm_category_status")
    private String ecomm_category_status;

    public String getEcomm_category_id() {
        return ecomm_category_id;
    }

    public void setEcomm_category_id(String ecomm_category_id) {
        this.ecomm_category_id = ecomm_category_id;
    }

    public String getEcomm_category_name() {
        return ecomm_category_name;
    }

    public void setEcomm_category_name(String ecomm_category_name) {
        this.ecomm_category_name = ecomm_category_name;
    }

    public String getEcomm_category_added_by() {
        return ecomm_category_added_by;
    }

    public void setEcomm_category_added_by(String ecomm_category_added_by) {
        this.ecomm_category_added_by = ecomm_category_added_by;
    }

    public String getEcomm_category_status() {
        return ecomm_category_status;
    }

    public void setEcomm_category_status(String ecomm_category_status) {
        this.ecomm_category_status = ecomm_category_status;
    }
}
