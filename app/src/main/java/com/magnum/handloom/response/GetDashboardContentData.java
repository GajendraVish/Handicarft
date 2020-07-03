package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDashboardContentData {

    String error;
    String msg;
    @SerializedName("top_view")
    private List<DashboardTopView> top_view;
    @SerializedName("recent")
    private List<DashboardRecent> recent;


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

    public List<DashboardTopView> getTop_view() {
        return top_view;
    }

    public void setTop_view(List<DashboardTopView> top_view) {
        this.top_view = top_view;
    }

    public List<DashboardRecent> getRecent() {
        return recent;
    }

    public void setRecent(List<DashboardRecent> recent) {
        this.recent = recent;
    }
}
