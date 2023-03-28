package com.example.projectcuoikhoa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikhoa.Fragment.MainFragment;

import java.util.ArrayList;

public class ClothesGridAdapter extends RecyclerView.Adapter<ClothesGridAdapter.ClothesGridViewHolder> {
    ArrayList<Clothes> clothesArrayList;

    Context context;

    UserGridCallBack userGridCallBack;

    public ClothesGridAdapter(ArrayList<Clothes> clothesArrayList, UserGridCallBack userGridCallBack) {
        this.clothesArrayList = clothesArrayList;
        this.userGridCallBack = userGridCallBack;
    }


    public interface UserGridCallBack {
        void onItemClick(String id);
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
        Clothes item = clothesArrayList.get(position);
        holder.imageView.setImageBitmap(Ultils.convertToBitmapFromAssets(context, item.getImage()));
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice());
        holder.itemView.setOnClickListener(view -> userGridCallBack.onItemClick(item.getId()));
        holder.tvTypeG.setText("Loại: " + item.getType());
//        holder.lnProduct.setOnClickListener(view -> userGridCallBack.onItemClick(item.getId()));
    }

    @Override
    public int getItemCount() {
        return clothesArrayList.size();
    }

//    public interface UserGridCallBack {
//        void onItemClick(String id);
//    }

    class ClothesGridViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName, tvPrice, tvTypeG;

        public ClothesGridViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTypeG = itemView.findViewById(R.id.tvTypeG);
//            lnProduct = itemView.findViewById(R.id.lnProduct);
        }
    }
}
