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

import java.util.ArrayList;

public class ShoesWishListAdapter extends RecyclerView.Adapter<ShoesWishListAdapter.ClothesGridViewHolder> {
    ArrayList<Shoes> shoesArrayList;

    Context context;
    ShoesWishListAdapter.UserGridCallBack userGridCallBack;

    public ShoesWishListAdapter(ArrayList<Shoes> shoesArrayList, UserGridCallBack userGridCallBack) {
        this.shoesArrayList = shoesArrayList;
        this.userGridCallBack = userGridCallBack;
    }

    @NonNull
    @Override
    public ClothesGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View clothesView = inflater.inflate(R.layout.layout_item_grid, parent, false);
        ClothesGridViewHolder gridViewHolder = new ClothesGridViewHolder(clothesView);
        return gridViewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ClothesGridViewHolder holder, int position) {
        Shoes item = shoesArrayList.get(position);
        holder.imageView.setImageBitmap(Ultils.convertToBitmapFromAssets(context, item.getImage()));
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(Ultils.ConvertToVND(item.getPrice()));
        holder.itemView.setOnClickListener(view -> userGridCallBack.onItemClick(String.valueOf(item.getId())));
        holder.btnLike2.setVisibility(View.VISIBLE);
//        holder.tvTypeG.setText("Loại: " + item.getType());
        holder.btnLike2.setOnClickListener(view -> {
            holder.btnLike2.setVisibility(View.INVISIBLE);
            userGridCallBack.onLikeCancel(String.valueOf(item.getId()));
        });
    }

    public interface UserGridCallBack {
        void onItemClick(String id);

        void onLikeBtnClick(String idShoe, View view);

        void onLikeCancel(String id);
    }



    @Override
    public int getItemCount() {
        return shoesArrayList.size();
    }

    class ClothesGridViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, btnLike,btnLike2;


        TextView tvName, tvPrice, tvTypeG;

        public ClothesGridViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnLike2 = itemView.findViewById(R.id.btnLikeCancel);
            btnLike.setVisibility(View.INVISIBLE);

//            tvTypeG = itemView.findViewById(R.id.tvTypeG);
//            lnProduct = itemView.findViewById(R.id.lnProduct);

        }
    }
}
