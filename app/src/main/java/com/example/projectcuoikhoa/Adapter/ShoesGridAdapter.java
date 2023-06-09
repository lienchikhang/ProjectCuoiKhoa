package com.example.projectcuoikhoa.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Ultils;
import com.example.projectcuoikhoa.activity.DetailActivity;

import java.util.ArrayList;

public class ShoesGridAdapter extends RecyclerView.Adapter<ShoesGridAdapter.ClothesGridViewHolder> {
    ArrayList<Shoes> shoesArrayList;

    Context context;

    UserGridCallBack userGridCallBack;

    public ShoesGridAdapter(ArrayList<Shoes> shoesArrayList, UserGridCallBack userGridCallBack) {
        this.shoesArrayList = shoesArrayList;
        this.userGridCallBack = userGridCallBack;
    }


    public interface UserGridCallBack {
        void onItemClick(String id);

        void onLikeBtnClick(String idShoe, View view);

        void onLikeCancel(String id);
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

    @Override
    public void onBindViewHolder(@NonNull ClothesGridViewHolder holder, int position) {
        Shoes item = shoesArrayList.get(position);
        holder.imageView.setImageBitmap(Ultils.convertToBitmapFromAssets(context, item.getImage()));
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(Ultils.ConvertToVND(item.getPrice()));
        holder.itemView.setOnClickListener(view -> userGridCallBack.onItemClick(String.valueOf(item.getId())));
        holder.btnLike.setOnClickListener(view -> {
            holder.btnLike.setVisibility(View.INVISIBLE);
            holder.btnLike2.setVisibility(View.VISIBLE);
            userGridCallBack.onLikeBtnClick(String.valueOf(item.getId()), holder.btnLike);
        });

        holder.btnLike2.setOnClickListener(view -> {
            holder.btnLike2.setVisibility(View.INVISIBLE);
            holder.btnLike.setVisibility(View.VISIBLE);
            userGridCallBack.onLikeCancel(String.valueOf(item.getId()));
        });

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
            btnLike2.setVisibility(View.INVISIBLE);
//            tvTypeG = itemView.findViewById(R.id.tvTypeG);
//            lnProduct = itemView.findViewById(R.id.lnProduct);

        }
    }
}
