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

public class ManageCartAdapter extends RecyclerView.Adapter<ManageCartAdapter.ManageCartHolder> {
    ArrayList<CartShoes> cartShoes;
    Context context;
    ManageCartCallBack manageCartCallBack;

    public ManageCartAdapter(ArrayList<CartShoes> cartShoes, ManageCartCallBack manageCartCallBack) {
        this.cartShoes = cartShoes;
        this.manageCartCallBack = manageCartCallBack;
    }

    @NonNull
    @Override
    public ManageCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View CartView = inflater.inflate(R.layout.layout_cart_history, parent, false);
        ManageCartHolder viewHolder = new ManageCartHolder(CartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ManageCartHolder holder, int position) {
        CartShoes item=cartShoes.get(position);
        holder.Name.setText(item.getShoes().getName());
        holder.Size.setText(item.getSize());
        holder.Quan.setText(Integer.toString(item.getQuantity()));
        holder.sumprice.setText(Ultils.ConvertToVND(item.getQuantity()*item.getShoes().getPrice()+item.getQuantity()*2000));
        holder.img.setImageBitmap(Ultils.convertToBitmapFromAssets(context.getApplicationContext(), item.getImgShoes()));
        holder.Address.setText(item.getAddress());
        holder.Phone.setText(item.getPhoneNumber());
        holder.btnDelete.setOnClickListener(view -> manageCartCallBack.onItemDelete(item,position));
        holder.userName.setText(String.valueOf(item.getIdUserCart()));
    }
    @Override
    public int getItemCount() {
        return cartShoes.size();
    }
    class ManageCartHolder extends RecyclerView.ViewHolder{
        TextView Name,Size,Quan,sumprice,Address,Phone, userName;
        ImageView img,btnDelete;
        public ManageCartHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.ImgHistoryCart);
            Name=itemView.findViewById(R.id.NameSPhistoryCart);
            Size=itemView.findViewById(R.id.SizeHistoryCart);
            Quan=itemView.findViewById(R.id.Quan);
            sumprice=itemView.findViewById(R.id.SumPriceHistoryCart);
            Address=itemView.findViewById(R.id.AddressCartManage);
            Phone=itemView.findViewById(R.id.SDTCartManage);
            btnDelete=itemView.findViewById(R.id.deleteManageCart);
            userName = itemView.findViewById(R.id.idUser);
        }
    }
    public interface ManageCartCallBack{
        void onItemDelete(CartShoes cartShoes,int position);
    }
}
