package com.example.projectcuoikhoa;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Ultils {
    public static final String SHARE_PREFERENCES_APP = "share_preferences_app";
    public static final String KEY_ACCOUNT = "key_account";
    public static final String KEY_IS_LOGIN = "key_is_login";
    public static final String KEY_USER = "key_user";
    public static final String KEY_USER_PROFILE = "key_user_profile";
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

    public static void LoadData(ArrayList<Clothes> clothesArrayList) {

    }
}
