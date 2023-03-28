package com.example.projectcuoikhoa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder>{
    ArrayList<Clothes> list;
    Context context;
    UserCallBack userCallBack;

    public ClothesAdapter(ArrayList<Clothes> list, UserCallBack userCallBack) {
        this.list = list;
        this.userCallBack = userCallBack;
    }

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
        Clothes item = list.get(position);

        holder.imageView.setImageBitmap(Ultils.convertToBitmapFromAssets(context,item.getImage()));
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice());
        holder.itemView.setOnClickListener(view -> userCallBack.onItemClick(item.getId()));
        holder.tvType.setText("Loại: " + item.getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClothesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName, tvPrice, tvType;
        public ClothesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvType = itemView.findViewById(R.id.tvType);
        }
    }
    public interface UserCallBack {
        void onItemClick(String id);
    }
}
