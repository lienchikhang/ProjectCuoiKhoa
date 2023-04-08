package com.example.projectcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectcuoikhoa.CartShoes;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.ShoppingCartAdapter;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartAdapter.CartCallback {
    RecyclerView rvlistCart;
    ShoppingCartAdapter shoppingCartAdapter;

    ArrayList<CartShoes> listCart;
    ImageView btnback;
    TextView PriceSum;
    TextView PriceDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        LoadData();
        SukienthemlistCart();
        rvlistCart=findViewById(R.id.rvListCart);
        btnback = findViewById(R.id.btnback);
        PriceSum=findViewById(R.id.PriceSum);
        PriceDelivery=findViewById(R.id.PriceDelivery);
        SetPriceSumAndDelivery();
        shoppingCartAdapter=new ShoppingCartAdapter(listCart,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvlistCart.setAdapter(shoppingCartAdapter);
        rvlistCart.setLayoutManager(linearLayoutManager);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
                finish();
            }
        });
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
        SetPriceSumAndDelivery();
        shoppingCartAdapter.notifyItemChanged(position);
    }
    public void onItemMinus(CartShoes cartShoes, int position){

        if(cartShoes.getQuantity()==1){
            onItemDelete(cartShoes,position);
        }
        else {
            listCart.remove(cartShoes);
            listCart.add(new CartShoes(cartShoes.getShoes(),cartShoes.getQuantity()-1,cartShoes.getSize(),cartShoes.getImgShoes()));
            SetPriceSumAndDelivery();
            shoppingCartAdapter.notifyItemChanged(position);
        }
    }
    public void onItemDelete(CartShoes cartShoes,int position){
        listCart.remove(cartShoes);
        SetPriceSumAndDelivery();
        shoppingCartAdapter.notifyItemRemoved(position);
    }
    void SaveData(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson=new Gson();
        String Save=gson.toJson(listCart);
        editor.putString("listCart",Save);
        editor.apply();
    }
    void LoadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String Save=sharedPreferences.getString("listCart",null);
        Type type = new TypeToken<ArrayList<CartShoes>>() {}.getType();
        listCart=gson.fromJson(Save,type);
        if(listCart==null){
            listCart=new ArrayList<>();
        }
    }
    void SetPriceSumAndDelivery(){
        int PriceS=0;
        int PriceD=0;
        for (CartShoes cartshoes:listCart
             ) {
            PriceS+=cartshoes.getQuantity()*cartshoes.getShoes().getPrice();
            PriceD+=cartshoes.getQuantity()*2000;
        }
        PriceSum.setText(Integer.toString(PriceS));
        PriceDelivery.setText(Integer.toString(PriceD));
    }

}