package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Anhxa();
        list = CartDataQuery.getAll(this);
        manageCartAdapter = new ManageCartAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(manageCartAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    void Anhxa() {
        recyclerView = findViewById(R.id.ManageListCart);
    }

    public void onItemDelete(CartShoes cartShoes, int position) {
        list.remove(cartShoes);
        CartDataQuery.delete(this,cartShoes.getIdCart());
        manageCartAdapter.notifyItemRemoved(position);
    }
}