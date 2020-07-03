package com.magnum.handloom.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.magnum.handloom.R;
import com.magnum.handloom.request.RegistrationRequestModel;
import com.magnum.handloom.response.RegistrationResponseModel;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ITES-05 on 7/31/2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    EditText ed_userfirstname,ed_userlastname, ed_mobile_no, ed_other_mobile_no,ed_useremail, ed_password, ed_confirm_password;
    TextView btn_sign_up;
    TextView txt_signup;
    public static RegistrationActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        setContentView(R.layout.user_registration);
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        ed_userfirstname = (EditText) findViewById(R.id.ed_userfirstname);
        ed_userlastname = (EditText) findViewById(R.id.ed_userlastname);
        ed_mobile_no = (EditText) findViewById(R.id.ed_mobile_no);
        ed_other_mobile_no = (EditText) findViewById(R.id.ed_other_mobile_no);
        ed_useremail= (EditText) findViewById(R.id.ed_useremail);
        ed_password = (EditText) findViewById(R.id.ed_password);
        ed_confirm_password = (EditText) findViewById(R.id.ed_confirm_password);
        btn_sign_up = (TextView) findViewById(R.id.btn_sign_up);
        ImageView img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_userfirstname = ed_userfirstname.getText().toString().trim();
                String str_userlastname = ed_userlastname.getText().toString().trim();
                String str_mobile_no = ed_mobile_no.getText().toString().trim();
                String str_other_mobile_no = ed_other_mobile_no.getText().toString().trim();
                String str_email = ed_useremail.getText().toString().trim();
                String str_password = ed_password.getText().toString().trim();
                String str_confirm_password = ed_confirm_password.getText().toString().trim();

                if (str_userfirstname.length() <= 0) {
                    showTost(ed_userfirstname, getString(R.string.msg_name));
                    ed_userfirstname.requestFocus();
                }
                else if (str_userlastname.length() <= 0) {
                    showTost(ed_userlastname, getString(R.string.msg_mobile_no));
                    ed_userlastname.requestFocus();
                }
                else if (str_mobile_no.length() <= 0) {
                    showTost(ed_mobile_no, getString(R.string.msg_mobile_no));
                    ed_mobile_no.requestFocus();
                }

                else if (str_email.length() <= 0) {
                    showTost(ed_useremail, getString(R.string.msg_email_id));
                    ed_useremail.requestFocus();
                }

                else if (str_password.length() <= 0) {
                    showTost(ed_password, getString(R.string.msg_password));
                    ed_password.requestFocus();
                }

                else if (str_confirm_password.length() <= 0) {
                    showTost(ed_confirm_password, getString(R.string.msg_confirm_password));
                    ed_confirm_password.requestFocus();
                } else if (!str_password.equalsIgnoreCase(str_confirm_password)) {
                    showTost(ed_confirm_password, getString(R.string.msg_pass_not_match));
                    ed_confirm_password.requestFocus();
                } else {

                    RegistrationRequestModel registrationRequestModel = new RegistrationRequestModel();
                    registrationRequestModel.setUser_name(str_userfirstname);
                    registrationRequestModel.setUsers_mobile(str_mobile_no);
                    registrationRequestModel.setUsers_email(str_email);
                    registrationRequestModel.setUsers_password(str_password);
                    getRegistration(registrationRequestModel);


                }
            }
        });

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getRegistration(final RegistrationRequestModel registrationRequestModel) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RegistrationResponseModel> call = apiService.userRegistration(registrationRequestModel);
        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {

                RegistrationResponseModel loginResponseBeenList = response.body();

                if (loginResponseBeenList != null) {

                    if (loginResponseBeenList.getError().trim().equalsIgnoreCase("0")) {

//                        if (loginActivity != null) {
//                            loginActivity.finish();
//                        }
                        showAlertDialog(loginResponseBeenList.getMsg());
                        finish();
                    } else {
                        showAlertDialog(loginResponseBeenList.getMsg().trim());
                    }
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    public void showTost(View view, String msg) {
        final Snackbar snack = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View view2 = snack.getView();
        TextView tv = (TextView) view2.findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.setAction(getString(R.string.label_dismiss), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snack.dismiss();
            }
        });
        snack.show();
    }

    public void showAlertDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();

            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      //  startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}
