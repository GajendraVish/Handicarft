package com.magnum.handloom.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventInfo implements Serializable {

    @SerializedName("event_id")
    private String event_id;
    @SerializedName("event_name")
    private String event_name;
    @SerializedName("event_description")
    private String event_description;
    @SerializedName("event_address")
    private String event_address;
    @SerializedName("event_added_date")
    private String event_added_date;
    @SerializedName("event_added_by")
    private String event_added_by;
    @SerializedName("event_date")
    private String event_date;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_address() {
        return event_address;
    }

    public void setEvent_address(String event_address) {
        this.event_address = event_address;
    }

    public String getEvent_added_date() {
        return event_added_date;
    }

    public void setEvent_added_date(String event_added_date) {
        this.event_added_date = event_added_date;
    }

    public String getEvent_added_by() {
        return event_added_by;
    }

    public void setEvent_added_by(String event_added_by) {
        this.event_added_by = event_added_by;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }
}


