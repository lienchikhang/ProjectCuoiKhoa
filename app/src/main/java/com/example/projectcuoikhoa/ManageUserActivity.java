package com.example.projectcuoikhoa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.projectcuoikhoa.Adapter.UserAdapter;
import com.example.projectcuoikhoa.Obj.User;

import java.util.ArrayList;

public class ManageUserActivity extends AppCompatActivity {
    RecyclerView rvList;
    ArrayList<User> list;
    ImageView btnBackManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        rvList = findViewById(R.id.rvGridUserManageAdmin);
        btnBackManage = findViewById(R.id.btnBackManage);

        btnBackManage.setOnClickListener(view -> finish());
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        list = UserDataQuery.getNormalUser(this,"admin");
        UserAdapter userAdapter = new UserAdapter(list);
//        userAdapter1.setCallBackAdmin(this);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvList.setAdapter(userAdapter);
        rvList.setLayoutManager(gridLayoutManager);
    }
}