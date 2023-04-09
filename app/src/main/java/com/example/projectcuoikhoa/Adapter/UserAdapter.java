package com.example.projectcuoikhoa.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.User;

import java.util.ArrayList;

public class UserAdapter {
    private ArrayList<User> list;
     public UserAdapter(ArrayList<User> list) {
        this.list = list;
    }
}
