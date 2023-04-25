package com.example.projectcuoikhoa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.projectcuoikhoa.Adapter.HistoryCartAdapter;
import com.example.projectcuoikhoa.Obj.CartShoes;

import java.util.ArrayList;

public class HistoryCartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<CartShoes> listCart;
    HistoryCartAdapter historyCartAdapter;
    ImageView btnBack;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cart);
        recyclerView=findViewById(R.id.listforhistoryCart);
        btnBack=findViewById(R.id.btnbackCart);
        Intent i=getIntent();
        id=i.getIntExtra("id",0);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        listCart=CartDataQuery.getAllByID(this,id);
        historyCartAdapter = new HistoryCartAdapter(listCart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(historyCartAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        btnBack.setOnClickListener(view -> finish());
    }
}