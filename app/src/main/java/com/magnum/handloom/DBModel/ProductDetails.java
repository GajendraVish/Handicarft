package com.magnum.handloom.DBModel;

import com.magnum.handloom.utilities.AppDatabase;
import com.reactiveandroid.Model;
import com.reactiveandroid.annotation.Column;
import com.reactiveandroid.annotation.PrimaryKey;
import com.reactiveandroid.annotation.Table;

import java.io.Serializable;


/**
 * Created by ITES-05 on 7/31/2017.
 */

@Table(name = "ProductDetails", database = AppDatabase.class)
public class ProductDetails extends Model implements Serializable {

    @PrimaryKey
    private Long id;
    @Column(name = "product_id")
    private String product_id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "total_price")
    private double total_price;
    @Column(name = "currency_code")
    private String currency_code;
    @Column(name = "product_image")
    private String product_image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
