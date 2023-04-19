package com.example.projectcuoikhoa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Ultils {
    public static final String SHARE_PREFERENCES_APP = "share_preferences_app";
    public static final String KEY_ACCOUNT = "key_account";
    public static final String KEY_IS_LOGIN = "key_is_login";
    public static final String KEY_USER = "key_user";
    public static final String KEY_USER_PROFILE = "key_user_profile";

    //sql shoes

    public static final String DATABASE_NAME = "db-shoe";
    public static final String TABLE_SHOE = "shoe";
    public static final String COLUMN_SHOE_NAME = "name";
    public static final String COLUMN_SHOE_ID = "id";
    public static final String COLUMN_SHOE_AVATAR = "avatar";
    public static final String COLUMN_SHOE_PRICE = "price";
    public static final String COLUMN_SHOE_TYPE = "type";

    // sql user
    public static final String DATABASE_USERNAME = "db-User";
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_AVATAR = "avatar";
    public static final String COLUMN_USER_PASSWORD = "password";

    public static final String COLUMN_USER_EMAIL = "email";

    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_GENDER= "gender";

    public static final String COLUMN_USER_ROLE = "role";
    public static final String COLUMN_USER_LOGINTIME = "loginTime";


    public static Bitmap convertToBitmapFromAssets(Context context,String nameImage) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("images/" + nameImage);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String ConvertToVND(int Price){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Price);
        return str1;
    }

}
