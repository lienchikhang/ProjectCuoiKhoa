package com.example.projectcuoikhoa.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDBHelper;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.ShoesAdapterAdmin;
import com.example.projectcuoikhoa.Ultils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainAdminFragment extends Fragment implements ShoesAdapterAdmin.ShoesCallBackAdmin, ShoesAdapter.ShoesCallBack{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainAdminFragment newInstance(String param1, String param2) {
        MainAdminFragment fragment = new MainAdminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Boolean checkButtonCLick;
    String type;
    int op;
    RecyclerView rvList;
    ArrayList<Shoes> list;
    ShoesAdapter shoesAdapterAdmin;

    FloatingActionButton fbAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_admin, container, false);
        fbAdd = view.findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view1 -> addShoeDialog());
        rvList = view.findViewById(R.id.rvGridAdmin);

        list = ShoeDataQuery.getAll(getActivity());
        shoesAdapterAdmin = new ShoesAdapter(list,this);
        shoesAdapterAdmin.setCallBack(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setAdapter(shoesAdapterAdmin);
        rvList.setLayoutManager(linearLayoutManager);



        return view;
    }



    void LoadData() {
        list = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            if(i < 10) {
                list.add(new Shoes(i, "giày " + i,"giay_0" + i + ".png",i+"00.000 VNĐ","Chạy bộ"));
            } else {
                int ndu = i % 10;
                int nNg = i / 10;
                list.add(new Shoes(i, "giày " + i,"giay_" + i + ".png",nNg + "." + ndu + "00.000 VNĐ","Đi bộ"));
            }

        }
    }


    @Override
    public void onItemClick(String id) {
    }

    @Override
    public void onItemDeleteClick(Shoes sh, int position) {
        boolean rs = ShoeDataQuery.delete(getActivity(),sh.getId());
        if(rs) {
            Toast.makeText(getActivity(), "xoa thanh cong", Toast.LENGTH_SHORT).show();
            resetData();
        } else {
            Toast.makeText(getActivity(), "xoa khong thanh cong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemEditClick(Shoes sh, int position) {
        updateShoeDialog(sh);
    }

    void resetData() {
        list.clear();
        list.addAll(ShoeDataQuery.getAll(getActivity()));
//        ShoesAdapter.notifyDataSetChanged();
    }

    void updateShoeDialog(Shoes sh) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
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

            if(sh.getName().isEmpty()) {
                Toast.makeText(getActivity(), "vui long nhap du lieu", Toast.LENGTH_SHORT).show();
            } else {
                int id = ShoeDataQuery.update(getActivity(),sh);
                if( id >= 0) {
                    Toast.makeText(getActivity(), "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
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
    void addShoeDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Them moi");
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.dialog_add_shoe,null);
        alertDialog.setView(dialogView);

        EditText edID = (EditText) dialogView.findViewById(R.id.edID);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
        EditText edType = (EditText) dialogView.findViewById(R.id.edType);
        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice);


        alertDialog.setPositiveButton("dong y", (dialog,which) -> {
            int idEd = Integer.parseInt(edID.getText().toString());
            String name = edName.getText().toString();
            String avatar = edAvatar.getText().toString();
            String type = edType.getText().toString();
            String price = edPrice.getText().toString();
            if(name.isEmpty()) {
                Toast.makeText(getActivity(), "nhap du lieu khong dung", Toast.LENGTH_SHORT).show();
            } else {
                Shoes sh = new Shoes(idEd, name,avatar,price,type);
                long id = ShoeDataQuery.insert(getActivity(),sh);
                if( id >= 0) {
                    Toast.makeText(getActivity(), "them thanh cong", Toast.LENGTH_SHORT).show();
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