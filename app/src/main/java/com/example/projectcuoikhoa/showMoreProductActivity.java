package com.example.projectcuoikhoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class showMoreProductActivity extends AppCompatActivity {

    RecyclerView rvGridProduct;

    ArrayList<Clothes> arrayList;


    ClothesGridAdapter clothesGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more_product);

//        Intent i = getIntent();
//        String id = i.getStringExtra("id");
//        rvGridProduct = findViewById(R.id.rvGrid);
//        LoadData();
////        arrayList = (ArrayList<Clothes>) getIntent().getSerializableExtra("arrayList");
//        clothesGridAdapter = new ClothesGridAdapter(arrayList,this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        rvGridProduct.setLayoutManager(gridLayoutManager);
//        rvGridProduct.setAdapter(clothesGridAdapter);



    }
    void LoadData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new Clothes(String.valueOf(i), "polo","ao_0" + i + ".png","$100"));
        }
    }
}