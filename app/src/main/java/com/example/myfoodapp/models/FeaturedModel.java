package com.example.myfoodapp.models;

public class FeaturedModel {

    String id_featured;
    int image;
    String name;
    String desc;


    public FeaturedModel(String id_featured, int image, String name, String desc) {
        this.id_featured = id_featured;
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    public FeaturedModel(int image, String name, String desc) {
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    public String getId_featured() {
        return id_featured;
    }

    public void setId_featured(String id_featured) {
        this.id_featured = id_featured;
    }

    public FeaturedModel() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
