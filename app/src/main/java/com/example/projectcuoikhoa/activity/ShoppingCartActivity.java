package com.example.projectcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.projectcuoikhoa.CartShoes;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.ShoppingCartAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartAdapter.CartCallback {
    RecyclerView rvlistCart;
    ShoppingCartAdapter shoppingCartAdapter;

    ArrayList<CartShoes> listCart=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Intent i=getIntent();
        String id=i.getStringExtra("ID");
        String Size=i.getStringExtra("Size");
        Shoes shoes= ShoeDataQuery.getShoes(this,Integer.parseInt(id));
        CartShoes cartShoes=new CartShoes(shoes,1,Size,shoes.getImage());
        listCart.add(cartShoes);
        rvlistCart=findViewById(R.id.rvListCart);
        shoppingCartAdapter=new ShoppingCartAdapter(listCart,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvlistCart.setAdapter(shoppingCartAdapter);
        rvlistCart.setLayoutManager(linearLayoutManager);

    }
    public void onItemAdd(CartShoes cartShoes, int position){
        cartShoes.setQuantity(cartShoes.getQuantity()+1);
    }
    public void onItemMinus(CartShoes cartShoes, int position){

        if(cartShoes.getQuantity()==1){
            onItemDelete(cartShoes,position);
        }
        else {
            cartShoes.setQuantity(cartShoes.getQuantity()-1);
        }
    }
    public void onItemDelete(CartShoes cartShoes,int position){
        listCart.remove(cartShoes);
    }
}