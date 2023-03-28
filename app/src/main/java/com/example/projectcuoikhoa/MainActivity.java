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
        actionBar.setDefaultDisplayHomeAsUpEnabled(false);

        bottomNavigationView.setOnItemSelectedListener(getListener(isLogined));
        //GridView & recycleView
//        rvList = findViewById(R.id.rwProduct);
//        rvGridMain = findViewById(R.id.rvGridMain);
//        LoadData();
//
//        clothesAdapter = new ClothesAdapter(list);
//        clothesGridAdapter = new ClothesGridAdapter(list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        rvList.setAdapter(clothesAdapter);
//        rvGridMain.setAdapter(clothesGridAdapter);
//        rvList.setLayoutManager(linearLayoutManager);
//        rvGridMain.setLayoutManager(gridLayoutManager);
//
//        tvMoreCate = findViewById(R.id.tvMoreCate);
//        tvMoreCate.setOnClickListener(this);



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
                        if(isLogined) {
                            loadFragment(new InfoFragment());
                        } else {
                            loadFragment(new InfoNotLoginFragment(isLogined));
                        }


                }
                return true;

            }
        };
    }

    void LoadData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Clothes(String.valueOf(i), "polo","ao_0" + i + ".png","$100"));
        }
    }



    void loadFragment(Fragment fmNew) {
        FragmentTransaction fmOld = getSupportFragmentManager().beginTransaction();
        fmOld.replace(R.id.main_fragment, fmNew);
        fmOld.addToBackStack(null);
        fmOld.commit();

    }

//    @Override
//    public void sendData(String id) {
//        DetailFragment detail = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailed_fragment);
//        detail.receiveData(id);
//    }
}