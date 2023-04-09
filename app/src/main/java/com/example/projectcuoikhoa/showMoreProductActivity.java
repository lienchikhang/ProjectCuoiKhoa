package com.example.projectcuoikhoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;

import java.util.ArrayList;

public class showMoreProductActivity extends AppCompatActivity {

    RecyclerView rvGridProduct;

    ArrayList<Shoes> arrayList;


    ShoesGridAdapter shoesGridAdapter;

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

}