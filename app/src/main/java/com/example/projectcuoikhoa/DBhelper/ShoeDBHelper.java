package com.example.projectcuoikhoa.DBhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectcuoikhoa.Ultils;

public class ShoeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = Ultils.DATABASE_NAME;
    private static final int DATABASE_VERSION = 9;
    public ShoeDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Ultils.TABLE_SHOE + "("
                + Ultils.COLUMN_SHOE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Ultils.COLUMN_SHOE_NAME + " TEXT, "
                + Ultils.COLUMN_SHOE_AVATAR + " TEXT, "
                + Ultils.COLUMN_SHOE_PRICE + " TEXT, "
                + Ultils.COLUMN_SHOE_TYPE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Ultils.TABLE_SHOE);
        onCreate(sqLiteDatabase);
    }

    public void restartID() {
        SQLiteDatabase db = getWritableDatabase();
        String result = "UPDATE SQLITE_SEQUENCE SET seq=0 WHERE name=" + Ultils.TABLE_SHOE;
        db.execSQL(result);
    }

    public void dropTableList() {
        SQLiteDatabase db = getReadableDatabase();
        String drop = "DROP TABLE IF EXISTS " + Ultils.TABLE_LIST;
        db.execSQL(drop);
    }

    public void createTableShoe() {
        SQLiteDatabase db = getWritableDatabase();
        String CREATE_USER_TABLE = "CREATE TABLE " + Ultils.TABLE_SHOE + "("
                + Ultils.COLUMN_SHOE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Ultils.COLUMN_SHOE_NAME + " TEXT, "
                + Ultils.COLUMN_SHOE_AVATAR + " TEXT, "
                + Ultils.COLUMN_SHOE_PRICE + " TEXT, "
                + Ultils.COLUMN_SHOE_TYPE + " TEXT"
                + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    public void createTableListShoe() {
        SQLiteDatabase db = getWritableDatabase();
        String CREATE_WISHLIST_TABLE = "CREATE TABLE " + Ultils.TABLE_LIST + "("
                + Ultils.COLUMN_LIST_SHOE_ID + " INTEGER, "
                + Ultils.COLUMN_LIST_SHOE_NAME + " TEXT, "
                + Ultils.COLUMN_LIST_SHOE_AVATAR + " TEXT, "
                + Ultils.COLUMN_LIST_SHOE_PRICE + " TEXT, "
                + Ultils.COLUMN_LIST_SHOE_TYPE + " TEXT, "
                + Ultils.COLUMN_LIST_USER_ID + " INTEGER"
                + ")";
        db.execSQL(CREATE_WISHLIST_TABLE);
    }

    public void dropTableShoe() {
        SQLiteDatabase db = getReadableDatabase();
        String drop = "DROP TABLE IF EXISTS " + Ultils.TABLE_SHOE;
        db.execSQL(drop);
    }

    public Cursor getData(String content) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(content, null);
    }
}
