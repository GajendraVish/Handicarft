package com.magnum.handloom.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.magnum.handloom.R;
import com.magnum.handloom.response.Product;
import com.magnum.handloom.response.UserAddress;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.Config;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapterAddress extends RecyclerView.Adapter<RecyclerAdapterAddress.MyViewHolder> {

    private Context context;
    List<UserAddress> userAddresses;
    public static int number_of_labour=1;
    int numberOfCheckboxesChecked;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox addressCheckBox;



        public MyViewHolder(View view) {
            super(view);
            addressCheckBox = view.findViewById(R.id.addressCheckBox);
            int no_of_labour=1;
            int numberOfCheckboxesChecked=0;


        }
    }

    public RecyclerAdapterAddress(Context context, List<UserAddress> userAddresses1) {
        this.context = context;
        this.userAddresses = userAddresses1;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_asign_address_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final UserAddress userAddress = userAddresses.get(position);

        holder.addressCheckBox.setText(userAddress.getUsers_address_name() +" , "+userAddress.getUsers_address_area()+" , "
                +userAddress.getUsers_address_city()+" , "+userAddress.getUsers_address_state()+" , "+userAddress.getUsers_address_state()+" , "
                +userAddress.getUsers_address_landmark()+" , "+userAddress.getUsers_address_zip_code()+" , ");


        holder.addressCheckBox.setChecked(userAddress.isSelected());
        holder.addressCheckBox.setTag(userAddresses.get(position));

        holder.addressCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final CheckBox cb = (CheckBox) v;
                UserAddress  userAddress1= (UserAddress) cb.getTag();
                userAddress1.setSelected(cb.isChecked());
                userAddresses.get(position).setSelected(cb.isChecked());

            }
        });

        holder.addressCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton checkboxView, boolean isChecked) {
                UserAddress c = (UserAddress) checkboxView.getTag();

                if (isChecked) {

                    numberOfCheckboxesChecked++;

                } else if (!isChecked) {

                    numberOfCheckboxesChecked--;

                }
                if (numberOfCheckboxesChecked >= number_of_labour + 1)// it will allow  checkboxes only
                {

                   // Toast.makeText(context, "You can't assign more than " + number_of_labour + " labour for this request!", Toast.LENGTH_LONG).show();
                    checkboxView.setChecked(false);
                    numberOfCheckboxesChecked--;
                } else {
                    c.setSelected(isChecked);
                }
                System.out.println(" ---------------    " + numberOfCheckboxesChecked);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userAddresses.size();
    }


    public interface ContactsAdapterListener {
        void onContactSelected(UserAddress userAddress);
    }


    public List<UserAddress> getAddressList() {
        return userAddresses;
    }
}
