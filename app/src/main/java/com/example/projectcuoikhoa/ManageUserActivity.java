package com.example.projectcuoikhoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projectcuoikhoa.Adapter.UserAdapter;

import java.util.ArrayList;

public class ManageUserActivity extends AppCompatActivity {
    RecyclerView rvList;
    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        rvList = findViewById(R.id.rvGridUserManageAdmin);

        list = UserDataQuery.getNormalUser(this,"admin");
        UserAdapter userAdapter1 = new UserAdapter(list);
//        userAdapter1.setCallBackAdmin(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setAdapter(userAdapter1);
        rvList.setLayoutManager(linearLayoutManager);
    }
}