package com.example.projectcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikhoa.MainAdminActivity;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Ultils;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    TableLayout TableSizeProduct;
    Button BtnSizeS
     ,BtnSizeM
    ,BtnSizeL,BtnDes,BuyProduct;
    TextView PriceProduct,SizeChoose;
    RadioButton Color1,Color2,Color3;
    boolean checkDes=false;
    boolean CheckClickSize=false;
    TextView NameProduct;
    ImageView ivAvatar;
    ArrayList<Shoes> list;
    Shoes temp;
    String id;
    ImageButton ivBackBtn,btnCart,wishlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        //ANH XA
        anhXadulieu();


        Intent i = getIntent();
        id = i.getStringExtra("id");

        //lOAD DU LIEU
        list=ShoeDataQuery.getAll(this);
//        LoadData();
        for (Shoes shoesTemp:list
        ) {
            if(shoesTemp.getId()==Integer.parseInt(id)){
                temp=shoesTemp;
            }
        }

        //THAY DOI TEN + GIA
        NameProduct.setText(temp.getName());
        PriceProduct.setText(Ultils.ConvertToVND(temp.getPrice()));
        ivAvatar.setImageBitmap(Ultils.convertToBitmapFromAssets(this,temp.getImage()));

        //su kien onclick
        ivBackBtn.setOnClickListener(getL());
        SuKienClick();
    }

    @NonNull
    private View.OnClickListener getL() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.ivBackBtn:
                        finish();
                        break;
                }
            }
        };
    }



    void anhXadulieu(){
        ivBackBtn = findViewById(R.id.ivBackBtn);
        NameProduct = findViewById(R.id.NameProduct);
        ivAvatar = findViewById(R.id.ivAvatar);
        PriceProduct = findViewById(R.id.PriceProduct);
        SizeChoose=findViewById(R.id.SizeChoose);
        BtnSizeL=findViewById(R.id.BtnSizeL);
        BtnSizeM=findViewById(R.id.BtnSizeM);
        BtnSizeS=findViewById(R.id.BtnSizeS);
        BuyProduct=findViewById(R.id.BuyProduct);
//        BtnDes=findViewById(R.id.btnDes);
        PriceProduct=findViewById(R.id.PriceProduct);
//        btnCart=findViewById(R.id.ImgBtnDetailCart);
        wishlist = findViewById(R.id.wishlist);
    }

    void SuKienClick(){
        BtnClick(BtnSizeS);
        makeBtnDefault(BtnSizeL,BtnSizeM);
        BtnSizeS.setOnClickListener(BtnSclick());
        BtnSizeM.setOnClickListener(BtnMclick());
        BtnSizeL.setOnClickListener(BtnLclick());
//        BtnDes.setOnClickListener(view -> BtnDesClick());
        BuyProduct.setOnClickListener(view -> BuyProductEvent());
//        btnCart.setOnClickListener(view -> CartClick());
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoes sh = ShoeDataQuery.getShoes(DetailActivity.this,Integer.parseInt(id));
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
                int idUserIn = sharedPreferences.getInt("id", MODE_PRIVATE);
                if (idUserIn == 0) {
                    Toast.makeText(DetailActivity.this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                    return;
                }
                long id = ShoeDataQuery.insertToWishList(DetailActivity.this,sh,idUserIn);
                if (id > 0) {
                    Toast.makeText(DetailActivity.this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailActivity.this, "Đã tồn tại trong yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    void BtnDesClick() {
        if(!checkDes){
            Animation animSlideup= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            TableSizeProduct.startAnimation(animSlideup);
            TableSizeProduct.setVisibility(View.VISIBLE);
            checkDes=true;
        }
        else {
            TableSizeProduct.setVisibility(View.INVISIBLE);
            checkDes=false;
        }
    }
    void BuyProductEvent(){
        Intent i=new Intent(this,ShoppingCartActivity.class);
        i.putExtra("ID",id.toString());
        i.putExtra("Size",SizeChoose.getText().toString());
        startActivity(i);
    }
    @NonNull
    private View.OnClickListener BtnSclick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnClick(BtnSizeS);
                makeBtnDefault(BtnSizeL,BtnSizeM);
            }
        };
    }
    @NonNull
    private View.OnClickListener BtnMclick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnClick(BtnSizeM);
                makeBtnDefault(BtnSizeL,BtnSizeS);
            }
        };
    }
    @NonNull
    private View.OnClickListener BtnLclick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnClick(BtnSizeL);
                makeBtnDefault(BtnSizeM,BtnSizeS);
            }
        };
    }

    void BtnClick(Button Click){
        CheckClickSize=true;
        String Text = Click.getText().toString();
        SizeChoose.setText(Text);
        Click.setTextColor(Color.parseColor("#F5F5F5"));
        Click.setBackgroundResource(R.drawable.custom_btn_border);

    }
    void makeBtnDefault(Button Click1,Button Click2){
        Click1.setBackgroundResource(R.drawable.btn_size_default);
        Click2.setBackgroundResource(R.drawable.btn_size_default);
        Click1.setTextColor(Color.parseColor("#000000"));
        Click2.setTextColor(Color.parseColor("#000000"));
        if(ClickAbleBuy())
        {
            BuyProduct.setClickable(true);
        }
    }

    boolean ClickAbleBuy(){
        if(CheckClickSize)
        {
            return true;
        }
        return false;
    }
    void CartClick(){
        Intent i=new Intent(this,ShoppingCartActivity.class);
        startActivity(i);
    }
}