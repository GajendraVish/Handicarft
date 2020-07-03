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
import com.magnum.handloom.request.ArticalDetailsRequestBean;
import com.magnum.handloom.response.Articaletails;
import com.magnum.handloom.response.EventInfo;
import com.magnum.handloom.response.EventListResponseBean;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.Config;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.magnum.handloom.rest.ApiClient.BASE_URL;


public class EventDetailsFragment extends Fragment {

    TextView event_name,event_date,event_discription,event_address;
    EventInfo eventInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.event_details_fragment, container, false);

        event_name=(TextView) rooView.findViewById(R.id.event_name);
        event_date=(TextView) rooView.findViewById(R.id.event_date);
        event_discription=(TextView) rooView.findViewById(R.id.event_discription);
        event_address=(TextView) rooView.findViewById(R.id.event_address);

        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        eventInfo= (EventInfo) getArguments().getSerializable("eventInfo");
        setData(eventInfo);
        return rooView;
    }

    public void setData(EventInfo data){
        event_name.setText(data.getEvent_name());
        event_date.setText(data.getEvent_date());
        event_discription.setText(data.getEvent_description());
        event_address.setText(data.getEvent_address());
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

}
