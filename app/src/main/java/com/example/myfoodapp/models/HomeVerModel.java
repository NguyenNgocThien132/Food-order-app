package com.example.myfoodapp.models;

public class HomeVerModel {
    String id_thucan;
    int image;
    String name;
    String time;
    String racking;
    float price;

    public HomeVerModel(int image, String name, String time, String racking, float price) {

        this.image = image;
        this.name = name;
        this.time = time;
        this.racking = racking;
        this.price = price;
    }

    public HomeVerModel(String id_thucan, int image, String name, String time, String racking, float price) {
        this.id_thucan = id_thucan;
        this.image = image;
        this.name = name;
        this.time = time;
        this.racking = racking;
        this.price = price;
    }

    public String getId_thucan() {
        return id_thucan;
    }

    public void setId_thucan(String id_thucan) {
        this.id_thucan = id_thucan;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRacking() {
        return racking;
    }

    public void setRacking(String racking) {
        this.racking = racking;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
