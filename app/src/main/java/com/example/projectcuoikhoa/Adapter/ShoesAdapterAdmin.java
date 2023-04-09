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

public class ShoesAdapterAdmin extends RecyclerView.Adapter<ShoesAdapterAdmin.ShoesViewHolder>{
    ArrayList<Shoes> list;
    Context context;
    ShoesCallBackAdmin shoesCallBackAdmin;

    public ShoesAdapterAdmin(ArrayList<Shoes> list, ShoesCallBackAdmin shoesCallBackAdmin) {
        this.list = list;
        this.shoesCallBackAdmin = shoesCallBackAdmin;
    }


    public void setCallBackAdmin(ShoesCallBackAdmin shoesCallBackAdmin) {this.shoesCallBackAdmin = shoesCallBackAdmin;}



    @NonNull
    @Override
    public ShoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View clothesView = inflater.inflate(R.layout.layout_shoe_admin, parent, false);
        ShoesViewHolder viewHolder = new ShoesViewHolder(clothesView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesViewHolder holder, int position) {
        Shoes item = list.get(position);
        holder.imageView.setImageBitmap(Ultils.convertToBitmapFromAssets(context,item.getImage()));
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        holder.ivEdit.setOnClickListener(view -> shoesCallBackAdmin.onItemEditClick(item, position));
        holder.ivDelete.setOnClickListener(view -> shoesCallBackAdmin.onItemDeleteClick(item,position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ShoesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView , ivEdit, ivDelete;
        TextView tvName, tvPrice, tvType;
        public ShoesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvType = itemView.findViewById(R.id.tvType);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
    public interface ShoesCallBackAdmin {
        void onItemClick(String id);

        void onItemDeleteClick(Shoes sh, int position);
        void onItemEditClick(Shoes sh, int position);
    }
}
