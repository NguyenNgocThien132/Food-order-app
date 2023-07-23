package com.example.myfoodapp.models;

public class CartModel {

    int id_cart;
    int image;
    String name;
    float price;
    float soluong;
    float giatien;

    public CartModel(int image, String name, float price, int soluong) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.soluong = soluong;
    }

    public CartModel(int id_cart, int image, String name, float price, float soluong) {
        this.id_cart = id_cart;
        this.image = image;
        this.name = name;
        this.price = price;
        this.soluong = soluong;
    }

    public float getGiatien() {
        return soluong*price;
    }

    public void setGiatien(float giatien) {
        this.giatien = giatien;
    }

    public float getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getId_cart() {
        return id_cart;
    }

    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
