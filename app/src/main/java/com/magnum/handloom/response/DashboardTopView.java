package com.magnum.handloom.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardTopView {

    @SerializedName("artical_id")
    private String artical_id;
    @SerializedName("artical_image")
    private String artical_image;
    @SerializedName("artical_video")
    private String artical_video;
    @SerializedName("artical_text")
    private String artical_text;
    @SerializedName("artical_created_date")
    private String artical_created_date;
    @SerializedName("category_child_name")
    private String category_child_name;
    @SerializedName("users_name")
    private String users_name;
    @SerializedName("artical_view")
    private String artical_view;

    public String getArtical_id() {
        return artical_id;
    }

    public void setArtical_id(String artical_id) {
        this.artical_id = artical_id;
    }

    public String getArtical_image() {
        return artical_image;
    }

    public void setArtical_image(String artical_image) {
        this.artical_image = artical_image;
    }

    public String getArtical_video() {
        return artical_video;
    }

    public void setArtical_video(String artical_video) {
        this.artical_video = artical_video;
    }

    public String getArtical_text() {
        return artical_text;
    }

    public void setArtical_text(String artical_text) {
        this.artical_text = artical_text;
    }

    public String getArtical_created_date() {
        return artical_created_date;
    }

    public void setArtical_created_date(String artical_created_date) {
        this.artical_created_date = artical_created_date;
    }

    public String getCategory_child_name() {
        return category_child_name;
    }

    public void setCategory_child_name(String category_child_name) {
        this.category_child_name = category_child_name;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getArtical_view() {
        return artical_view;
    }

    public void setArtical_view(String artical_view) {
        this.artical_view = artical_view;
    }
}

