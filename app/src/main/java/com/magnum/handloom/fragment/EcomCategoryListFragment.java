package com.magnum.handloom.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.magnum.handloom.activity.ActivityProduct;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.EcomCategoryListViewListAdapter;
import com.magnum.handloom.adaptor.EventListViewListAdapter;
import com.magnum.handloom.request.EventListRequestBean;
import com.magnum.handloom.response.EcomCategoryResponseBean;
import com.magnum.handloom.response.EventListResponseBean;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.RecyclerTouchListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EcomCategoryListFragment extends Fragment {

    EcomCategoryListViewListAdapter mAdapter;
    private RecyclerView recyclerView;
    EcomCategoryResponseBean ecomCategoryResponseBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.event_list_fragment, container, false);

        recyclerView = (RecyclerView) rooView.findViewById(R.id.recycler_view);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

//                EventDetailsFragment fragment = new EventDetailsFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("title", "Event Details");
//                bundle.putSerializable("eventInfo",eventListResponseBean.getEventInfo().get(position));
//                fragment.setArguments(bundle);
//                ((MainActivity) getActivity()).AddFragment(fragment, "Event Details");

                Intent intent = new Intent(getActivity(), ActivityProduct.class);
                intent.putExtra("category_id", ecomCategoryResponseBean.getCategorieInfo().get(position).getEcomm_category_id());
                intent.putExtra("category_name", ecomCategoryResponseBean.getCategorieInfo().get(position).getEcomm_category_name());
                startActivity(intent);

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
        getEcomCategoty();
        return rooView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setHeader(getArguments().getString("title"));
    }



    public void getEcomCategoty() {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<EcomCategoryResponseBean> call = apiService.getEcomCategoty();
            call.enqueue(new Callback<EcomCategoryResponseBean>() {
                @Override
                public void onResponse(Call<EcomCategoryResponseBean> call, Response<EcomCategoryResponseBean> response) {
                    ecomCategoryResponseBean = response.body();

                    if (ecomCategoryResponseBean != null) {

                        mAdapter = new EcomCategoryListViewListAdapter(getActivity(), ecomCategoryResponseBean.getCategorieInfo());
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
                        // set the adapter
                        recyclerView.setAdapter(mAdapter);


                    } else {

                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<EcomCategoryResponseBean> call, Throwable t) {
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
}
