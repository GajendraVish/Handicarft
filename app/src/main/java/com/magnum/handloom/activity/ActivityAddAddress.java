package com.magnum.handloom.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.magnum.handloom.R;
import com.magnum.handloom.request.AddAddressRequestBean;
import com.magnum.handloom.response.AddAddressResponseBean;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.Config;
import com.magnum.handloom.utilities.UserPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityAddAddress extends AppCompatActivity {

    EditText edt_name, edt_phone, edt_state, edt_city, edt_area, edt_zipcode, edt_landmark;
    Button btn_submit_order;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        view = findViewById(android.R.id.content);
        if (Config.ENABLE_RTL_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);
        edt_state = findViewById(R.id.edt_state);
        edt_city = findViewById(R.id.edt_city);
        edt_area = findViewById(R.id.edt_area);
        edt_zipcode = findViewById(R.id.edt_zipcode);
        edt_landmark = findViewById(R.id.edt_landmark);
        btn_submit_order = findViewById(R.id.btn_submit_order);
        setupToolbar();
        submitAddress();
    }

    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title_add_address);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void submitAddress() {
        btn_submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValueFromEditText();
            }
        });
    }


    public void getValueFromEditText() {
        String str_name, str_phone, str_state, str_city, str_area, str_zipcode, str_landmark;
        str_name = edt_name.getText().toString();
        str_phone = edt_phone.getText().toString();
        str_state = edt_state.getText().toString();
        str_city = edt_city.getText().toString();
        str_area = edt_area.getText().toString();
        str_zipcode = edt_zipcode.getText().toString();
        str_landmark = edt_landmark.getText().toString();

        if (str_name.equalsIgnoreCase("") ||
                str_phone.equalsIgnoreCase("") ||
                str_state.equalsIgnoreCase("") ||
                str_city.equalsIgnoreCase("") ||
                str_area.equalsIgnoreCase("") ||
                str_zipcode.equalsIgnoreCase("") ||
                str_landmark.equalsIgnoreCase("")) {
            Snackbar.make(view, R.string.checkout_fill_form, Snackbar.LENGTH_SHORT).show();
        } else {

            AddAddressRequestBean addAddressRequestBean = new AddAddressRequestBean();
            addAddressRequestBean.setUser_id(UserPreference.getPreference(ActivityAddAddress.this).getUsers_id());
            addAddressRequestBean.setName(str_name);
            addAddressRequestBean.setMobile(str_phone);
            addAddressRequestBean.setState(str_state);
            addAddressRequestBean.setCity(str_city);
            addAddressRequestBean.setArea(str_area);
            addAddressRequestBean.setZipcode(str_zipcode);
            addAddressRequestBean.setLandmark(str_landmark);
            addUserAddress(addAddressRequestBean);
        }
    }
    public void addUserAddress(AddAddressRequestBean addAddressRequestBean) {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(ActivityAddAddress.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<AddAddressResponseBean> call = apiService.addUserAddress(addAddressRequestBean);
            call.enqueue(new Callback<AddAddressResponseBean>() {
                @Override
                public void onResponse(Call<AddAddressResponseBean> call, Response<AddAddressResponseBean> response) {
                    AddAddressResponseBean ecomCategoryResponseBean = response.body();
                    if (ecomCategoryResponseBean.getError().equalsIgnoreCase("0")) {
                        showAlertDialog(ecomCategoryResponseBean.getMsg());
                    } else {
                        showAlertDialog(ecomCategoryResponseBean.getMsg());
                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<AddAddressResponseBean> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(ActivityAddAddress.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    //     tv_msg.setVisibility(View.VISIBLE);
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showAlertDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAddAddress.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}
