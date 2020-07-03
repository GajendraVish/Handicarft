
package com.magnum.handloom.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("category_child_id")
    @Expose
    private String categoryChildId;
    @SerializedName("category_child_main_id")
    @Expose
    private String categoryChildMainId;
    @SerializedName("category_child_name")
    @Expose
    private String categoryChildName;
    @SerializedName("category_child_status")
    @Expose
    private String categoryChildStatus;

    public String getCategoryChildId() {
        return categoryChildId;
    }

    public void setCategoryChildId(String categoryChildId) {
        this.categoryChildId = categoryChildId;
    }

    public String getCategoryChildMainId() {
        return categoryChildMainId;
    }

    public void setCategoryChildMainId(String categoryChildMainId) {
        this.categoryChildMainId = categoryChildMainId;
    }

    public String getCategoryChildName() {
        return categoryChildName;
    }

    public void setCategoryChildName(String categoryChildName) {
        this.categoryChildName = categoryChildName;
    }

    public String getCategoryChildStatus() {
        return categoryChildStatus;
    }

    public void setCategoryChildStatus(String categoryChildStatus) {
        this.categoryChildStatus = categoryChildStatus;
    }

}
