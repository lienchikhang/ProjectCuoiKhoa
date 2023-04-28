package com.example.projectcuoikhoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.projectcuoikhoa.DBhelper.CartDBHelper;
import com.example.projectcuoikhoa.Obj.CartShoes;
import java.util.ArrayList;

public class CartDataQuery {
    public static long insert(Context context, CartShoes cartShoes, int idUser,String Address,String PhoneNumber) {
        CartDBHelper cartDBHelper=new CartDBHelper(context);
        SQLiteDatabase sqLiteDatabase = cartDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Ultils.COLUMN_USER_ID_CART,idUser);
        value.put(Ultils.COLUMN_SHOE_NAME_CART,cartShoes.getShoes().getName());
        value.put(Ultils.COLUMN_QUANTITY_CART,cartShoes.getQuantity());
        value.put(Ultils.COLUMN_SHOE_SIZE_CART,cartShoes.getSize());
        value.put(Ultils.COLUMN_CART_IMG,cartShoes.getImgShoes());
        value.put(Ultils.COLUMN_CART_PHONE,PhoneNumber);
        value.put(Ultils.COLUMN_CART_ADDRESS,Address);
        value.put(Ultils.COLUMN_CART_PRICE,cartShoes.getShoes().getPrice());
        long rs = sqLiteDatabase.insert(Ultils.TABLE_CART,null,value);
        return (rs);
    }
    public static ArrayList<CartShoes> getAllByID(Context context, int idUser) {
        ArrayList<CartShoes> cartShoes=new ArrayList<>();
        Shoes shoes=new Shoes();
        CartDBHelper cartDBHelper = new CartDBHelper(context);
        SQLiteDatabase db = cartDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_CART+" Where "+Ultils.COLUMN_USER_ID_CART+"="+idUser, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            String name=cs.getString(2);
            int Quantity=cs.getInt(8);
            String Size=cs.getString(4);
            String Img=cs.getString(7);
            int price=cs.getInt(3);
            String Address=cs.getString(5);
            String PhoneNumber=cs.getString(6);
            shoes=new Shoes(name,price);
            cartShoes.add(new CartShoes(shoes,Quantity,Size,Address,PhoneNumber,Img));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return cartShoes;
    }
    public static ArrayList<CartShoes> getAll(Context context){
        ArrayList<CartShoes> cartShoes=new ArrayList<>();
        Shoes shoes=new Shoes();
        CartDBHelper cartDBHelper = new CartDBHelper(context);
        SQLiteDatabase db = cartDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_CART, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            String name=cs.getString(2);
            int Quantity=cs.getInt(8);
            String Size=cs.getString(4);
            String Img=cs.getString(7);
            int price=cs.getInt(3);
            String Address=cs.getString(5);
            String PhoneNumber=cs.getString(6);
            shoes=new Shoes(name,price);
            cartShoes.add(new CartShoes(shoes,Quantity,Size,Address,PhoneNumber,Img));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return cartShoes;
    }
}
