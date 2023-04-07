package com.example.projectcuoikhoa.Adapter;

import android.app.Activity;
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

import java.util.ArrayList;

public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.ClothesViewHolder>{
    ArrayList<Shoes> list;
    Context context;
    ShoesCallBack shoesCallBack;

    Activity main;

    public ShoesAdapter(ArrayList<Shoes> list, ShoesCallBack shoesCallBack) {
        this.list = list;
        this.shoesCallBack = shoesCallBack;
    }
    public void setCallBack(ShoesCallBack callBack) {this.shoesCallBack = callBack;}
    @NonNull
    @Override
    public ClothesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View clothesView = inflater.inflate(R.layout.product_layout, parent, false);
        ClothesViewHolder viewHolder = new ClothesViewHolder(clothesView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesViewHolder holder, int position) {
        Shoes item = list.get(position);

        holder.imageView.setImageBitmap(Ultils.convertToBitmapFromAssets(context,item.getImage()));
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice());
        holder.itemView.setOnClickListener(view -> shoesCallBack.onItemClick(String.valueOf(item.getId())));


//        holder.ivEdit.setOnClickListener(view -> shoesCallBack.onItemEditClick(item, position));
//        holder.ivDelete.setOnClickListener(view -> shoesCallBack.onItemDeleteClick(item,position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClothesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, ivEdit, ivDelete;
        TextView tvName, tvPrice, tvType;
        public ClothesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvType = itemView.findViewById(R.id.tvType);
//            ivDelete = itemView.findViewById(R.id.ivDelete);
//            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
    public interface ShoesCallBack {
        void onItemClick(String id);

        void onItemDeleteClick(Shoes sh, int position);
        void onItemEditClick(Shoes sh, int position);
    }
}
