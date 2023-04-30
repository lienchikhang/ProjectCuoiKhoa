package com.example.projectcuoikhoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.Fragment.InfoAdminFragment;
import com.example.projectcuoikhoa.Fragment.MainAdminFragment;
import com.example.projectcuoikhoa.Obj.User;
import com.example.projectcuoikhoa.activity.MainActivity;
import com.example.projectcuoikhoa.activity.ManageCartActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainAdminActivity extends AppCompatActivity {
    RecyclerView rvList, rvGridMain;

    BottomNavigationView bottomNavigationView;
    ArrayList<Shoes> listShoes;
    Boolean isLogin = false;
    HorizontalScrollView scrollViewAdmin;
    ShoesAdapter shoesAdapter;
    ShoesGridAdapter shoesGridAdapter;

    TextView adminName;

    LinearLayout manageProduct, manageUser, btnLogoutt, CartManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
        boolean isLogined=false;
        if(sharedPreferences.getInt("id",0)!=0){
            isLogined=true;
        }
//        String role = i.getStringExtra("role");
//        String adminNames = i.getStringExtra("name");
        User user=UserDataQuery.getUser(this,sharedPreferences.getInt("id",0));

        adminName = findViewById(R.id.adminName);
        adminName.setText(user.getUsername());

//        scrollViewAdmin = findViewById(R.id.scrollViewAdminn);
        manageProduct = findViewById(R.id.manageProduct);
        manageUser = findViewById(R.id.manageUser);
        btnLogoutt = findViewById(R.id.btnLogoutt);
        CartManager=findViewById(R.id.manageOrder);
        getOptionsListener();

        //bottom nav
//        loadFragment(new MainAdminFragment());
//        bottomNavigationView.setOnItemSelectedListener(getListener(isLogined,role));
    }

    void getOptionsListener() {
        manageProduct.setOnClickListener(getListentOptionAdmin());
        manageUser.setOnClickListener(getListentOptionAdmin());
        btnLogoutt.setOnClickListener(getListentOptionAdmin());
//        thirdOption.setOnClickListener(getListenerOption());
//        fouthOption.setOnClickListener(getListenerOption());
        CartManager.setOnClickListener(getListentOptionAdmin());
    }

    @NonNull
    private View.OnClickListener getListentOptionAdmin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.manageOrder:
                        Intent i=new Intent(MainAdminActivity.this, ManageCartActivity.class);
                        startActivity(i);
                        break;
                    case R.id.manageProduct:
                         i = new Intent(MainAdminActivity.this, ManageProductActivity.class);
                        startActivity(i);
                        break;
                    case R.id.manageUser:
                        i = new Intent(MainAdminActivity.this, ManageUserActivity.class);
                        startActivity(i);
                        break;
                    case R.id.btnLogoutt:
                        i = new Intent(MainAdminActivity.this, MainActivity.class);
                        SharedPreferences sharedPreferencesInfo = getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
                        sharedPreferencesInfo.edit().remove("id").apply();
                        sharedPreferencesInfo.edit().remove("role").apply();
                        startActivity(i);
                        break;
                }
            }
        };
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener(Boolean isLogined, String role) {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        scrollViewAdmin.setVisibility(View.VISIBLE);
                        loadFragment(new MainAdminFragment());
                        break;
                    case R.id.info:
                        scrollViewAdmin.setVisibility(View.INVISIBLE);
                        loadFragment(new InfoAdminFragment());
                        break;
//


                }
                return true;
            }
        };
    }

    void addUserDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainAdminActivity.this);
        alertDialog.setTitle("Them moi");
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.dialog_add_shoe,null);
        alertDialog.setView(dialogView);

        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
        EditText edType = (EditText) dialogView.findViewById(R.id.edType);
        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice);

        alertDialog.setPositiveButton("dong y", (dialog,which) -> {
            String name = edName.getText().toString();
            String avatar = edAvatar.getText().toString();
            String type = edType.getText().toString();
            int price =Integer.parseInt(edPrice.getText().toString()) ;
            if(name.isEmpty()) {
                Toast.makeText(this, "nhap du lieu khong dung", Toast.LENGTH_SHORT).show();
            } else {
                Shoes sh = new Shoes(0, name,avatar,price,type);
                long id = ShoeDataQuery.insert(MainAdminActivity.this,sh);
                if( id > 0) {
                    Toast.makeText(this, "them thanh cong", Toast.LENGTH_SHORT).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Huy",(dialog,which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();

    }
    void loadFragment(Fragment fmNew) {
        FragmentTransaction fmOld = getSupportFragmentManager().beginTransaction();
        fmOld.replace(R.id.mainAdmin_fragment, fmNew);
        fmOld.addToBackStack(null);
        fmOld.commit();

    }

    void resetData() {
        listShoes.clear();
        listShoes.addAll(ShoeDataQuery.getAll(MainAdminActivity.this));
       // shoesAdapter.notifyDataSetChanged();
    }

    void updateUserDialog(Shoes sh) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainAdminActivity.this);
        alertDialog.setTitle("cap nhat");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_shoe,null);
        alertDialog.setView(dialogView);

        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
        EditText edType = (EditText) dialogView.findViewById(R.id.edType);
        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice);

        //gan du lieu
        edAvatar.setText(sh.getImage());
        edName.setText(sh.getName());
        edPrice.setText(sh.getPrice());
        edType.setText(sh.getType());
        alertDialog.setPositiveButton("dong y", (dialog,which) -> {
            sh.setName(edName.getText().toString());
            sh.setImage(edAvatar.getText().toString());
            sh.setPrice(Integer.parseInt(edPrice.getText().toString()));
            sh.setType(edType.getText().toString());

            if(sh.name.isEmpty()) {
                Toast.makeText(this, "vui long nhap du lieu", Toast.LENGTH_SHORT).show();
            } else {
                int id = ShoeDataQuery.update(MainAdminActivity.this,sh);
                if( id > 0) {
                    Toast.makeText(this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Huy",(dialog,which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }

}