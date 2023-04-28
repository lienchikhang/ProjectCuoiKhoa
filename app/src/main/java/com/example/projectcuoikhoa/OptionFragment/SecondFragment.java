package com.example.projectcuoikhoa.OptionFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.activity.DetailActivity;
import com.example.projectcuoikhoa.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment implements ShoesGridAdapter.UserGridCallBack {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public SecondFragment(String type, int op) {

        chooseType = type;
        opt = op;
    }
    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    RecyclerView rvProductList;

    ArrayList<Shoes> list, preList;
    ImageButton ivBackFrag2;
    ShoesGridAdapter shoesGridAdapter;
    String chooseType;
    int opt;
    LinearLayout firstOption, secondOption, thirdOption, fouthOption;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        rvProductList = view.findViewById(R.id.rvGridProductList);
        ivBackFrag2 = view.findViewById(R.id.ivBackFrag2);
        anhXaOption();
        ivBackFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager().popBackStack();
            }
        });
        list = ShoeDataQuery.FilterData(getActivity(),chooseType);

        shoesGridAdapter = new ShoesGridAdapter(list, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rvProductList.setAdapter(shoesGridAdapter);
        rvProductList.setLayoutManager(gridLayoutManager);
        return view;
    }
    @Override
    public void onItemClick(String id) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    @Override
    public void onLikeBtnClick(String idShoe, View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        int idUserIn = sharedPreferences.getInt("id", getActivity().MODE_PRIVATE);
        if (idUserIn == 0) {
            Toast.makeText(getActivity(), "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
            return;
        }
        Shoes sh = ShoeDataQuery.getShoes(getActivity(), Integer.parseInt(idShoe));
        sharedPreferences = getActivity().getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        int idUser = sharedPreferences.getInt("id", getActivity().MODE_PRIVATE);
        long id = ShoeDataQuery.insertToWishList(getActivity(), sh, idUser);
        if (id > 0) {
            Toast.makeText(getActivity(), "đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "đã tồn tại trong yêu thích", Toast.LENGTH_SHORT).show();
        }
    }
    public int idUserIn;
    @Override
    public void onLikeCancel(String id) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        idUserIn = sharedPreferences.getInt("id", getActivity().MODE_PRIVATE);
        if (idUserIn == 0) {
            Toast.makeText(getActivity(), "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean rs = ShoeDataQuery.deleteFromWishList(getActivity(),Integer.parseInt(id),idUserIn);
        if(rs) {
            Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
            resetData();
        } else {
            Toast.makeText(getActivity(), "Xoá không thành công", Toast.LENGTH_SHORT).show();
        }
    }
    void resetData() {
        list.clear();
        list.addAll(ShoeDataQuery.getAllWishList(getActivity(),idUserIn));
    }
    void makeBtnDefault(LinearLayout option2,LinearLayout option3, LinearLayout option4){
        option2.setBackgroundResource(R.drawable.btn_size_default);
        option3.setBackgroundResource(R.drawable.btn_size_default);
        option4.setBackgroundResource(R.drawable.btn_size_default);
    }

    void anhXaOption() {
        firstOption = getActivity().findViewById(R.id.firstOption);
        secondOption = getActivity().findViewById(R.id.secondOption);
        thirdOption = getActivity().findViewById(R.id.thirdOption);
        fouthOption = getActivity().findViewById(R.id.fouthOption);
    }

    void BtnClick(LinearLayout option){
//        option.findViewById(R.id.firstOptionName).setTextColor(Color.parseColor("#F5F5F5"));
        option.setBackgroundResource(R.drawable.custom_btn_border);

    }
}