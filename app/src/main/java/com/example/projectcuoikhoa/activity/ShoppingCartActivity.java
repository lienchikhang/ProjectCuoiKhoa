package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikhoa.Obj.CartShoes;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Adapter.ShoppingCartAdapter;
import com.example.projectcuoikhoa.Ultils;
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
    EditText Address;
    Button Buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        LoadData();
        SukienthemlistCart();
        Anhxa();
        SetPriceSumAndDelivery();
        shoppingCartAdapter = new ShoppingCartAdapter(listCart, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvlistCart.setAdapter(shoppingCartAdapter);
        rvlistCart.setLayoutManager(linearLayoutManager);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
                finish();
            }
        });
        Buy.setOnClickListener(view -> ClickBuy());
    }
    void Anhxa(){
        rvlistCart = findViewById(R.id.rvListCart);
        btnback = findViewById(R.id.btnback);
        PriceSum = findViewById(R.id.PriceSum);
        PriceDelivery = findViewById(R.id.PriceDelivery);
        Buy=findViewById(R.id.BuyCart);
    }
    void ClickBuy(){
        SharedPreferences sharedPreferencesInfo = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
        if(sharedPreferencesInfo.getInt("id",0)==0){
            Toast.makeText(this,"Vui long dang nhap",Toast.LENGTH_LONG).show();
            return;
        }
        if(listCart.size()==0){
            Toast.makeText(this,"San pham trong",Toast.LENGTH_LONG).show();
            return;
        }
        Intent i=new Intent(this,InfoCartForCusActivity.class);
        startActivity(i);
    }
    void SukienthemlistCart() {
        Intent i = getIntent();
        String id = i.getStringExtra("ID");
        if(id==null){
            return;
        }
        String Size = i.getStringExtra("Size");
        Shoes shoes = ShoeDataQuery.getShoes(this, Integer.parseInt(id));
        CartShoes cartShoes = new CartShoes(shoes, 1, Size, shoes.getImage());
        for(int a=0;a<listCart.size();a++){
            if(listCart.get(a).equals(cartShoes.getShoes().getName(),Size)){
                int OldQuan=listCart.get(a).getQuantity();
                listCart.get(a).setQuantity(OldQuan+1);
                return;
            }
        }
        listCart.add(cartShoes);
    }

    public void onItemAdd(CartShoes cartShoes, int position) {
        cartShoes.setQuantity(cartShoes.getQuantity()+1);
        listCart.set(position,cartShoes);
        SetPriceSumAndDelivery();
        shoppingCartAdapter.notifyItemChanged(position);

    }

    public void onItemMinus(CartShoes cartShoes, int position) {

        if (cartShoes.getQuantity() == 1) {
            onItemDelete(cartShoes, position);
        } else {
            cartShoes.setQuantity(cartShoes.getQuantity()-1);
            listCart.set(position,cartShoes);
            SetPriceSumAndDelivery();
            shoppingCartAdapter.notifyItemChanged(position);
        }
    }

    public void onItemDelete(CartShoes cartShoes, int position) {
        listCart.remove(cartShoes);
        SetPriceSumAndDelivery();
        shoppingCartAdapter.notifyItemRemoved(position);
    }


    void SaveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Cart", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String Save = gson.toJson(listCart);
        editor.putString("listCart", Save);
        editor.apply();
    }

    void LoadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Cart", MODE_PRIVATE);
        Gson gson = new Gson();
        String Save = sharedPreferences.getString("listCart", null);
        Type type = new TypeToken<ArrayList<CartShoes>>() {
        }.getType();
        listCart = gson.fromJson(Save, type);
        if (listCart == null) {
            listCart = new ArrayList<>();
        }
    }
    void SetPriceSumAndDelivery() {
        int PriceS = 0;
        int PriceD = 0;
        for (CartShoes cartshoes : listCart
        ) {
            PriceS += cartshoes.getQuantity() * cartshoes.getShoes().getPrice();
            PriceD += cartshoes.getQuantity() * 2000;
        }
        PriceSum.setText(Ultils.ConvertToVND(PriceS+PriceD));
        PriceDelivery.setText(Ultils.ConvertToVND(PriceD));
    }


}