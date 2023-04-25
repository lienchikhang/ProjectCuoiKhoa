package com.example.projectcuoikhoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectcuoikhoa.DBhelper.CartDBHelper;
import com.example.projectcuoikhoa.Obj.CartShoes;

import java.util.ArrayList;

public class CartDataQuery {
    public static long insert(Context context, CartShoes cartShoes, int idUser) {
        CartDBHelper cartDBHelper = new CartDBHelper(context);
        SQLiteDatabase sqLiteDatabase = cartDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ultils.COLUMN_USER_ID_CART,idUser);
        String name=cartShoes.getShoes().getName();
        values.put(Ultils.COLUMN_SHOE_NAME_CART,cartShoes.getShoes().getName());
        values.put(Ultils.COLUMN_QUANTITY_CART,cartShoes.getQuantity());
        values.put(Ultils.COLUMN_SHOE_SIZE_CART,cartShoes.getSize());
        long rs = sqLiteDatabase.insert(Ultils.TABLE_CART,null,values);
        return (rs);
    }
    public static ArrayList<CartShoes> getAllByID(Context context, int idUser) {
        ArrayList<CartShoes> cartShoes=new ArrayList<>();
        CartDBHelper cartDBHelper = new CartDBHelper(context);
        SQLiteDatabase db = cartDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_CART+" Where "+Ultils.COLUMN_USER_ID_CART+"="+idUser, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            String Name=cs.getString(2);
            Shoes shoes=ShoeDataQuery.GetByName(context, Name);
            int Quantity=cs.getInt(3);
            String Size=cs.getString(4);
            cartShoes.add(new CartShoes(shoes,Quantity,Size,shoes.getImage()));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return cartShoes;
    }
}
