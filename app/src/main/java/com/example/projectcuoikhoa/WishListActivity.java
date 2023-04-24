package com.example.projectcuoikhoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.Adapter.ShoesAdapterAdmin;

import java.util.ArrayList;

public class WishListActivity extends AppCompatActivity implements ShoesAdapterAdmin.ShoesCallBackAdmin, ShoesAdapter.ShoesCallBack{

    RecyclerView rvWishList;
    ArrayList<Shoes> list;
    ShoesAdapter shoesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        rvWishList = findViewById(R.id.rvWishList);
//        Intent i = getIntent();
//        String test = i.getStringExtra("idU");
        SharedPreferences sharedPreferences = getSharedPreferences("idUserIn", Context.MODE_PRIVATE);
        int idUserIn = sharedPreferences.getInt("id",0);
        list = new ArrayList<>();
//        resetData(idUserIn);
        list.addAll(ShoeDataQuery.getAllWishList(this,idUserIn));
        shoesAdapter = new ShoesAdapter(list,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvWishList.setAdapter(shoesAdapter);
        rvWishList.setLayoutManager(linearLayoutManager);
    }
    void resetData(int id) {
        list.clear();
        list.addAll(ShoeDataQuery.getAllWishList(this,id));
        shoesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemDeleteClick(Shoes sh, int position) {

    }

    @Override
    public void onItemEditClick(Shoes sh, int position) {

    }
}