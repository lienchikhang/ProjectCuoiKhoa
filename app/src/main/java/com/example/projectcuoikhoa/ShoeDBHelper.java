package com.example.projectcuoikhoa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShoeDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = Ultils.DATABASE_NAME;
    private static final int DATABASE_VERSION = 5;
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

    public void dropTableShoe() {
        SQLiteDatabase db = getReadableDatabase();
        String drop = "DROP TABLE IF EXISTS " + Ultils.TABLE_SHOE;
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
//        String RESET_SEQ_TABLE = "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = '" + Ultils.TABLE_SHOE + "'";
//        db.execSQL(RESET_SEQ_TABLE);

//        String INSERT_ID_0 = "INSERT INTO " + Ultils.TABLE_SHOE + " (" + Ultils.COLUMN_SHOE_ID + ", " + Ultils.COLUMN_SHOE_NAME + ", " + Ultils.COLUMN_SHOE_AVATAR + ", " + Ultils.COLUMN_SHOE_PRICE + ", " + Ultils.COLUMN_SHOE_TYPE + ")"
//                + " VALUES (0, 'shoe 0', 'image_0', '100', 'type 0')";
//        db.execSQL(INSERT_ID_0);
    }
}
