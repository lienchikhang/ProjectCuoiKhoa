package com.example.projectcuoikhoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserDataQuery {
    public static long insert(Context context, User us) {
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
}
