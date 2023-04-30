package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.projectcuoikhoa.Adapter.ManageCartAdapter;
import com.example.projectcuoikhoa.Adapter.ShoppingCartAdapter;
import com.example.projectcuoikhoa.CartDataQuery;
import com.example.projectcuoikhoa.Obj.CartShoes;
import com.example.projectcuoikhoa.R;

import java.util.ArrayList;

public class ManageCartActivity extends AppCompatActivity implements ManageCartAdapter.ManageCartCallBack {
    ArrayList<CartShoes> list;
    ManageCartAdapter manageCartAdapter;
    RecyclerView recyclerView;
    ImageView btnBackManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Anhxa();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        int idUserIn = sharedPreferences.getInt("id",MODE_PRIVATE);
        list = CartDataQuery.getAll(this);
        manageCartAdapter = new ManageCartAdapter(list, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(manageCartAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        btnBackManage.setOnClickListener(view -> finish());
    }

    void Anhxa() {
        recyclerView = findViewById(R.id.ManageListCart);
        btnBackManage = findViewById(R.id.btnBackManage);
    }

    public void onItemDelete(CartShoes cartShoes, int position) {
        list.remove(cartShoes);
        CartDataQuery.delete(this,cartShoes.getIdCart());
        manageCartAdapter.notifyItemRemoved(position);
    }
}