package com.example.projectcuoikhoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.projectcuoikhoa.DBhelper.ShoeDBHelper;
import com.example.projectcuoikhoa.DBhelper.UserDBHelper;
import com.example.projectcuoikhoa.Obj.User;

import java.util.ArrayList;

public class ShoeDataQuery  {
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
//    public static ArrayList<Shoes> checkUser(Context context,int idUser) {
//        User user=UserDataQuery.getUser(context,idUser);
//
//
//    }
    public static ArrayList<Shoes> getAllWishList(Context context, int idUserIn) {
        ArrayList<Shoes> lstUser = new ArrayList<>();
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase db = shoeDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_LIST + " where " + Ultils.COLUMN_LIST_USER_ID +"=?", new String[] {String.valueOf(idUserIn)});
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String image = cs.getString(2);
            int price = cs.getInt(3);
            String type = cs.getString(4);
            int idUser = cs.getInt(5);
            lstUser.add(new Shoes(id,name,image,price,type,idUser));
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
        Shoes shoes=new Shoes(id,name,image,price,type);
        return shoes;
    }
    public static Shoes GetByName(Context context,String Name){
        ShoeDBHelper shoeDBHelper=new ShoeDBHelper(context);
        SQLiteDatabase db=shoeDBHelper.getReadableDatabase();
        Cursor cs=db.rawQuery("Select * from "+Ultils.TABLE_SHOE+" Where "+Ultils.COLUMN_SHOE_NAME+" like"+"'%"+Name+"%'",null);
        cs.moveToFirst();
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
        Cursor cs=db.rawQuery("Select * from "+Ultils.TABLE_SHOE+" Where "+Ultils.COLUMN_SHOE_NAME+" like '%"+Name,null);
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

    public static Boolean getDeleteShoe(Context context, String nameShoe) {
        ShoeDBHelper shoeDBHelper=new ShoeDBHelper(context);
        SQLiteDatabase db = shoeDBHelper.getReadableDatabase();
        db = shoeDBHelper.getWritableDatabase();
        int rs = db.delete(Ultils.TABLE_SHOE,Ultils.COLUMN_SHOE_NAME+" like '%" + nameShoe + "%'",null);
        return (rs >0);
    }

    public static long insertToWishList(Context context, Shoes sh, int idUser) {
        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(context);
        SQLiteDatabase sqLiteDatabase = shoeDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ultils.COLUMN_LIST_SHOE_ID, sh.id);
        values.put(Ultils.COLUMN_LIST_SHOE_NAME,sh.name);
        values.put(Ultils.COLUMN_LIST_SHOE_AVATAR, sh.image);
        values.put(Ultils.COLUMN_LIST_SHOE_PRICE,sh.price);
        values.put(Ultils.COLUMN_LIST_SHOE_TYPE, sh.type);
        values.put(Ultils.COLUMN_LIST_USER_ID, idUser);
        Toast.makeText(context, "idshoe: " + sh.getId(), Toast.LENGTH_SHORT).show();
        long rs = sqLiteDatabase.insert(Ultils.TABLE_LIST,null,values);
        return (rs);
    }
}
