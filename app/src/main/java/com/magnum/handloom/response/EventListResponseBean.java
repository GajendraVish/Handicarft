package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventListResponseBean implements Serializable {

    @SerializedName("error")
    private String error;
    @SerializedName("msg")
    private String msg;
    @SerializedName("event_info")
    private List<EventInfo> EventInfo;

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

    public List<com.magnum.handloom.response.EventInfo> getEventInfo() {
        return EventInfo;
    }

    public void setEventInfo(List<com.magnum.handloom.response.EventInfo> eventInfo) {
        EventInfo = eventInfo;
    }
}
