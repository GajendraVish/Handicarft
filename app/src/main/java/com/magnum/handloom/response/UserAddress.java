package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserAddress implements Serializable {

    @SerializedName("users_address_id")
    private String users_address_id;
    @SerializedName("users_address_users_id")
    private String users_address_users_id;
    @SerializedName("users_address_name")
    private String users_address_name;
    @SerializedName("users_address_mobile")
    private String users_address_mobile;
    @SerializedName("users_address_state")
    private String users_address_state;
    @SerializedName("users_address_city")
    private String users_address_city;
    @SerializedName("users_address_area")
    private String users_address_area;
    @SerializedName("users_address_zip_code")
    private String users_address_zip_code;
    @SerializedName("users_address_landmark")
    private String users_address_landmark;
    @SerializedName("users_address_status")
    private String users_address_status;
    boolean isSelected;




    public String getUsers_address_id() {
        return users_address_id;
    }

    public void setUsers_address_id(String users_address_id) {
        this.users_address_id = users_address_id;
    }

    public String getUsers_address_users_id() {
        return users_address_users_id;
    }

    public void setUsers_address_users_id(String users_address_users_id) {
        this.users_address_users_id = users_address_users_id;
    }

    public String getUsers_address_name() {
        return users_address_name;
    }

    public void setUsers_address_name(String users_address_name) {
        this.users_address_name = users_address_name;
    }

    public String getUsers_address_mobile() {
        return users_address_mobile;
    }

    public void setUsers_address_mobile(String users_address_mobile) {
        this.users_address_mobile = users_address_mobile;
    }

    public String getUsers_address_state() {
        return users_address_state;
    }

    public void setUsers_address_state(String users_address_state) {
        this.users_address_state = users_address_state;
    }

    public String getUsers_address_city() {
        return users_address_city;
    }

    public void setUsers_address_city(String users_address_city) {
        this.users_address_city = users_address_city;
    }

    public String getUsers_address_area() {
        return users_address_area;
    }

    public void setUsers_address_area(String users_address_area) {
        this.users_address_area = users_address_area;
    }

    public String getUsers_address_zip_code() {
        return users_address_zip_code;
    }

    public void setUsers_address_zip_code(String users_address_zip_code) {
        this.users_address_zip_code = users_address_zip_code;
    }

    public String getUsers_address_landmark() {
        return users_address_landmark;
    }

    public void setUsers_address_landmark(String users_address_landmark) {
        this.users_address_landmark = users_address_landmark;
    }

    public String getUsers_address_status() {
        return users_address_status;
    }

    public void setUsers_address_status(String users_address_status) {
        this.users_address_status = users_address_status;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
