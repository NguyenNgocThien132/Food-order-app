package com.example.myfoodapp.models;

public class DailyMealModel {

    String id_loaithucan;
    int image;
    String name;
    String discount;
    String type;
    String description;

    public DailyMealModel() {
    }

    public DailyMealModel(String id_loaithucan, int image, String name, String discount, String description) {
        this.id_loaithucan = id_loaithucan;
        this.image = image;
        this.name = name;
        this.discount = discount;
        this.description = description;
    }

    public String getId_loaithucan() {
        return id_loaithucan;
    }

    public void setId_loaithucan(String id_loaithucan) {
        this.id_loaithucan = id_loaithucan;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
