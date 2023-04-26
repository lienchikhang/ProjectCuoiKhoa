package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcuoikhoa.CartDataQuery;
import com.example.projectcuoikhoa.Obj.CartShoes;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.Obj.User;
import com.example.projectcuoikhoa.UserDataQuery;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class InfoCartForCusActivity extends AppCompatActivity {

    EditText name, phone, address, Email;
    Button ThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cart_for_cus);
        Anhxa();
        SetInfo();
        ThanhToan.setOnClickListener(view -> Sukien());
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    void Sukien() {
//        if(Check()){
//            Toast.makeText(this,"Vui long dien du thong tin",Toast.LENGTH_LONG).show();
//            return;
//        }
//        SharedPreferences sharedPreferences =getSharedPreferences("shared preferences Cart", Context.MODE_PRIVATE);
//        sharedPreferences.edit().remove("listCart").apply();
//        Toast.makeText(this, "dat hang thanh cong", Toast.LENGTH_LONG).show();
//        startActivity(new Intent(this,MainActivity.class));
        Intent i=getIntent();
        if(Check()){
            Toast.makeText(this,"Vui long dien du thong tin",Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences sharedPreferencesInfo = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
        int id=sharedPreferencesInfo.getInt("id",0);
        SharedPreferences sharedPreferences =getSharedPreferences("shared preferences Cart", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String Save = i.getStringExtra("listcart");
        Type type = new TypeToken<ArrayList<CartShoes>>() {
        }.getType();
        ArrayList<CartShoes> listCart = gson.fromJson(Save, type);
        for(int a=0;a<listCart.size();a++){
            CartDataQuery.insert(this,listCart.get(a),id,address.getText().toString(),phone.getText().toString());
        }
        sharedPreferences.edit().remove("listCart").apply();
        Toast.makeText(this, "dat hang thanh cong", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,MainActivity.class));
    }
    boolean Check(){
        if(name.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||address.getText().toString().isEmpty()||Email.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }

    void SetInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
        int id=sharedPreferences.getInt("id",0);
        User user=UserDataQuery.getUser(this,id);
        name.setText(user.getUsername());
        phone.setText(user.getPhone());
        Email.setText(user.getEmail());
    }

    void Anhxa() {
        name = findViewById(R.id.NameInfoCart);
        phone = findViewById(R.id.PhoneInfoCart);
        address = findViewById(R.id.AddressInfoCart);
        Email = findViewById(R.id.EmailInfoCart);
        ThanhToan = findViewById(R.id.VerifyCart);
    }
}