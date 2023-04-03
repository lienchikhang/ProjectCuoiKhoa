package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcuoikhoa.MainAdminActivity;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDBHelper;
import com.example.projectcuoikhoa.Ultils;
import com.example.projectcuoikhoa.User;
import com.example.projectcuoikhoa.UserDBHelper;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    Button btLogin, btRegister;
    EditText edUsername, edPassword;

//    SharedPreferences.Editor editor;

//    private final Gson gson = new Gson();
//    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Intent i = getIntent();
        Boolean isLogined = i.getBooleanExtra("bool",false);

        //Anh Xa
        anhXa();
        taoSuKien(isLogined);

//        sharedPreferences = getSharedPreferences(Ultils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
    }
    void anhXa() {
        btLogin = findViewById(R.id.loginbtn);
        btRegister = findViewById(R.id.registerBtn);
        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
    }

    void taoSuKien(Boolean isLogined) {
        btLogin.setOnClickListener(view -> checkUserLogin(isLogined));
        btRegister.setOnClickListener(funRegister());
    }

    private View.OnClickListener funRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }

    void checkUserLogin(Boolean isLogined) {
        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        UserDBHelper userDBHelper = new UserDBHelper(this);
//        SQLiteDatabase db = userDBHelper.getReadableDatabase();
        Cursor listUser = userDBHelper.getData(
                "Select * From " + Ultils.TABLE_USER
        );
        String role = "";
        boolean existUser = false;
        while(listUser.moveToNext()) {
            if(username.equals(listUser.getString(1)) && password.equals(listUser.getString(2))) {
                role = listUser.getString(6);
                existUser = true;
                break;
            }
        }
        if(existUser) {
            if(role.equals("admin")) {
                Intent i = new Intent(this, MainAdminActivity.class);
                startActivity(i);
                return;
            } else {
                Intent i = new Intent(this,MainActivity.class);
                isLogined=true;
                i.putExtra("bool",isLogined);
                i.putExtra("UserName",username);
                i.putExtra("Password",password);
                startActivity(i);
            }
        } else {
            Toast.makeText(getApplicationContext(),"sai mat khau hoac tai khoan",Toast.LENGTH_LONG).show();
        }
//        String query = "SELECT * FROM " + Ultils.TABLE_USER + " WHERE " +Ultils.COLUMN_USER_NAME + "= ? AND " + Ultils.COLUMN_USER_PASSWORD + " = ?";
//        Cursor cursor = db.rawQuery(query, new String[]{Ultils.COLUMN_USER_NAME,Ultils.COLUMN_USER_PASSWORD});

//        boolean isValid = username.trim().equals(x.getUsername) && edPassword.getText().toString().trim().equals(x.getPassword());
//        if(cursor.getCount() > 0) {
//            Intent intent = new Intent(this, MainActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(Ultils.KEY_USER_PROFILE,user);
//            isLogined = true;
//            intent.putExtra("bool", isLogined);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        } else {
//            // Username không tồn tại trong cơ sở dữ liệu
//        }
//        if(cursor.moveToFirst()) {
//            int id = cursor.getInt(0);
//            String dbUsername = cursor.getString(1);
//            String dbPassword = cursor.getString(2);
//            User user = new User(id, dbUsername, dbPassword);
//            boolean isValid = username.equals(user.getUsername()) && password.equals(user.getPassword());
//            if(isValid) {
//                Intent intent = new Intent(this, MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Ultils.KEY_USER_PROFILE,user);
//                isLogined = true;
//                intent.putExtra("bool", isLogined);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            } else {
//                // Đăng nhập thất bại
//            }
//        }
//        cursor.close();
//        db.close();

//        String userPref = sharedPreferences.getString(Ultils.KEY_USER, null);
//        User user = gson.fromJson(userPref, User.class);
//        if(user == null) {
//            return;
//        }


    }


}