package com.example.projectcuoikhoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.projectcuoikhoa.Fragment.InfoFragment;
import com.example.projectcuoikhoa.Fragment.InfoNotLoginFragment;
import com.example.projectcuoikhoa.Fragment.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvList, rvGridMain;

    BottomNavigationView bottomNavigationView;
    ArrayList<Clothes> list;
    Boolean isLogin = false;
    HorizontalScrollView scrollView;
    ClothesAdapter clothesAdapter;
    ClothesGridAdapter clothesGridAdapter;
    TextView tvMoreCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        Boolean isLogined = i.getBooleanExtra("bool", false);
        //top
        scrollView = findViewById(R.id.scrollView);
        scrollView.setVisibility(View.VISIBLE);
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
                        scrollView.setVisibility(View.VISIBLE);
                        loadFragment(new MainFragment());
                        break;
                    case R.id.info:
                        scrollView.setVisibility(View.INVISIBLE);
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


    void loadFragment(Fragment fmNew) {
        FragmentTransaction fmOld = getSupportFragmentManager().beginTransaction();
        fmOld.replace(R.id.main_fragment, fmNew);
        fmOld.addToBackStack(null);
        fmOld.commit();

    }


}