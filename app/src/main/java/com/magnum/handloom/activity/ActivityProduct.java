package com.magnum.handloom.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.magnum.handloom.R;
import com.magnum.handloom.adaptor.EcomCategoryListViewListAdapter;
import com.magnum.handloom.adaptor.RecyclerAdapterProduct;
import com.magnum.handloom.request.EcomProductRequestBean;
import com.magnum.handloom.response.EcomCategoryResponseBean;
import com.magnum.handloom.response.EcomProductResponseBean;
import com.magnum.handloom.response.Product;
import com.magnum.handloom.rest.ApiClient;
import com.magnum.handloom.rest.ApiInterface;
import com.magnum.handloom.rest.Config;
import com.magnum.handloom.utilities.ItemOffsetDecoration;
import com.magnum.handloom.utilities.UserPreference;
import com.magnum.handloom.utilities.Utils;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityProduct extends AppCompatActivity implements RecyclerAdapterProduct.ContactsAdapterListener {

    private RecyclerView recyclerView;
    private List<Product> productList;
    private RecyclerAdapterProduct mAdapter;
    private SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout = null;
    private String category_id, category_name;
    public Toolbar toolbar;

    int p_n = 0;
    boolean isEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        if (Config.ENABLE_RTL_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        Intent intent = getIntent();
        category_id = intent.getStringExtra("category_id");
        category_name = intent.getStringExtra("category_name");

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(category_name);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recycler_view);
        productList = new ArrayList<>();
       // fetchData();
        EcomProductRequestBean ecomProductRequestBean=new EcomProductRequestBean();
        ecomProductRequestBean.setUser_id(UserPreference.getPreference(ActivityProduct.this).getUsers_id());
        ecomProductRequestBean.setCategory_id(category_id);
        ecomProductRequestBean.setPage_no(0);
        getEcomProduct(ecomProductRequestBean);
        onRefresh();

    }

    private void onRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productList.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (Utils.isNetworkAvailable(ActivityProduct.this)) {
                            swipeRefreshLayout.setRefreshing(false);
                         //   fetchData();


                            EcomProductRequestBean ecomProductRequestBean=new EcomProductRequestBean();
                            ecomProductRequestBean.setUser_id(UserPreference.getPreference(ActivityProduct.this).getUsers_id());
                            ecomProductRequestBean.setCategory_id(category_id);
                            ecomProductRequestBean.setPage_no(0);
                            getEcomProduct(ecomProductRequestBean);


                        } else {
                            swipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, 1500);
            }
        });
    }

//    private void fetchData() {
//        JsonArrayRequest request = new JsonArrayRequest(GET_CATEGORY_DETAIL + category_id, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        if (response == null) {
//                            Toast.makeText(getApplicationContext(), R.string.failed_fetch_data, Toast.LENGTH_LONG).show();
//                            return;
//                        }
//
//                        List<Product> items = new Gson().fromJson(response.toString(), new TypeToken<List<Product>>() {
//                        }.getType());
//
//                        // adding contacts to contacts list
//                        productList.clear();
//                        productList.addAll(items);
//
//                        // refreshing recycler view
//                        mAdapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // error in getting json
//                Log.e("INFO", "Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        MyApplication.getInstance().addToRequestQueue(request);
//    }
    public void getEcomProduct(EcomProductRequestBean ecomProductRequestBean) {
        try {
            final ProgressDialog progressDialog = new ProgressDialog(ActivityProduct.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<EcomProductResponseBean> call = apiService.getEcomProduct(ecomProductRequestBean);
            call.enqueue(new Callback<EcomProductResponseBean>() {
                @Override
                public void onResponse(Call<EcomProductResponseBean> call, Response<EcomProductResponseBean> response) {
                    EcomProductResponseBean ecomCategoryResponseBean = response.body();
                    if (ecomCategoryResponseBean.getCategorieInfo()!=null){
                        productList.clear();
                        productList.addAll(ecomCategoryResponseBean.getCategorieInfo());
                        mAdapter = new RecyclerAdapterProduct(ActivityProduct.this, productList, ActivityProduct.this);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ActivityProduct.this, 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(ActivityProduct.this, R.dimen.item_offset);
                        recyclerView.addItemDecoration(itemDecoration);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);
                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<EcomProductResponseBean> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(ActivityProduct.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    //     tv_msg.setVisibility(View.VISIBLE);
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @Override
    public void onContactSelected(Product product) {
        Intent intent = new Intent(getApplicationContext(), ActivityProductDetail.class);
        intent.putExtra("product_id", product.getEcomm_product_id());
        intent.putExtra("title", product.getEcomm_product_name());
        intent.putExtra("image", product.getEcomm_product_image1());
        intent.putExtra("product_price", product.getEcomm_product_offer_price());
        intent.putExtra("product_description", product.getEcomm_product_description());
//      intent.putExtra("product_quantity", product.getProduct_quantity());
        intent.putExtra("product_status", "Available");
//      intent.putExtra("currency_code", product.getCurrency_code());
        intent.putExtra("category_name", product.getEcomm_product_category_id());
        startActivity(intent);
    }

}
