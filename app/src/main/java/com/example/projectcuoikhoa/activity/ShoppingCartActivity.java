package com.example.projectcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Size;
import android.widget.TextView;

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
        SukienthemlistCart();
        TextView PriceBig;
        PriceBig=findViewById(R.id.PriceSum);
        rvlistCart=findViewById(R.id.rvListCart);
        shoppingCartAdapter=new ShoppingCartAdapter(listCart,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvlistCart.setAdapter(shoppingCartAdapter);
        rvlistCart.setLayoutManager(linearLayoutManager);

    }
    void SukienthemlistCart(){
        Intent i=getIntent();
        String id=i.getStringExtra("ID");
        String Size=i.getStringExtra("Size");
        Shoes shoes= ShoeDataQuery.getShoes(this,Integer.parseInt(id));
        CartShoes cartShoes=new CartShoes(shoes,1,Size,shoes.getImage());
        listCart.add(cartShoes);

    }
    public void onItemAdd(CartShoes cartShoes, int position){
        listCart.remove(cartShoes);
        listCart.add(new CartShoes(cartShoes.getShoes(),cartShoes.getQuantity()+1,cartShoes.getSize(),cartShoes.getImgShoes()));
        shoppingCartAdapter.notifyItemChanged(position);
    }
    public void onItemMinus(CartShoes cartShoes, int position){

        if(cartShoes.getQuantity()==1){
            onItemDelete(cartShoes,position);
        }
        else {
            listCart.remove(cartShoes);
            listCart.add(new CartShoes(cartShoes.getShoes(),cartShoes.getQuantity()-1,cartShoes.getSize(),cartShoes.getImgShoes()));
            shoppingCartAdapter.notifyItemChanged(position);
        }
    }
    public void onItemDelete(CartShoes cartShoes,int position){
        listCart.remove(cartShoes);
        shoppingCartAdapter.notifyItemRemoved(position);
    }
    public int SumPriceinList(ArrayList<CartShoes> cartShoes){
        int Price=0;
        for (CartShoes cart:cartShoes
             ) {
            Price+= cart.getQuantity()*cart.getShoes().getPrice();
        }
        return Price;
    }

}