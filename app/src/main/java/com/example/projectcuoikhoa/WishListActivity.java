package com.example.projectcuoikhoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.Adapter.ShoesAdapterAdmin;
import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.activity.DetailActivity;

import java.util.ArrayList;

public class WishListActivity extends AppCompatActivity implements ShoesAdapterAdmin.ShoesCallBackAdmin, ShoesAdapter.ShoesCallBack, ShoesGridAdapter.UserGridCallBack{

    RecyclerView rvWishList;
    ArrayList<Shoes> list;
    ShoesAdapter shoesAdapter;
    ShoesGridAdapter shoesGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        rvWishList = findViewById(R.id.rvWishList);
//        Intent i = getIntent();
//        String test = i.getStringExtra("idU");
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
        int idUserIn = sharedPreferences.getInt("id", MODE_PRIVATE);
        list = new ArrayList<>();
//        resetData(idUserIn);
        list = ShoeDataQuery.getAllWishList(this,idUserIn);
        shoesGridAdapter = new ShoesGridAdapter(list,this);
        shoesAdapter = new ShoesAdapter(list,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rvWishList.setAdapter(shoesGridAdapter);
        rvWishList.setLayoutManager(gridLayoutManager);
    }
    void resetData(int id) {
        list.clear();
        list.addAll(ShoeDataQuery.getAllWishList(this,id));
        shoesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String id) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("id", id);
        Toast.makeText(this, "idshoe: " + id, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    @Override
    public void onItemDeleteClick(Shoes sh, int position) {

    }

    @Override
    public void onItemEditClick(Shoes sh, int position) {

    }
}