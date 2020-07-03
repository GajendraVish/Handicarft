package com.magnum.handloom.utilities;


import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.magnum.handloom.DBModel.ProductDetails;
import com.reactiveandroid.annotation.Database;
import com.reactiveandroid.query.Delete;
import com.reactiveandroid.query.Select;
import com.reactiveandroid.query.Update;

import java.util.ArrayList;
import java.util.List;


@Database(name = "SocialAppDatabase", version = 3)

public class AppDatabase {

    public static int checkAlreadyItemExit(String product_id) {
        // This is how you execute a query
        int count = Select.from(ProductDetails.class)
                .where("product_id = ?", product_id).count();
        return count;
    }
//
//
//    public static List<UserRegistrationRequest> getAllUserData() {
//        // This is how you execute a query
//        List<UserRegistrationRequest> userRegistrationRequestList = Select.from(UserRegistrationRequest.class).fetch();
//        return userRegistrationRequestList;
//    }
//
//    public static List<UserRegistrationRequest> getUserData(String name) {
//        // This is how you execute a query
//        List<UserRegistrationRequest> userRegistrationRequestList = Select.from(UserRegistrationRequest.class)
//                .where("name LIKE ?", "%" + name + "%").fetch();
//        return userRegistrationRequestList;
//    }
    public static void updateProduct(ProductDetails productDetails,String id) {
        //updating record
        Update.table(ProductDetails.class).set("product_id = ?,product_name = ?,quantity = ?,total_price = ?,currency_code = ?,product_image = ?", productDetails.getProduct_id(), productDetails.getProduct_name(), productDetails.getQuantity(), productDetails.getTotal_price(), productDetails.getCurrency_code(), productDetails.getProduct_image()).where("product_id = ?", id).execute();

    }
    public static void deleteAllData() {
        //updating record
        Delete.from(ProductDetails.class).execute();

    }

    public static void deleteIDData(int category_id) {
        //updating record
        Delete.from(ProductDetails.class).where("product_id = ?", category_id).execute();

    }

    public static List<ProductDetails> getAllData() {
        // This is how you execute a query
        List<ProductDetails> productDetails = Select.from(ProductDetails.class).fetch();
        return productDetails;
    }

}


