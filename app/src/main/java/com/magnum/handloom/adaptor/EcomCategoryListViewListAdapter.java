package com.magnum.handloom.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magnum.handloom.R;
import com.magnum.handloom.response.CategorieEcomInfo;
import com.magnum.handloom.response.EventInfo;

import java.util.List;


/**
 * Created by ITES-05 on 8/5/2017.
 */


public class EcomCategoryListViewListAdapter extends RecyclerView.Adapter<EcomCategoryListViewListAdapter.MyViewHolder> {

    private List<CategorieEcomInfo> categorieEcomInfos;
    Context context;
//    ImageLoader imageLoader;
//    DisplayImageOptions options;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView labour_user_img;
        TextView category_name;
        LinearLayout linear_lay;

        public MyViewHolder(View view) {
            super(view);
            labour_user_img = (ImageView) view.findViewById(R.id.labour_user_img);
            category_name = (TextView) view.findViewById(R.id.category_name);
            linear_lay = (LinearLayout) view.findViewById(R.id.linear_lay);

        }
    }

    public EcomCategoryListViewListAdapter(Context context1, List<CategorieEcomInfo> categorieEcomInfos1) {
        this.categorieEcomInfos = categorieEcomInfos1;
        this.context = context1;
//        imageLoader = ImageLoader.getInstance();
//        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
//        options = new DisplayImageOptions.Builder()
//                .cacheInMemory(true) // default
//                .cacheOnDisk(true) // default
//                .handler(new Handler()) // default
//                .showImageOnLoading(R.mipmap.loaing_icon)
//                .showImageForEmptyUri(R.mipmap.loaing_icon)
//                .showImageOnFail(R.mipmap.loaing_icon)
//                .build();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final CategorieEcomInfo dashboardTopView = categorieEcomInfos.get(position);
//        int serial_no = Integer.parseInt(String.valueOf(position));
//        holder.tv_sn.setText(String.valueOf(serial_no + 1));

        final int pos = position;


        holder.category_name.setText(dashboardTopView.getEcomm_category_name());

    //    String url = BASE_URL + "/admin/" + dashboardTopView.getArtical_image();
    //    imageLoader.displayImage(url, holder.labour_user_img, options);

//        holder.linear_lay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(context, LabourTaskActivity.class);
////                intent.putExtra("Labour_id", getLabourListResponseBean.getLabour_id());
////                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categorieEcomInfos.size();
    }

    public List<CategorieEcomInfo> getTopViewList() {
        return categorieEcomInfos;
    }
}
