package com.magnum.handloom.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.magnum.handloom.DBModel.Cart;
import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.AdapterCart;
import com.magnum.handloom.adaptor.AdapterOrderHistory;
import com.magnum.handloom.response.EventInfo;
import com.magnum.handloom.response.OrderHistory;
import com.magnum.handloom.response.OrderHistoryCart;
import com.magnum.handloom.response.OrderHistoryItem;
import com.magnum.handloom.response.OrderHistoryResponseBean;
import com.magnum.handloom.utilities.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class OrderHistoryDetailsFragment extends Fragment {

    TextView order_id,order_date,order_total_amount;
    OrderHistoryItem order_history;
    RecyclerView recyclerView;
    AdapterOrderHistory adapterCart;
    List<OrderHistoryCart> arrayCart=new ArrayList<>();
    OrderHistoryResponseBean orderHistoryResponseBean;
    double total_price = 0;;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.order_history_details_fragment, container, false);

        order_id=(TextView) rooView.findViewById(R.id.order_id);
        order_date=(TextView) rooView.findViewById(R.id.order_date);
        order_total_amount=(TextView) rooView.findViewById(R.id.order_total_amount);

        recyclerView = rooView.findViewById(R.id.recycler_view);
        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        order_history= (OrderHistoryItem) getArguments().getSerializable("order_details");
        orderHistoryResponseBean= (OrderHistoryResponseBean) getArguments().getSerializable("order_history");
        setData(order_history,orderHistoryResponseBean);




        return rooView;
    }

    public void setData(OrderHistoryItem order_history,OrderHistoryResponseBean orderHistoryResponseBean1){
        order_id.setText(order_history.getEcomm_order_order_id());
        order_date.setText(order_history.getEcomm_order_item_date());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL, 86));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < orderHistoryResponseBean1.getOrder_history().size(); i++) {

            for (int j = 0; j < orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().size(); j++) {
                OrderHistoryCart cart=new OrderHistoryCart();
                cart.setMenuId(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_product_id());
                cart.setMenuName(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_product_name());
                cart.setMenuPrice(String.valueOf(Double.parseDouble(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_product_offer_price())*Double.parseDouble(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_order_item_qty())));
                cart.setMenuQuantity(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_order_item_qty()+"*"+orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_product_offer_price());
                cart.setMenuImage(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_product_image1());
                arrayCart.add(cart);
                total_price += Double.parseDouble(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_product_offer_price())*Double.parseDouble(orderHistoryResponseBean1.getOrder_history().get(i).getOrderHistoryItems().get(j).getEcomm_order_item_qty());

            }
        }
        order_total_amount.setText("Total amount: "+String.valueOf(total_price)+" Rs/-");
        adapterCart = new AdapterOrderHistory(getActivity(), arrayCart);
        recyclerView.setAdapter(adapterCart);

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
