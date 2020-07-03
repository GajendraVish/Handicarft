package com.magnum.handloom.request;

import java.io.Serializable;

/**
 * Created by ITES-05 on 7/31/2017.
 */

public class RegistrationRequestModel implements Serializable {


    private String user_name;
    private String users_mobile;
    private String users_email;
    private String users_password;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUsers_mobile() {
        return users_mobile;
    }

    public void setUsers_mobile(String users_mobile) {
        this.users_mobile = users_mobile;
    }

    public String getUsers_email() {
        return users_email;
    }

    public void setUsers_email(String users_email) {
        this.users_email = users_email;
    }

    public String getUsers_password() {
        return users_password;
    }

    public void setUsers_password(String users_password) {
        this.users_password = users_password;
    }
}
