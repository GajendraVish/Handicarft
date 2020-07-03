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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.flipkart.youtubeview.activity.YouTubeActivity;
import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.ExpandableListAdapter;
import com.magnum.handloom.request.ArticalDetailsRequestBean;
import com.magnum.handloom.response.Articaletails;
import com.magnum.handloom.response.Child;
import com.magnum.handloom.response.SlieMenuCategory;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.Config;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.magnum.handloom.rest.ApiClient.BASE_URL;


public class ArticalDetailsFragment extends Fragment {
    ImageView img_artical;
    TextView artical_details;
    TextView artical_created_by;
    TextView artical_created_date;
    ImageView btn_play_video;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.artical_details_fragment, container, false);

        img_artical=(ImageView)rooView.findViewById(R.id.img_artical);
        artical_details=(TextView) rooView.findViewById(R.id.artical_details);
        artical_created_by=(TextView) rooView.findViewById(R.id.artical_created_by);
        artical_created_date=(TextView) rooView.findViewById(R.id.artical_created_date);
        btn_play_video=(ImageView) rooView.findViewById(R.id.play);
        btn_play_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getArguments()!=null) {
                    Intent intent = new Intent(getActivity(), YouTubeActivity.class);
                    intent.putExtra("apiKey", Config.DEVELOPER_KEY);
                    intent.putExtra("videoId", getArguments().getString("videoId"));
                    startActivity(intent);
                }
            }
        });

        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        ArticalDetailsRequestBean articalDetailsRequestBean = new ArticalDetailsRequestBean();
        articalDetailsRequestBean.setArtical_id(getArguments().getString("artical_id"));
        getArticalDetails(articalDetailsRequestBean);

        return rooView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setHeader(getArguments     ().getString("title"));
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

    public void getArticalDetails(ArticalDetailsRequestBean articalDetailsRequestBean) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Articaletails> call = apiService.getArticalDetails(articalDetailsRequestBean);

        call.enqueue(new Callback<Articaletails>() {
            @Override
            public void onResponse(Call<Articaletails> call, Response<Articaletails> response) {
                Articaletails articaletails = response.body();
                if (articaletails != null) {


                    Transformation transformation = new RoundedTransformationBuilder()
                            .cornerRadiusDp(8)
                            .oval(false)
                            .build();

                    Picasso.with(getActivity())
                            .load(ApiClient.BASE_URL +articaletails.getArticals_info().getArtical_image())
                            .placeholder(R.drawable.ic_loading)
                            .resize(250, 250)
                            .centerCrop()
                            .transform(transformation)
                            .into(img_artical);


                    artical_details.setText(articaletails.getArticals_info().getArtical_text());
                    artical_created_by.setText("Created by :"+articaletails.getArticals_info().getArtical_created_by());
                    artical_created_date.setText("Created date :"+articaletails.getArticals_info().getArtical_created_date());



                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Articaletails> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

}
