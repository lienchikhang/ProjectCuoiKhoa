package com.example.projectcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.Fragment.InfoFragment;
import com.example.projectcuoikhoa.Fragment.InfoNotLoginFragment;
import com.example.projectcuoikhoa.Fragment.MainFragment;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDBHelper;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.ShoesGridAdapter;
import com.example.projectcuoikhoa.ShoppingCartFragment;
import com.example.projectcuoikhoa.UserDBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvList, rvGridMain;

    BottomNavigationView bottomNavigationView;
    ArrayList<Shoes> list;
    Boolean isLogin = false;
    HorizontalScrollView scrollView;
    ShoesAdapter shoesAdapter;
    ShoesGridAdapter shoesGridAdapter;
    TextView tvMoreCate;
    LinearLayout firstOption, secondOption, thirdOption, fouthOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        Boolean isLogined = i.getBooleanExtra("bool", false);
        //top
        scrollView = findViewById(R.id.scrollView);
        scrollView.setVisibility(View.VISIBLE);

        firstOption = findViewById(R.id.firstOption);
        secondOption = findViewById(R.id.secondOption);

        ShoeDBHelper shoeDBHelper = new ShoeDBHelper(this);

        //tạo bảng user
       UserDBHelper userDBHelper = new UserDBHelper(this);
//       userDBHelper.dropTable();
//        userDBHelper.createTable();


        // Drop bangr shoe
     // shoeDBHelper.dropTableShoe();
//
//        //tao bảng shoe
     // shoeDBHelper.createTableShoe();

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
//                    case R.id.ShoppingCart:
//                            scrollView.setVisibility(View.INVISIBLE);
//                            loadFragment(new ShoppingCartFragment());
//                            break;
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