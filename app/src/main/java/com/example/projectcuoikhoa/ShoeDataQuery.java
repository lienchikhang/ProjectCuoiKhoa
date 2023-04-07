package com.example.projectcuoikhoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ShoeDataQuery {
    public static long insert(Context context, Shoes sh) {
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase sqLiteDatabase = shoeDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(Ultils.COLUMN_SHOE_ID, sh.id);
        values.put(Ultils.COLUMN_SHOE_NAME,sh.name);
        values.put(Ultils.COLUMN_SHOE_AVATAR, sh.image);
        values.put(Ultils.COLUMN_SHOE_PRICE,sh.price);
        values.put(Ultils.COLUMN_SHOE_TYPE, sh.type);
        long rs = sqLiteDatabase.insert(Ultils.TABLE_SHOE,null,values);
        return (rs);
    }

    public static ArrayList<Shoes> getAll(Context context) {
        ArrayList<Shoes> lstUser = new ArrayList<>();
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase db = shoeDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_SHOE, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String image = cs.getString(2);
            int price = cs.getInt(3);
            String type = cs.getString(4);
            lstUser.add(new Shoes(id,name,image,price,type));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }
    public static Shoes getShoes(Context context,int ID){
        ShoeDBHelper shoeDBHelper=new ShoeDBHelper(context);
        SQLiteDatabase db=shoeDBHelper.getReadableDatabase();
        Cursor cs=db.rawQuery("Select * from "+Ultils.TABLE_SHOE+" Where "+Ultils.COLUMN_SHOE_ID+"="+ID,null);
        cs.moveToFirst();
        int id = cs.getInt(0);
        String name = cs.getString(1);
        String image = cs.getString(2);
        int price = cs.getInt(3);
        String type = cs.getString(4);
        Shoes shoes=new Shoes(name,image,price,type);
        return shoes;
    }


    public static int update(Context context, Shoes sh) {
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase sqLiteDatabase = shoeDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ultils.COLUMN_SHOE_NAME,(sh.getName()));
        values.put(Ultils.COLUMN_SHOE_AVATAR,sh.getImage());
        values.put(Ultils.COLUMN_SHOE_PRICE, sh.getPrice());
        values.put(Ultils.COLUMN_SHOE_TYPE, sh.getType());
        int rs = sqLiteDatabase.update(Ultils.TABLE_SHOE, values, Ultils.COLUMN_SHOE_ID +"=?", new String[] {String.valueOf(sh.id)});
        return (rs);
    }

    public static boolean delete(Context context, int id) {
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase sqLiteDatabase = shoeDBHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(Ultils.TABLE_SHOE,Ultils.COLUMN_SHOE_ID +"=?", new String[] {String.valueOf(id)});
        return (rs > 0);
    }

    public static ArrayList<Shoes> FilterData(Context context, String typeFilter) {
        ArrayList<Shoes> lstUser = new ArrayList<>();
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase db = shoeDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_SHOE + " where " + Ultils.COLUMN_SHOE_TYPE + "=?", new String[] {String.valueOf(typeFilter)});
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String image = cs.getString(2);
            int price = cs.getInt(3);
            String type = cs.getString(4);
            lstUser.add(new Shoes(id,name,image,price,type));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }
    public static ArrayList<Shoes> SerchByName(Context context,String Name){
        ArrayList<Shoes> lstUser = new ArrayList<>();
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase db = shoeDBHelper.getReadableDatabase();
        Cursor cs=db.rawQuery("Select * from "+Ultils.TABLE_SHOE+" Where "+Ultils.COLUMN_SHOE_NAME+" like"+" %"+Name+"%",null);
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String image = cs.getString(2);
            int price = cs.getInt(3);
            String type = cs.getString(4);
            lstUser.add(new Shoes(id,name,image,price,type));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }


}
