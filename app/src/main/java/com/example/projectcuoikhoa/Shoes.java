package com.example.projectcuoikhoa;

public class Shoes {
    int id;
    String name;
    String image;

    String type;

    int price;

    int idUser;
    public Shoes(){}
    public Shoes(String name,int price){
        this.name=name;
        this.price=price;
    }

    public Shoes(int id, String name, String image, int price,String type, int idUser) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
        this.idUser = idUser;
    }

    public Shoes(String name, String image, int price, String type) {
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
    }

    public Shoes(int id, String name, String image, int price, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
