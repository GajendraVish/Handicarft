package com.magnum.handloom.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.magnum.handloom.R;


/**
 * Created by alka on 6/25/2016.
 */
public class SlideListAdaptor extends BaseAdapter {
    Context context;
    String[] data ;
    int[] imgages = {R.drawable.home_icon,R.mipmap.article,R.mipmap.events,R.mipmap.events,R.mipmap.ecommerce,R.drawable.ic_order_history,R.drawable.ic_about,R.drawable.logout_icon, R.drawable.exit,};

    public SlideListAdaptor(Context context) {
        this.context = context;
        data=context.getResources().getStringArray(R.array.slide_oprtions);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.slidemenu_list_adp, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(data[position]);
        viewHolder.imageView.setImageResource(imgages[position]);
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        ImageView imageView;
    }
}
