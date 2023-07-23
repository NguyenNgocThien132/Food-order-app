package com.example.myfoodapp.models;

public class DetailedDailyModel {
    //khóa chính
    String id_thucan;

    int image;
    String name;
    String description;
    String rating;
    float price;
    String timing;

    //khóa ngoại
    String id_loaithucan;
    String id_featured;


    public DetailedDailyModel() {
    }

    public DetailedDailyModel(String id_thucan, int image, String name, String description, String rating, float price, String timing, String id_loaithucan, String id_featured) {
        this.id_thucan = id_thucan;
        this.image = image;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.timing = timing;
        this.id_loaithucan = id_loaithucan;
        this.id_featured = id_featured;
    }

    public String getId_featured() {
        return id_featured;
    }

    public void setId_featured(String id_featured) {
        this.id_featured = id_featured;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getId_loaithucan() {
        return id_loaithucan;
    }

    public void setId_loaithucan(String id_loaithucan) {
        this.id_loaithucan = id_loaithucan;
    }
}
