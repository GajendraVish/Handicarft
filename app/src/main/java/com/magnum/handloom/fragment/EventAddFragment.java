package com.magnum.handloom.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.ArticalListViewListAdapter;
import com.magnum.handloom.adaptor.EventListViewListAdapter;
import com.magnum.handloom.request.AddEventRequestBean;
import com.magnum.handloom.request.ArticalListRequestBean;
import com.magnum.handloom.request.EventListRequestBean;
import com.magnum.handloom.response.AddEventResponseBean;
import com.magnum.handloom.response.ArticalListResponseBean;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.RecyclerTouchListener;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventAddFragment extends Fragment {


    EditText ed_title, ed_event_date, ed_description, ed_event_address;
    Button btn_submit_event;
    AddEventResponseBean articalListResponseBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.add_event_fragment, container, false);

        ed_title = (EditText) rooView.findViewById(R.id.ed_title);
        ed_event_date = (EditText) rooView.findViewById(R.id.ed_event_date);
        ed_description = (EditText) rooView.findViewById(R.id.ed_description);
        ed_event_address = (EditText) rooView.findViewById(R.id.ed_event_address);
        btn_submit_event = (Button) rooView.findViewById(R.id.btn_submit_event);
        ed_event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(ed_event_date);
            }
        });

        btn_submit_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_ed_title = ed_title.getText().toString().trim();
                String str_ed_event_date = ed_event_date.getText().toString().trim();
                String str_ed_description = ed_description.getText().toString().trim();
                String str_ed_event_address = ed_event_address.getText().toString().trim();

                if (str_ed_title.length() <= 0) {
                    showTost(v, "Please Enter Event title.");
                } else if (str_ed_event_date.length() <= 0) {
                    showTost(v, "Please Enter Event date");
                } else if (str_ed_description.length() <= 0) {
                    showTost(v, "Please Enter Event description");
                } else if (str_ed_event_address.length() <= 0) {
                    showTost(v, "Please Enter Event Address.");
                } else {

                    AddEventRequestBean addEventRequestBean = new AddEventRequestBean();
                    addEventRequestBean.setUser_id("2");
                    addEventRequestBean.setTitle(str_ed_title);
                    addEventRequestBean.setEvent_date(str_ed_event_date);
                    addEventRequestBean.setDescription(str_ed_description);
                    addEventRequestBean.setAddress(str_ed_event_address);
                    addEventByUser(addEventRequestBean);

                }
            }
        });


        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rooView;
    }


    public void addEventByUser(AddEventRequestBean addEventRequestBean) {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<AddEventResponseBean> call = apiService.addEventByUser(addEventRequestBean);
            call.enqueue(new Callback<AddEventResponseBean>() {
                @Override
                public void onResponse(Call<AddEventResponseBean> call, Response<AddEventResponseBean> response) {
                    articalListResponseBean = response.body();
                    if (articalListResponseBean != null) {
                        showAlertDialog(articalListResponseBean.getMsg());
                    } else {
                        showAlertDialog(articalListResponseBean.getMsg());
                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<AddEventResponseBean> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
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


    public void datePicker(final EditText editText) {
        int mYear;
        int mMonth;
        int mDay;
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DATE);
        // Launch Date Picker Dialog

        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        DatePicker dp = dpd.getDatePicker();
        //Set the DatePicker minimum date selection to current date
        dp.setMinDate(Calendar.getInstance().getTimeInMillis());
        dpd.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setHeader(getArguments().getString("title"));
    }

    public void showTost(View view, String msg) {
        final Snackbar snack = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View view2 = snack.getView();
        TextView tv = (TextView) view2.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.setAction(getString(R.string.btn_dismiss), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snack.dismiss();
            }
        });
        snack.show();
    }

    public void showAlertDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity) getActivity()).closeFragment();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showAlertDialog2(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity) getActivity()).closeFragment();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
