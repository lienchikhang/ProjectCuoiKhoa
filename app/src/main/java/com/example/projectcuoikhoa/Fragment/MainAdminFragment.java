package com.example.projectcuoikhoa.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.Adapter.ShoesAdapterAdmin;
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
    ShoesAdapterAdmin shoesAdapterAdmin;

    HorizontalScrollView scrollView;
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
//        View view = inflater.inflate(R.layout.fragment_main_admin, container, false);
//        fbAdd = view.findViewById(R.id.fbAdd);
//        fbAdd.setOnClickListener(view1 -> addShoeDialog());
//        rvList = view.findViewById(R.id.rvGridAdmin);
//
//
//        list = ShoeDataQuery.getAll(getActivity());
//        shoesAdapterAdmin = new ShoesAdapterAdmin(list,this);
//        shoesAdapterAdmin.setCallBackAdmin(this);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        rvList.setAdapter(shoesAdapterAdmin);
//        rvList.setLayoutManager(linearLayoutManager);
//
//
//
//        return view;
        return null;
    }

    @Override
    public void onItemClick(String id) {

    }

    @Override
    public void onItemDeleteClick(Shoes sh, int position) {

    }

    @Override
    public void onItemEditClick(Shoes sh, int position) {

    }


//    @Override
//    public void onItemClick(String id) {
//    }
//
//    @Override
//    public void onItemDeleteClick(Shoes sh, int position) {
//        boolean rs = ShoeDataQuery.delete(getActivity(),sh.getId());
//        if(rs) {
//            Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
//            resetData();
//        } else {
//            Toast.makeText(getActivity(), "Xoá không thành công", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onItemEditClick(Shoes sh, int position) {
//        updateShoeDialog(sh);
//    }
//
//    void resetData() {
//        list.clear();
//        list.addAll(ShoeDataQuery.getAll(getActivity()));
//        shoesAdapterAdmin.notifyDataSetChanged();
//    }
//
//    void updateShoeDialog(Shoes sh) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//        alertDialog.setTitle("Cập Nhật");
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialog_add_shoe,null);
//        alertDialog.setView(dialogView);
//
//        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
//        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
//        EditText edType = (EditText) dialogView.findViewById(R.id.edType);
//        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice);
//
//        //gan du lieu
//        edAvatar.setText(sh.getImage());
//        edName.setText(sh.getName());
//        edPrice.setText(sh.getPrice());
//        edType.setText(sh.getType());
//
//        alertDialog.setPositiveButton("Đồng ý", (dialog,which) -> {
//            sh.setName(edName.getText().toString());
//            sh.setImage(edAvatar.getText().toString());
//            sh.setPrice(Integer.parseInt(edPrice.getText().toString()));
//            sh.setType(edType.getText().toString());
//
//            if(sh.getName().isEmpty()) {
//                Toast.makeText(getActivity(), "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
//            } else {
//                int id = ShoeDataQuery.update(getActivity(),sh);
//                if( id >= 0) {
//                    Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//                    resetData();
//                    dialog.dismiss();
//                }
//            }
//        });
//        alertDialog.setNegativeButton("Huỷ",(dialog,which)->{
//            dialog.dismiss();
//        });
//        alertDialog.create();
//        alertDialog.show();
//    }
//    void addShoeDialog() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//        alertDialog.setTitle("Thêm mới");
//        LayoutInflater layoutInflater = this.getLayoutInflater();
//        View dialogView = layoutInflater.inflate(R.layout.dialog_add_shoe,null);
//        alertDialog.setView(dialogView);
//
////        EditText edID = (EditText) dialogView.findViewById(R.id.edID);
//        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
//        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
//        EditText edType = (EditText) dialogView.findViewById(R.id.edType);
//        EditText edPrice = (EditText) dialogView.findViewById(R.id.edPrice);
//
//
//        alertDialog.setPositiveButton("Đồng ý", (dialog,which) -> {
////            int idEd = Integer.parseInt(edID.getText().toString());
//            String name = edName.getText().toString();
//            String avatar = edAvatar.getText().toString();
//            String type = edType.getText().toString();
//            int price = Integer.parseInt(edPrice.getText().toString());
//            if(name.isEmpty()) {
//                Toast.makeText(getActivity(), "Nhập dữ liệu không đúng", Toast.LENGTH_SHORT).show();
//            } else if(price < 0) {
//                Toast.makeText(getActivity(), "Giá tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Shoes sh = new Shoes(name,avatar,price,type);
//                long id = ShoeDataQuery.insert(getActivity(),sh);
//                if( id > 0) {
//                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                    resetData();
//                    dialog.dismiss();
//                }
//            }
//        });
//        alertDialog.setNegativeButton("Huỷ",(dialog,which)->{
//            dialog.dismiss();
//        });
//        alertDialog.create();
//        alertDialog.show();
//
//    }
}