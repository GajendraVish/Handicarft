package com.magnum.handloom.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.magnum.handloom.DBModel.Cart;
import com.magnum.handloom.R;
import com.magnum.handloom.response.OrderHistoryCart;
import com.magnum.handloom.response.UserAddress;
import com.magnum.handloom.rest.ApiClient;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class AdapterOrderHistory extends RecyclerView.Adapter<AdapterOrderHistory.MyViewHolder> {

    private Context context;
    List<OrderHistoryCart> cartList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name,product_quantity,product_price;
        ImageView product_image;



        public MyViewHolder(View view) {
            super(view);
            product_name = view.findViewById(R.id.product_name);
            product_quantity = view.findViewById(R.id.product_quantity);
            product_price = view.findViewById(R.id.product_price);
            product_image = view.findViewById(R.id.product_image);


        }
    }

    public AdapterOrderHistory(Context context, List<OrderHistoryCart> cartList1) {
        this.context = context;
        this.cartList = cartList1;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final OrderHistoryCart cart = cartList.get(position);
        holder.product_name.setText(cart.getMenuName());
        holder.product_quantity.setText(cart.getMenuQuantity());
        holder.product_price.setText(cart.getMenuPrice());

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(8)
                .oval(false)
                .build();

        Picasso.with(context)
                .load(ApiClient.BASE_URL +cart.getMenuImage())
                .placeholder(R.drawable.ic_loading)
                .resize(250, 250)
                .centerCrop()
                .transform(transformation)
                .into(holder.product_image);


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public interface ContactsAdapterListener {
        void onContactSelected(UserAddress userAddress);
    }


    public List<OrderHistoryCart> getAddressList() {
        return cartList;
    }
}
