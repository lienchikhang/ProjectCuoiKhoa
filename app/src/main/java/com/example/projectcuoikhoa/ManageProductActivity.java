package com.example.projectcuoikhoa;

import static com.example.projectcuoikhoa.R.color.primary_color;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.Adapter.ShoesAdapterAdmin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ManageProductActivity extends AppCompatActivity implements ShoesAdapterAdmin.ShoesCallBackAdmin, ShoesAdapter.ShoesCallBack {
    RecyclerView rvList;
    ArrayList<Shoes> list;
    ShoesAdapterAdmin shoesAdapterAdmin;

    HorizontalScrollView scrollView;
    FloatingActionButton fbAdd;

    ImageView btnBackManage;
    EditText etDeleteShoe;
    Button btnDeletePickedShoe;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        fbAdd = findViewById(R.id.fbAddd);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int myPrimaryColor = Color.parseColor("#F8D69C");
        fbAdd.setBackgroundTintList(ColorStateList.valueOf(myPrimaryColor));
        fbAdd.setOnClickListener(view1 -> addShoeDialog());
//        fbAdd.setBackgroundTintList(ColorStateList.valueOf();
        rvList = findViewById(R.id.rvGridAdmin);
        btnBackManage = findViewById(R.id.btnBackManage);
//        etDeleteShoe = findViewById(R.id.etDeleteShoe);
//        btnDeletePickedShoe = findViewById(R.id.btnDeletePickedShoe);

//        String pickedShoe = etDeleteShoe.getText().toString();
//        btnDeletePickedShoe.setOnClickListener(getListenPickedShoe(pickedShoe,this));
        btnBackManage.setOnClickListener(getListen());
        list = ShoeDataQuery.getAll(this);
        shoesAdapterAdmin = new ShoesAdapterAdmin(list,this);
        shoesAdapterAdmin.setCallBackAdmin(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvList.setAdapter(shoesAdapterAdmin);
        rvList.setLayoutManager(gridLayoutManager);



    }

    @NonNull
    private View.OnClickListener getListen() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnBackManage:
                        finish();
                        break;
                }
            }
        };
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemDeleteClick(Shoes sh, int position) {
        boolean rs = ShoeDataQuery.delete(this,sh.getId());
        if(rs) {
            Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
            resetData();
        } else {
            Toast.makeText(this, "Xoá không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemEditClick(Shoes sh, int position) {
        updateShoeDialog(sh);
    }

    void resetData() {
        list.clear();
        list.addAll(ShoeDataQuery.getAll(this));
        shoesAdapterAdmin.notifyDataSetChanged();
    }

    void updateShoeDialog(Shoes sh) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Cập Nhật");
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
        edPrice.setText(String.valueOf(sh.getPrice()));
        edType.setText(sh.getType());

        alertDialog.setPositiveButton("Đồng ý", (dialog,which) -> {
            sh.setName(edName.getText().toString());
            sh.setImage(edAvatar.getText().toString());
            int price = Integer.parseInt(edPrice.getText().toString());
            sh.setType(edType.getText().toString());
            if(sh.getName().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
            } else if(price <= 0) {
                Toast.makeText(this, "Giá tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            } else {
                sh.setPrice(Integer.parseInt(edPrice.getText().toString()));
                int id = ShoeDataQuery.update(this,sh);
                if( id >= 0) {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Huỷ",(dialog,which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }
    void addShoeDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.dialog_add_shoe,null);
        alertDialog.setView(dialogView);

//        EditText edID = (EditText) dialogView.findViewById(R.id.edID);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
        EditText edType = (EditText) dialogView.findViewById(R.id.edType);
        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice);


        alertDialog.setPositiveButton("Đồng ý", (dialog,which) -> {
//            int idEd = Integer.parseInt(edID.getText().toString());
            String name = edName.getText().toString();
            String avatar = edAvatar.getText().toString();
            String type = edType.getText().toString();
            String priceStr = edPrice.getText().toString();
            int price = 0;
            if(!priceStr.isEmpty()) {
                price = Integer.parseInt(priceStr);
            } else {
                Toast.makeText(this, "Nhập dữ liệu không đúng", Toast.LENGTH_SHORT).show();
            }
            if(name.isEmpty() || type.isEmpty() || avatar.isEmpty()) {
                Toast.makeText(this, "Nhập dữ liệu không đúng", Toast.LENGTH_SHORT).show();
            } else if(price <= 0) {
                Toast.makeText(this, "Giá tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            }
            else {
                Shoes sh = new Shoes(name,avatar,price,type);
                long id = ShoeDataQuery.insert(this,sh);
                if( id > 0) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Huỷ",(dialog,which)->{
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();

    }
}