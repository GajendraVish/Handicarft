package com.magnum.handloom.fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.DashboardRecentViewListAdapter;
import com.magnum.handloom.adaptor.DashboardTopViewListAdapter;
import com.magnum.handloom.response.GetDashboardContentData;
import com.magnum.handloom.response.SliderImage;
import com.magnum.handloom.response.SliderImageResponse;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

import static com.magnum.handloom.rest.ApiClient.BASE_URL;

/**
 * Created by GAJENDRA on 9/11/2017.
 */

public class DashboardNewFragment extends Fragment {

    protected String[] main_option;
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    private BannerSlider bannerSlider;
    List<SliderImageResponse> sliderImageResponseList;

    DashboardTopViewListAdapter mAdapter;
    DashboardRecentViewListAdapter mAdapter1;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    GetDashboardContentData getDashboardContentData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Bold.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "NotoSans-Regular.ttf");
        main_option = getResources().getStringArray(R.array.main_options);

        View x = inflater.inflate(R.layout.dashboard_artical, null);
        bannerSlider = (BannerSlider) x.findViewById(R.id.banner_slider1);

        recyclerView = (RecyclerView) x.findViewById(R.id.recycler_view);
        recyclerView1 = (RecyclerView) x.findViewById(R.id.recycler_view1);

        getSliderImage();
        getDasboardContentData();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                ArticalDetailsFragment fragment = new ArticalDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "Articals Details");
                bundle.putString("artical_id", getDashboardContentData.getTop_view().get(position).getArtical_id());
                fragment.setArguments(bundle);
                ((MainActivity) getActivity()).AddFragment(fragment, "Articals Details");

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView1.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                ArticalDetailsFragment fragment = new ArticalDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "Articals Details");
                bundle.putString("artical_id", getDashboardContentData.getRecent().get(position).getArtical_id());
                fragment.setArguments(bundle);
                ((MainActivity) getActivity()).AddFragment(fragment, "Articals Details");

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return x;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).reIntailzeToolbar();

    }

    public void getSliderImage() {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<SliderImage> call = apiService.getSliderImage();
            call.enqueue(new Callback<SliderImage>() {
                @Override
                public void onResponse(Call<SliderImage> call, Response<SliderImage> response) {
                    SliderImage sliderImage = response.body();


                    if (sliderImage != null) {

                        sliderImageResponseList = sliderImage.getImagedata();

                        List<Banner> remoteBanners = new ArrayList<>();
//                        List<Slider> slider = sliderImageResponse.getSlider();
//
                        for (SliderImageResponse sliderImageResponse : sliderImageResponseList) {
                            remoteBanners.add(new RemoteBanner(ApiClient.BASE_URL + sliderImageResponse.getSlider_image()
                            ));
                        }
                        bannerSlider.setBanners(remoteBanners);

                    } else {

                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<SliderImage> call, Throwable t) {
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


    public void getDasboardContentData() {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<GetDashboardContentData> call = apiService.getDashboardContentData();
            call.enqueue(new Callback<GetDashboardContentData>() {
                @Override
                public void onResponse(Call<GetDashboardContentData> call, Response<GetDashboardContentData> response) {
                    getDashboardContentData = response.body();

                    if (getDashboardContentData != null) {

                        mAdapter = new DashboardTopViewListAdapter(getActivity(), getDashboardContentData.getTop_view());
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
                        // set the adapter
                        recyclerView.setAdapter(mAdapter);


                        mAdapter1 = new DashboardRecentViewListAdapter(getActivity(), getDashboardContentData.getRecent());
                        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView1.setLayoutManager(mLayoutManager1);
                        recyclerView1.setItemAnimator(new DefaultItemAnimator());
                        recyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
                        // set the adapter
                        recyclerView1.setAdapter(mAdapter1);


                    } else {

                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<GetDashboardContentData> call, Throwable t) {
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