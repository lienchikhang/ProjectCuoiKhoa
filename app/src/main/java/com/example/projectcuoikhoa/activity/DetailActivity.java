package com.example.projectcuoikhoa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.example.projectcuoikhoa.CartShoes;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Ultils;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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
    ImageButton ivBackBtn;
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
        PriceProduct.setText(temp.getPrice());
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

    void LoadData() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if(i < 10) {
                list.add(new Shoes(i, "giày " + i,"giay_0" + i + ".png",i+"00.000 VNĐ","run"));
            } else {
                int ndu = i % 10;
                int nNg = i / 10;
                list.add(new Shoes(i, "giày " + i,"giay_0" + i + ".png",nNg + "." + ndu + "00.000 VNĐ","walk"));
            }
        }
    }

    void anhXadulieu(){
        ivBackBtn = findViewById(R.id.ivBackBtn);
        NameProduct = findViewById(R.id.NameProduct);
        ivAvatar = findViewById(R.id.ivAvatar);
        PriceProduct = findViewById(R.id.PriceProduct);
        SizeChoose=findViewById(R.id.SizeChoose);
        TableSizeProduct=findViewById(R.id.DesProduct);
        BtnSizeL=findViewById(R.id.BtnSizeL);
        BtnSizeM=findViewById(R.id.BtnSizeM);
        BtnSizeS=findViewById(R.id.BtnSizeS);
        BuyProduct=findViewById(R.id.BuyProduct);
        BtnDes=findViewById(R.id.btnDes);
//        Color1=findViewById(R.id.Color1);
//        Color2=findViewById(R.id.Color2);
//        Color3=findViewById(R.id.Color3);
        PriceProduct=findViewById(R.id.PriceProduct);
    }

    void SuKienClick(){
        BtnClick(BtnSizeS);
        makeBtnDefault(BtnSizeL,BtnSizeM);
        BtnSizeS.setOnClickListener(BtnSclick());
        BtnSizeM.setOnClickListener(BtnMclick());
        BtnSizeL.setOnClickListener(BtnLclick());
        BtnDes.setOnClickListener(view -> BtnDesClick());
        BuyProduct.setOnClickListener(view -> BuyProductEvent());
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
}