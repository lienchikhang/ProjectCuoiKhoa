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

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.CartHolder> {
    ArrayList<CartShoes> cartShoes;
    Context context;
    CartCallback cartCallback;

    public ShoppingCartAdapter(ArrayList<CartShoes> cartShoes, CartCallback cartCallback) {
        this.cartShoes = cartShoes;
        this.cartCallback = cartCallback;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View CartView = inflater.inflate(R.layout.layout_shoppingcart_item, parent, false);
        CartHolder viewHolder = new CartHolder(CartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        CartShoes item = cartShoes.get(position);
        holder.ImgShoes.setImageBitmap(Ultils.convertToBitmapFromAssets(context, item.getImgShoes()));
        holder.Name.setText(item.getShoes().getName());
        holder.Size.setText(item.getSize());
        holder.Quantity.setText(Integer.toString(item.getQuantity()));
        holder.PriceCart.setText(String.valueOf(item.getShoes().getPrice()));
        holder.add.setOnClickListener(view -> cartCallback.onItemAdd(item,position));
        holder.minus.setOnClickListener(view -> cartCallback.onItemMinus(item,position));
        holder.delete.setOnClickListener(view -> cartCallback.onItemDelete(item,position));
    }

    @Override
    public int getItemCount() {
        if(cartShoes==null){
            return 0;
        }
        return cartShoes.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        ImageView ImgShoes;
        TextView Size;
        TextView Quantity;
        TextView PriceCart;
        TextView Name;


        ImageView add, minus, delete;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            Quantity=itemView.findViewById(R.id.QuantityCart);
            ImgShoes = itemView.findViewById(R.id.ImgSP);
            Size = itemView.findViewById(R.id.SizeCart);
            PriceCart = itemView.findViewById(R.id.PriceCart);
            Name = itemView.findViewById(R.id.NameSp);
            add = itemView.findViewById(R.id.addBtn);
            minus = itemView.findViewById(R.id.minusBtn);
            delete = itemView.findViewById(R.id.DeleteCart);
        }

    }

    public interface CartCallback {
        void onItemAdd(CartShoes cartShoes, int position);

        void onItemMinus(CartShoes cartShoes, int position);
        void onItemDelete(CartShoes cartShoes,int position);

    }
}
