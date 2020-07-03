package com.magnum.handloom.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flipkart.youtubeview.activity.YouTubeActivity;
import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.ArticalListViewListAdapter;
import com.magnum.handloom.adaptor.DashboardRecentViewListAdapter;
import com.magnum.handloom.adaptor.DashboardTopViewListAdapter;
import com.magnum.handloom.request.ArticalDetailsRequestBean;
import com.magnum.handloom.request.ArticalListRequestBean;
import com.magnum.handloom.response.ArticalListResponseBean;
import com.magnum.handloom.response.Articaletails;
import com.magnum.handloom.response.GetDashboardContentData;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.Config;
import com.magnum.handloom.rest.RecyclerTouchListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.magnum.handloom.rest.ApiClient.BASE_URL;


public class ArticalListFragment extends Fragment {

    ArticalListViewListAdapter mAdapter;
    private RecyclerView recyclerView;
    ArticalListResponseBean articalListResponseBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.artical_list_fragment, container, false);

        recyclerView = (RecyclerView) rooView.findViewById(R.id.recycler_view);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                ArticalDetailsFragment fragment = new ArticalDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "Articals Details");
                bundle.putString("old_title", "Articals");
                bundle.putString("artical_id",articalListResponseBean.getArticals().get(position).getArtical_id());
                bundle.putString("videoId",articalListResponseBean.getArticals().get(position).getArtical_video());
                fragment.setArguments(bundle);
                ((MainActivity) getActivity()).AddFragment(fragment, "Articals Details");

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
            ArticalListRequestBean articalDetailsRequestBean = new ArticalListRequestBean();
            articalDetailsRequestBean.setChild_category_id(getArguments().getString("category_id"));
            getArticalList(articalDetailsRequestBean);
        }

        return rooView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setHeader(getArguments().getString("title"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) getActivity()).setHeader(getArguments().getString("old_title"));
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

    public void getArticalList(ArticalListRequestBean articalDetailsRequestBean) {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<ArticalListResponseBean> call = apiService.getArticalListData(articalDetailsRequestBean);
            call.enqueue(new Callback<ArticalListResponseBean>() {
                @Override
                public void onResponse(Call<ArticalListResponseBean> call, Response<ArticalListResponseBean> response) {
                    articalListResponseBean = response.body();

                    if (articalListResponseBean != null) {

                        mAdapter = new ArticalListViewListAdapter(getActivity(), articalListResponseBean.getArticals());
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
                public void onFailure(Call<ArticalListResponseBean> call, Throwable t) {
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
