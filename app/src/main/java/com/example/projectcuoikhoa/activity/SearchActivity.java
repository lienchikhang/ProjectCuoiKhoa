package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ShoesGridAdapter.UserGridCallBack  {
    RecyclerView rvgrid;
    ArrayList<Shoes> list;
    ShoesGridAdapter shoesGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        Intent i=getIntent();
        String text=i.getStringExtra("text");
        list=ShoeDataQuery.SerchByName(this,text);
        rvgrid = findViewById(R.id.rvGridProductList);
        shoesGridAdapter=new ShoesGridAdapter(list,this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        rvgrid.setAdapter(shoesGridAdapter);
        rvgrid.setLayoutManager(gridLayoutManager);
    }
    @Override
    public void onItemClick(String id) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }


}