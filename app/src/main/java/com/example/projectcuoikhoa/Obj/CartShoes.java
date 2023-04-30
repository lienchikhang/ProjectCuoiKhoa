package com.example.projectcuoikhoa.Obj;

import com.example.projectcuoikhoa.Shoes;

import java.io.Serializable;
import java.util.ArrayList;


public class CartShoes implements Serializable {
    Shoes shoes;
    int idCart;

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    int quantity;
    String Size;
    String Address;
    String PhoneNumber;
    int idUserCart;

    public int getIdUserCart() {
        return idUserCart;
    }

    public void setIdUserCart(int idUserCart) {
        this.idUserCart = idUserCart;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getImgShoes() {
        return ImgShoes;
    }

    public void setImgShoes(String imgShoes) {
        ImgShoes = imgShoes;
    }

    public CartShoes(Shoes shoes, int quantity, String size, String address, String phoneNumber, String imgShoes,int idCart) {
        this.shoes = shoes;
        this.quantity = quantity;
        Size = size;
        Address = address;
        PhoneNumber = phoneNumber;
        ImgShoes = imgShoes;
        this.idCart=idCart;
    }

    public CartShoes(Shoes shoes, int quantity, String size, String address, String phoneNumber, String imgShoes, int idCart, int idUserCart) {
        this.shoes = shoes;
        this.idCart = idCart;
        this.quantity = quantity;
        Size = size;
        Address = address;
        PhoneNumber = phoneNumber;
        this.idUserCart = idUserCart;
        ImgShoes = imgShoes;
        this.idUserCart = idUserCart;
    }

    String ImgShoes;

    public CartShoes(Shoes shoes, int quantity, String size, String ImgShoes) {
        this.shoes = shoes;
        this.quantity = quantity;
        this.ImgShoes = ImgShoes;
        Size = size;
    }

    public CartShoes() {
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

    public boolean equals(String name, String size) {
        return this.getShoes().getName().equals(name) && this.getSize().equals(size);
    }
}

