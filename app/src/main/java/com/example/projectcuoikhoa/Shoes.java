package com.example.projectcuoikhoa;

public class Shoes {
    int id;
    String name;
    String image;

    String type;

    String price;

    public Shoes(String name, String image, String price, String type) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
    }

    public Shoes(int id, String name, String image, String price, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
