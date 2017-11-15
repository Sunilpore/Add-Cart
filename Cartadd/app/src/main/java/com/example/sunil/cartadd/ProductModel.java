package com.example.sunil.cartadd;

/**
 * Created by Sunil on 11/13/2017.
 */

public class ProductModel {

    public int pid;
    public String prodcat;
    public boolean clickbutton;

    public ProductModel(int pid) {
        this.pid = pid;
    }

    public ProductModel(String prodcat) {
        this.prodcat = prodcat;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProdcat() {
        return prodcat;
    }

    public void setProdcat(String prodcat) {
        this.prodcat = prodcat;
    }

    public boolean isClickbutton() {
        return clickbutton;
    }

    public void setClickbutton(boolean clickbutton) {
        this.clickbutton = clickbutton;
    }
}
