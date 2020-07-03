package com.magnum.handloom.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.magnum.handloom.R;
import com.magnum.handloom.activity.MainActivity;
import com.magnum.handloom.adaptor.ExpandableListAdapter;
import com.magnum.handloom.response.Child;
import com.magnum.handloom.response.SlieMenuCategory;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArticalCategoryFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Child>> expandableListDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.artical_category_fragment, container, false);

        expandableListView = (ExpandableListView) rooView.findViewById(R.id.expandableListView);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {


//                Toast.makeText(
//                        getApplicationContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT
//                )
//                        .show();

                Toast.makeText(getActivity(), "" + expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).getCategoryChildName(), Toast.LENGTH_SHORT).show();

                ArticalListFragment fragment = new ArticalListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "Articals");
                bundle.putString("old_title", "Select ArticalCategory");
                bundle.putString("category_id", expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).getCategoryChildId());
                fragment.setArguments(bundle);
                ((MainActivity) getActivity()).AddFragment(fragment, "Articals Details");


                return false;
            }
        });

        rooView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        getSlideMenuCategory();
        return rooView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setHeader(getArguments().getString("title"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) getActivity()).reIntailzeToolbar();
    }

    public void getSlideMenuCategory() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<SlieMenuCategory> call = apiService.getSlideMenuCategory();

        call.enqueue(new Callback<SlieMenuCategory>() {
            @Override
            public void onResponse(Call<SlieMenuCategory> call, Response<SlieMenuCategory> response) {
                SlieMenuCategory slieMenuCategory = response.body();
                if (slieMenuCategory != null) {
                    expandableListDetail = new HashMap<String, List<Child>>();
                    if (slieMenuCategory.getError().equals(0)) {
                        expandableListTitle = new ArrayList<String>();
                            for (int i = 0; i < slieMenuCategory.getArticalCategory().size(); i++) {
                                List<Child> child_list = new ArrayList<>();
                                for (int j = 0; j < slieMenuCategory.getArticalCategory().get(i).getChild().size(); j++) {
                                    Child child = new Child();
                                    child.setCategoryChildId(slieMenuCategory.getArticalCategory().get(i).getChild().get(j).getCategoryChildId());
                                    child.setCategoryChildMainId(slieMenuCategory.getArticalCategory().get(i).getChild().get(j).getCategoryChildMainId());
                                    child.setCategoryChildName(slieMenuCategory.getArticalCategory().get(i).getChild().get(j).getCategoryChildName());
                                    child.setCategoryChildStatus(slieMenuCategory.getArticalCategory().get(i).getChild().get(j).getCategoryChildStatus());
                                    child_list.add(child);
                                    expandableListDetail.put(slieMenuCategory.getArticalCategory().get(i).getName(), child_list);
                                }
                                expandableListTitle.add(slieMenuCategory.getArticalCategory().get(i).getName());
                            }


                        expandableListAdapter = new ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
                        expandableListView.setAdapter(expandableListAdapter);

                    } else {
                        showAlertDialog("");
                    }
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SlieMenuCategory> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    public void showAlertDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("अपडेट", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }


}
