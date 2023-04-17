package com.example.projectcuoikhoa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Ultils;
import com.example.projectcuoikhoa.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    ArrayList<User> list;
    Context context;


     public UserAdapter(ArrayList<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.user_layout, parent, false);
        UserAdapter.UserViewHolder viewHolder = new UserAdapter.UserViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User item = list.get(position);

//        holder.imageView.setImageBitmap(Ultils.convertToBitmapFromAssets(context,item.getImage()));
        holder.tvManagePass.setText(item.getPassword());
        holder.tvManageUsername.setText(item.getUsername());
        holder.tvManagePhone.setText(item.getPhone());
//        holder.itemView.setOnClickListener(view -> shoesCallBack.onItemClick(String.valueOf(item.getId())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
         TextView tvManageUsername, tvManagePass, tvManagePhone, tvManageLoginTime;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvManageUsername= itemView.findViewById(R.id.tvManageUsername);
            tvManagePass= itemView.findViewById(R.id.tvManageUserPass);
            tvManagePhone = itemView.findViewById(R.id.tvManageUserPhone);
            tvManageLoginTime = itemView.findViewById(R.id.tvManageLoginTime);
        }
    }
}
