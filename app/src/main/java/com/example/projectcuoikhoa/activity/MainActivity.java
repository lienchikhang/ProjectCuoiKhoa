package com.example.projectcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.DBhelper.CartDBHelper;
import com.example.projectcuoikhoa.Fragment.InfoFragment;
import com.example.projectcuoikhoa.Fragment.InfoNotLoginFragment;
import com.example.projectcuoikhoa.Fragment.MainFragment;
import com.example.projectcuoikhoa.Fragment.ProductListFragment;
import com.example.projectcuoikhoa.Fragment.WishListFragment;
import com.example.projectcuoikhoa.MainAdminActivity;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.DBhelper.ShoeDBHelper;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.DBhelper.UserDBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvList, rvGridMain;
    EditText SearchText;
    ImageView btnSearch;
    ConstraintLayout SearchBar;
    BottomNavigationView bottomNavigationView;
    ArrayList<Shoes> list;
    HorizontalScrollView scrollView;
    ShoesAdapter shoesAdapter;
    ShoesGridAdapter shoesGridAdapter;

    UserDBHelper userDBHelper;
    ShoeDBHelper shoeDBHelper;
    CartDBHelper cartDBHelper;
    TextView tvMoreCate;

    LinearLayout firstOption, secondOption, thirdOption, fouthOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SearchText=findViewById(R.id.SearchText);
//        btnSearch=findViewById(R.id.SearchBtn);
        //top
        scrollView = findViewById(R.id.scrollView);

        SearchText=findViewById(R.id.SearchText);
        btnSearch=findViewById(R.id.SearchBtn);
        //top


        SearchBar=findViewById(R.id.SearchBar);
        btnSearch.setOnClickListener(view1 -> SearchClick(SearchText.getText().toString()));
//        SearchBar=findViewById(R.id.SearchBar);
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", MODE_PRIVATE);
        boolean isLogined=false;
        if(sharedPreferences.getInt("id",0)!=0){
            isLogined=true;
            if(sharedPreferences.getString("role",null)!=null){
                startActivity(new Intent(this, MainAdminActivity.class));
            }
        }

        firstOption = findViewById(R.id.firstOption);
        secondOption = findViewById(R.id.secondOption);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //Drop bang shoe
//        shoeDBHelper = new ShoeDBHelper(this);
//        shoeDBHelper.createTableListShoe();
//        shoeDBHelper.dropTableList();
//        shoeDBHelper.dropTableShoe();
//        userDBHelper.dropTable();
//        userDBHelper.createTable();
        //tao bảng shoe
//        shoeDBHelper.createTableShoe();
        //bottom nav
        cartDBHelper=new CartDBHelper(this);
//        cartDBHelper.dropTable();
//        cartDBHelper.createTableCart();
        bottomNavigationView = findViewById(R.id.navbarBottom);
//        btnSearch.setOnClickListener(view -> SearchClick(SearchText.getText().toString()));
        loadFragment(new MainFragment());
        bottomNavigationView.setOnItemSelectedListener(getListener(isLogined));
    }
    void SearchClick(String text){
        Intent i=new Intent(this,SearchActivity.class);
        i.putExtra("text",text);
        startActivity(i);
    }
    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener(Boolean isLogined) {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        scrollView.setVisibility(View.VISIBLE);
                        SearchBar.setVisibility(View.VISIBLE);
                        loadFragment(new MainFragment());
                        break;
                    case R.id.info:
                        scrollView.setVisibility(View.INVISIBLE);
                        SearchBar.setVisibility(View.INVISIBLE);
                        if (isLogined) {
                            loadFragment(new InfoFragment());
                        } else {
                            loadFragment(new InfoNotLoginFragment(isLogined));
                        }
                        break;
                    case R.id.wishListNe:
                        scrollView.setVisibility(View.INVISIBLE);
                        SearchBar.setVisibility(View.INVISIBLE);
                        loadFragment(new WishListFragment());
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

    void loadFragmentWithID(Fragment fmNew, int idUser) {
        FragmentTransaction fmOld = getSupportFragmentManager().beginTransaction();
        fmOld.replace(R.id.main_fragment, fmNew);
        fmOld.addToBackStack(null);
        fmOld.commit();

    }


}