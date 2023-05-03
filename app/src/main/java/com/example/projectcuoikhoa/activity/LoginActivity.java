package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcuoikhoa.MainAdminActivity;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.Ultils;
import com.example.projectcuoikhoa.DBhelper.UserDBHelper;

public class LoginActivity extends AppCompatActivity {

    Button btLogin, btRegister;
    EditText edUsername, edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        Intent i = getIntent();
        Boolean isLogined = i.getBooleanExtra("bool", false);
        //Anh Xa
        anhXa();
        taoSuKien(isLogined);
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
        Cursor listUser = userDBHelper.getData(
                "Select * From " + Ultils.TABLE_USER
        );
        String role = "";
        int loginTime = 0;

        boolean existUser = false;
        while (listUser.moveToNext()) {
            if (username.equals(listUser.getString(1)) && password.equals(listUser.getString(2))) {
                role = listUser.getString(6);
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id",listUser.getInt(0));
                editor.apply();
                existUser = true;
                break;
            }
        }
        if (existUser) {
            if (role.equals("admin")) {
                Intent i = new Intent(this, MainAdminActivity.class);
//                i.putExtra("role", role);
//                i.putExtra("name", username);
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("role",role);
                editor.apply();
                startActivity(i);
                return;
            } else {
                Intent i = new Intent(this, MainActivity.class);
//                i.putExtra("role", role);
//                i.putExtra("bool", isLogined);
//                i.putExtra("UserName", username);
//                i.putExtra("Password", password);
//                i.putExtra("loginTime", loginTime);
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
                int idUserIn = sharedPreferences.getInt("id", MODE_PRIVATE);
//                Toast.makeText(this, "idU: " + idUserIn, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        } else {
            Toast.makeText(getApplicationContext(), "sai mật khẩu hoặc tài khoản", Toast.LENGTH_LONG).show();
        }
    }
}