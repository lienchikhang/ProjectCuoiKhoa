package com.example.projectcuoikhoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectcuoikhoa.Fragment.InfoFragment;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    Button btLogin, btRegister;
    EditText edUsername, edPassword;

    SharedPreferences.Editor editor;

    private final Gson gson = new Gson();
    SharedPreferences sharedPreferences;
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

        sharedPreferences = getSharedPreferences(Ultils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
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
        String userPref = sharedPreferences.getString(Ultils.KEY_USER, null);
        User user = gson.fromJson(userPref, User.class);
        if(user == null) {
            return;
        }

        boolean isValid = edUsername.getText().toString().trim().equals(user.getUsername()) && edPassword.getText().toString().trim().equals(user.getPassword());
        if(isValid) {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Ultils.KEY_USER_PROFILE,user);
            isLogined = true;
            intent.putExtra("bool", isLogined);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}