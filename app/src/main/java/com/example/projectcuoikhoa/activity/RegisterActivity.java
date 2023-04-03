package com.example.projectcuoikhoa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.projectcuoikhoa.MainAdminActivity;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Ultils;
import com.example.projectcuoikhoa.User;
import com.example.projectcuoikhoa.UserDataQuery;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements UserDataQuery.UserCallback {

    private EditText edUsername, edPassword, edEmail, edPhone, edConfirmpassword, edRole;
    private RadioGroup rdGender;
    private ImageView imAvatar;
    private Button btnRegisterAbc;

    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    UserDataQuery userDataQuery;

    private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences(Ultils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        anhXaDuLieu();
        taoSuKien();
    }
    void anhXaDuLieu() {
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edConfirmpassword = findViewById(R.id.edConfirmPassword);
        edPhone = findViewById(R.id.edPhone);
        edEmail = findViewById(R.id.edEmail);
        edRole = findViewById(R.id.edRole);
        rdGender = findViewById(R.id.rdGender);
        btnRegisterAbc = findViewById(R.id.btnRegisterAbc);
    }

    void taoSuKien() {
        btnRegisterAbc.setOnClickListener(view -> sukienRegister2());

    }

    void sukienRegister2() {
        String userName = edUsername.getText().toString().trim();
        String passWord = edPassword.getText().toString().trim();
        String confirmPassword = edConfirmpassword.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String phone = edPhone.getText().toString().trim();
        String role = edRole.getText().toString().trim();
        int gender = 1;
        boolean isValid = checkUserName(userName) && checkPassword(passWord, confirmPassword);
        if(isValid) {
            int selectedRadio = rdGender.getCheckedRadioButtonId();
            if(selectedRadio == R.id.rdFemale) {
                gender = 0;
            }
            User user = new User(userName,passWord,gender,email,phone,role);
            long id = UserDataQuery.insert(RegisterActivity.this,user);
            if( id > 0) {
                Toast.makeText(this, "them thanh cong", Toast.LENGTH_SHORT).show();
            }
//
//            String userStr = gson.toJson(user);
//            editor.putString(Ultils.KEY_USER, userStr);
//            editor.commit();

            Toast.makeText(RegisterActivity.this,"Register Success!",Toast.LENGTH_LONG);
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

    boolean checkUserName(String username) {
        if (username.isEmpty()) {
            edUsername.setText("Please enter username!");
            return false;
        }
        if (username.length() <= 5) {
            edUsername.setText("More than 5 characters");
            return false;
        }
        return true;
    }

    boolean checkPassword(String password, String confirmPassword) {
        if(password.isEmpty()) {
            edPassword.setText("Please enter password");
            return false;
        }
        if(password.length() <= 5 ) {
            edPassword.setText("More than 5 characters!");
        }

        if(!password.equals(confirmPassword)) {
            edConfirmpassword.setText("Incorrect password!");
            return false;
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bottom_nav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void Check() {

    }
}