package com.example.projectcuoikhoa.DBhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectcuoikhoa.Ultils;

public class CartDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_CART = Ultils.DATABASE_CART;
    private static final int DATABASE_VERSION = 1;
    public CartDBHelper(Context context){
        super(context,DATABASE_CART,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CART_TABLE="CREATE TABLE "+Ultils.TABLE_CART+"("
                +Ultils.COLUMN_CART_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Ultils.COLUMN_USER_ID_CART+" INTEGER, "
                +Ultils.COLUMN_SHOE_NAME_CART+ " TEXT, "
                +Ultils.COLUMN_QUANTITY_CART+" INTEGER, "
                +Ultils.COLUMN_SHOE_SIZE_CART+" Text"
                +" )";
        sqLiteDatabase.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Ultils.TABLE_CART);
        onCreate(sqLiteDatabase);
    }
    public void createTableCart(){
        SQLiteDatabase db=getWritableDatabase();
        String CREATE_CART_TABLE="CREATE TABLE "+Ultils.TABLE_CART+"("
                +Ultils.COLUMN_CART_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Ultils.COLUMN_USER_ID_CART+" INTEGER, "
                +Ultils.COLUMN_SHOE_NAME_CART+ " TEXT, "
                +Ultils.COLUMN_QUANTITY_CART+" INTEGER, "
                +Ultils.COLUMN_SHOE_SIZE_CART+" Text"
                +" )";
        db.execSQL(CREATE_CART_TABLE);
    }
    public void dropTable() {
        SQLiteDatabase db = getReadableDatabase();
        String drop = "DROP TABLE IF EXISTS " + Ultils.TABLE_CART;
        db.execSQL(drop);
    }
}

