package com.example.projectcuoikhoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikhoa.Fragment.InfoFragment;
import com.example.projectcuoikhoa.Fragment.InfoNotLoginFragment;
import com.example.projectcuoikhoa.Fragment.MainFragment;
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
    TextView tvMoreCate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Intent i = getIntent();
        Boolean isLogined = i.getBooleanExtra("bool", false);
        scrollViewAdmin = findViewById(R.id.scrollViewAdmin);
        scrollViewAdmin.setVisibility(View.VISIBLE);
        //bottom nav
        bottomNavigationView = findViewById(R.id.navbarBottom);
        loadFragment(new MainFragment());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        bottomNavigationView.setOnItemSelectedListener(getListener(isLogined));
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener(Boolean isLogined) {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        scrollViewAdmin.setVisibility(View.VISIBLE);
                        loadFragment(new MainFragment());
                        break;
                    case R.id.info:
                        scrollViewAdmin.setVisibility(View.INVISIBLE);
                        if (isLogined) {
                            loadFragment(new InfoFragment());
                        } else {
                            loadFragment(new InfoNotLoginFragment(isLogined));
                        }


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
            String price = edPrice.getText().toString();
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
        fmOld.replace(R.id.main_fragment, fmNew);
        fmOld.addToBackStack(null);
        fmOld.commit();

    }

    void resetData() {
        listShoes.clear();
        listShoes.addAll(ShoeDataQuery.getAll(MainAdminActivity.this));
//        ShoesAdapter.notifyDataSetChanged();
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
            sh.setPrice(edPrice.getText().toString());
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