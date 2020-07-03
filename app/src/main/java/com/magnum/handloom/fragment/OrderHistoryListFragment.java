package com.magnum.handloom.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.EventListViewListAdapter;
import com.magnum.handloom.adaptor.OrderHistoryListViewListAdapter;
import com.magnum.handloom.request.EventListRequestBean;
import com.magnum.handloom.request.OrderHistoryRequestBean;
import com.magnum.handloom.response.EventListResponseBean;
import com.magnum.handloom.response.OrderHistoryResponseBean;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.RecyclerTouchListener;
import com.magnum.handloom.utilities.UserPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderHistoryListFragment extends Fragment {

    OrderHistoryListViewListAdapter mAdapter;
    private RecyclerView recyclerView;
    OrderHistoryResponseBean orderHistoryResponseBean;
    View lyt_empty_history;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.order_history_list_fragment, container, false);

        recyclerView = (RecyclerView) rooView.findViewById(R.id.recycler_view);
        lyt_empty_history = rooView.findViewById(R.id.lyt_empty_history);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                OrderHistoryDetailsFragment fragment = new OrderHistoryDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "Order Details");
                bundle.putString("old_title", "Order Details");
                bundle.putSerializable("order_details",orderHistoryResponseBean.getOrder_history().get(position).getOrderHistoryItems().get(0));
                bundle.putSerializable("order_history",orderHistoryResponseBean);
                fragment.setArguments(bundle);
                ((MainActivity) getActivity()).AddFragment(fragment, "Order Details");

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (getArguments() != null) {
            OrderHistoryRequestBean orderHistoryRequestBean = new OrderHistoryRequestBean();
            orderHistoryRequestBean.setUser_id(UserPreference.getPreference(getActivity()).getUsers_id());
            orderHistoryRequestBean.setPage_no("0");
            getOrderHistoryList(orderHistoryRequestBean);
        }

        return rooView;
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

    public void getOrderHistoryList(OrderHistoryRequestBean eventListRequestBean) {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<OrderHistoryResponseBean> call = apiService.getOrderHistoryList(eventListRequestBean);
            call.enqueue(new Callback<OrderHistoryResponseBean>() {
                @Override
                public void onResponse(Call<OrderHistoryResponseBean> call, Response<OrderHistoryResponseBean> response) {
                    orderHistoryResponseBean = response.body();

                    if (orderHistoryResponseBean != null&&orderHistoryResponseBean.getOrder_history().size()>0) {

                        mAdapter = new OrderHistoryListViewListAdapter(getActivity(), orderHistoryResponseBean.getOrder_history());
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
                        // set the adapter
                        recyclerView.setAdapter(mAdapter);


                    } else {

                        lyt_empty_history.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);

                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<OrderHistoryResponseBean> call, Throwable t) {
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
}
