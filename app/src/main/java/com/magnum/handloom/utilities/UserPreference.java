package com.magnum.handloom.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.magnum.handloom.response.UserBean;


/**
 * Created by gajendra on 3/24/2019.
 */

public class UserPreference {
    public static final String MYPREFS = "MySharedPref";
    public static final int mode = Activity.MODE_PRIVATE;
    public static void setPreference(Context context, UserBean userBean) {
        SharedPreferences prefs = context.getSharedPreferences(MYPREFS, mode);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("users_id", userBean.getUsers_id());
        edit.putString("users_name", userBean.getUsers_name());
        edit.putString("users_mobile", userBean.getUsers_mobile());
        edit.putString("users_email", userBean.getUsers_email());
        edit.putString("users_password", userBean.getUsers_password());
        edit.putString("users_type", userBean.getUsers_type());
        edit.putString("users_android_id", userBean.getUsers_android_id());
        edit.putString("users_status", userBean.getUsers_status());
        edit.commit();

    }
    public static UserBean getPreference(Context context) {
        UserBean userBean = new UserBean();
        SharedPreferences prefs = context.getSharedPreferences(MYPREFS, mode);
        userBean.setUsers_id(prefs.getString("users_id", null));
        userBean.setUsers_name(prefs.getString("users_name", null));
        userBean.setUsers_mobile(prefs.getString("users_mobile", null));
        userBean.setUsers_email(prefs.getString("users_email", null));
        userBean.setUsers_password(prefs.getString("users_password", null));
        userBean.setUsers_type(prefs.getString("users_type", null));
        userBean.setUsers_android_id(prefs.getString("users_android_id", null));
        userBean.setUsers_status(prefs.getString("users_status", null));
        return userBean;
    }
}
