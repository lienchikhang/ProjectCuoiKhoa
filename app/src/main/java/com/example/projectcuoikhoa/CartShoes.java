package com.example.projectcuoikhoa;

import java.io.Serializable;
import java.util.ArrayList;


public class CartShoes implements Serializable {
    Shoes shoes;
    int quantity;
    String Size;

    public String getImgShoes() {
        return ImgShoes;
    }

    public void setImgShoes(String imgShoes) {
        ImgShoes = imgShoes;
    }

    String ImgShoes;

    public CartShoes(Shoes shoes, int quantity, String size,String ImgShoes) {
        this.shoes = shoes;
        this.quantity = quantity;
        this.ImgShoes=ImgShoes;
        Size = size;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
}