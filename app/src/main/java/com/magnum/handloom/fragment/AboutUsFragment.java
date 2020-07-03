package com.magnum.handloom.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;


public class AboutUsFragment extends Fragment {

    TextView text_about_us;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.about_us_fragment, container, false);

        text_about_us=rooView.findViewById(R.id.text_about_us);

        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        text_about_us.setText("Handicraft is about processing materials by hand with hand tools. The results can be helpful things or decorative things. The materials utilized in the product are natural, industrially processed or maybe recycled. The models of the product are ancient, revised traditional or fashionable. Handicraft is deeply frozen in society and contributes to preserving and sending traditions. In their product, crafters transfer an area of their cultural heritage in ideas, forms, materials and work ways, similarly as their own values, philosophy of life, fashion and self-image. Craftspeople, conjointly known as artisans, possess technical data of materials and work ways. They’re skilled employees whose profession relies on manual skills. Their main tools are the power of work strategies and their manual skills. Handicraft contains plenty of implicit data which grows every year, aboard with skills. The order and apprentice system has been widely used to transfer skills and implicit data.");

        return rooView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setHeader(getArguments().getString("title"));
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
}
