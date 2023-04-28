package com.example.projectcuoikhoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectcuoikhoa.DBhelper.UserDBHelper;
import com.example.projectcuoikhoa.Obj.User;

import java.util.ArrayList;

public class UserDataQuery {
    public static long insert(Context context, User us) {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ultils.COLUMN_USER_NAME,us.getUsername());
        values.put(Ultils.COLUMN_USER_PASSWORD, us.getPassword());
        values.put(Ultils.COLUMN_USER_GENDER,us.getGender(1));
        values.put(Ultils.COLUMN_USER_EMAIL, us.getEmail());
        values.put(Ultils.COLUMN_USER_PHONE, us.getPhone());
        values.put(Ultils.COLUMN_USER_ROLE,us.getRole());
        long rs = sqLiteDatabase.insert(Ultils.TABLE_USER,null,values);
        return (rs);
    }



    public static int update(Context context, User us) {
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase sqLiteDatabase = userDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Ultils.COLUMN_USER_ID,us.getId());
        values.put(Ultils.COLUMN_USER_NAME,us.getUsername());
        values.put(Ultils.COLUMN_USER_PASSWORD, us.getPassword());
        values.put(Ultils.COLUMN_USER_GENDER,us.getGender(1));
        values.put(Ultils.COLUMN_USER_EMAIL, us.getEmail());
        values.put(Ultils.COLUMN_USER_PHONE, us.getPhone());
        values.put(Ultils.COLUMN_USER_ROLE,us.getRole());
        int rs = sqLiteDatabase.update(Ultils.TABLE_USER, values, Ultils.COLUMN_USER_ID +"=?", new String[] {String.valueOf(us.getId())});
        return (rs);
    }

    public interface UserCallback {
        void Check();
    }
    public static User getUser (Context context, int idUs) {
        UserDBHelper userDBHelper=new UserDBHelper(context);
        SQLiteDatabase db=userDBHelper.getReadableDatabase();
        Cursor cs=db.rawQuery("Select * from "+Ultils.TABLE_USER+" Where "+Ultils.COLUMN_SHOE_ID+"="+idUs,null);
        cs.moveToFirst();
        int id = cs.getInt(0);
        String name = cs.getString(1);
        String pass = cs.getString(2);
        int gender = cs.getInt(3);
        String email = cs.getString(4);
        String phone = cs.getString(5);
        String role = cs.getString(6);
        User user=new User(id,name,pass,gender,email,phone,role);
        return user;
    }

    public static ArrayList<User> getAll(Context context) {
        ArrayList<User> lstUser = new ArrayList<>();
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_USER, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String pass = cs.getString(2);
            int gender = cs.getInt(3);
            String email = cs.getString(4);
            String phone = cs.getString(5);
            String role = cs.getString(6);
            lstUser.add(new User(id,name,pass,gender,email,phone,role));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }

    public static ArrayList<User> getNormalUser(Context context,String roleU) {
        ArrayList<User> lstUser = new ArrayList<>();
        UserDBHelper userDBHelper = new UserDBHelper(context);
        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + Ultils.TABLE_USER + " where " + Ultils.COLUMN_USER_ROLE + " not like" + "\"" + "%" + roleU + "%" + "\"", null);
        cs.moveToFirst();
        while(!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String pass = cs.getString(2);
            int gender = cs.getInt(3);
            String email = cs.getString(4);
            String phone = cs.getString(5);
            String role = cs.getString(6);
            lstUser.add(new User(id,name,pass,gender,email,phone,role));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstUser;
    }
}
