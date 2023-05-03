package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ShoesGridAdapter.UserGridCallBack  {
    RecyclerView rvgrid;
    ArrayList<Shoes> list;

    ImageView btnback;
    ShoesGridAdapter shoesGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        Intent i=getIntent();
        String text=i.getStringExtra("text");
        list=ShoeDataQuery.SearchByName(this,text);
        rvgrid = findViewById(R.id.rvGridProductList);
        btnback=findViewById(R.id.btnbackCart);
        btnback.setOnClickListener(view -> finish());
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

    @Override
    public void onLikeBtnClick(String idShoe, View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        int idUserIn = sharedPreferences.getInt("id", MODE_PRIVATE);
        if (idUserIn == 0) {
            Toast.makeText(SearchActivity.this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
            return;
        }
        Shoes sh = ShoeDataQuery.getShoes(this, Integer.parseInt(idShoe));
        sharedPreferences = getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("id", MODE_PRIVATE);
        long id = ShoeDataQuery.insertToWishList(this, sh, idUser);
        if (id > 0) {
            Toast.makeText(SearchActivity.this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SearchActivity.this, "Đã tồn tại trong yêu thích", Toast.LENGTH_SHORT).show();
        }
    }
    public int idUserIn;

    @Override
    public void onLikeCancel(String id) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        idUserIn = sharedPreferences.getInt("id", MODE_PRIVATE);
        if (idUserIn == 0) {
            Toast.makeText(SearchActivity.this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean rs = ShoeDataQuery.deleteFromWishList(this,Integer.parseInt(id),idUserIn);
        if(rs) {
            Toast.makeText(SearchActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
            resetData();
        } else {
            Toast.makeText(SearchActivity.this, "Xoá không thành công", Toast.LENGTH_SHORT).show();
        }
    }
    void resetData() {
        list.clear();
        list.addAll(ShoeDataQuery.getAllWishList(this,idUserIn));

    }

}