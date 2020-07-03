package com.magnum.handloom.adaptor;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magnum.handloom.R;
import com.magnum.handloom.response.DashboardRecent;
import com.magnum.handloom.response.DashboardTopView;
import com.magnum.handloom.rest.ApiClient;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import static com.magnum.handloom.rest.ApiClient.BASE_URL;


/**
 * Created by ITES-05 on 8/5/2017.
 */


public class DashboardRecentViewListAdapter extends RecyclerView.Adapter<DashboardRecentViewListAdapter.MyViewHolder> {

    private List<DashboardRecent> dashboardRecentsList;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView labour_user_img;
        TextView artical;
        LinearLayout linear_lay;

        public MyViewHolder(View view) {
            super(view);
            labour_user_img = (ImageView) view.findViewById(R.id.labour_user_img);
            artical = (TextView) view.findViewById(R.id.artical);
            linear_lay = (LinearLayout) view.findViewById(R.id.linear_lay);

        }
    }

    public DashboardRecentViewListAdapter(Context context1, List<DashboardRecent> dashboardRecentsList1) {
        this.dashboardRecentsList = dashboardRecentsList1;
        this.context = context1;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_topview_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final DashboardRecent recent = dashboardRecentsList.get(position);
//        int serial_no = Integer.parseInt(String.valueOf(position));
//        holder.tv_sn.setText(String.valueOf(serial_no + 1));

        final int pos = position;
        holder.artical.setText(recent.getArtical_text()+"...");

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(6)
                .oval(false)
                .build();

        Picasso.with(context)
                .load(ApiClient.BASE_URL +recent.getArtical_image())
                .placeholder(R.drawable.ic_loading)
                .resize(250, 250)
                .centerCrop()
                .transform(transformation)
                .into(holder.labour_user_img);

        holder.linear_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, LabourTaskActivity.class);
//                intent.putExtra("Labour_id", getLabourListResponseBean.getLabour_id());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardRecentsList.size();
    }

    public List<DashboardRecent> getTopViewList() {
        return dashboardRecentsList;
    }
}
