package com.example.projectcuoikhoa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectcuoikhoa.Obj.CartShoes;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.Ultils;

import java.util.ArrayList;

public class HistoryCartAdapter extends RecyclerView.Adapter<HistoryCartAdapter.HistoryCartHolder> {
    ArrayList<CartShoes> cartShoes;
    Context context;

    public HistoryCartAdapter(ArrayList<CartShoes> cartShoes) {
        this.cartShoes = cartShoes;
    }

    @NonNull
    @Override
    public HistoryCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View CartView = inflater.inflate(R.layout.history_cart_item, parent, false);
        HistoryCartHolder viewHolder = new HistoryCartHolder(CartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryCartHolder holder, int position) {
        CartShoes item=cartShoes.get(position);
        holder.Name.setText(item.getShoes().getName());
        holder.Size.setText(item.getSize());
        holder.Quan.setText(Integer.toString(item.getQuantity()));
        holder.sumprice.setText(Ultils.ConvertToVND(item.getQuantity()*item.getShoes().getPrice()+item.getQuantity()*2000));
        holder.img.setImageBitmap(Ultils.convertToBitmapFromAssets(context.getApplicationContext(), item.getImgShoes()));
    }

    @Override
    public int getItemCount() {
        return cartShoes.size();
    }
    class HistoryCartHolder extends RecyclerView.ViewHolder{
        TextView Name,Size,Quan,sumprice;
        ImageView img;
        public HistoryCartHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.ImgHistoryCart);
            Name=itemView.findViewById(R.id.NameSPhistoryCart);
            Size=itemView.findViewById(R.id.SizeHistoryCart);
            Quan=itemView.findViewById(R.id.Quan);
            sumprice=itemView.findViewById(R.id.SumPriceHistoryCart);
        }
    }
}
