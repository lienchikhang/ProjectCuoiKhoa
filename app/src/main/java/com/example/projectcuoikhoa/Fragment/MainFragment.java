package com.example.projectcuoikhoa.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.projectcuoikhoa.Clothes;
import com.example.projectcuoikhoa.ClothesAdapter;
import com.example.projectcuoikhoa.ClothesGridAdapter;
import com.example.projectcuoikhoa.DetailActivity;
import com.example.projectcuoikhoa.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements ClothesAdapter.UserCallBack, ClothesGridAdapter.UserGridCallBack {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
    RecyclerView rvList, rvGridMain;
    ArrayList<Clothes> list;
    ClothesAdapter clothesAdapter;
    ClothesGridAdapter clothesGridAdapter;

    TextView tvMoreCate, tvMoreCate2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //ANH XA
        rvList = view.findViewById(R.id.rwProduct);
        rvGridMain = view.findViewById(R.id.rvGridMain);
        tvMoreCate = view.findViewById(R.id.tvMoreCate);
        tvMoreCate2 = view.findViewById(R.id.tvMoreCate2);

        //load du lieu
        LoadData();

        //tao view
        clothesAdapter = new ClothesAdapter(list, this);
        clothesGridAdapter = new ClothesGridAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rvList.setAdapter(clothesAdapter);
        rvGridMain.setAdapter(clothesGridAdapter);
        rvList.setLayoutManager(linearLayoutManager);
        rvGridMain.setLayoutManager(gridLayoutManager);



        tvMoreCate.setOnClickListener(getL());
        tvMoreCate2.setOnClickListener(getL());


        return view;
    }

    @NonNull
    private View.OnClickListener getL() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tvMoreCate:
                        loadFragment(new ProductListFragment());
                        break;
                    case R.id.tvMoreCate2:
                        loadFragment(new ProductListFragment());
                        break;
                }
            }
        };
    }

    void loadFragment(Fragment fmNew) {
        FragmentTransaction fmOld = getParentFragmentManager().beginTransaction();
        fmOld.replace(R.id.main_fragment, fmNew);
        //add fragment muon chuyen sang vao backstack thi khi qua fragment muon chuyen sang, su dung popPackStack thi moi chuyen lai
        fmOld.addToBackStack(ProductListFragment.tag);
        fmOld.commit();

    }
    void LoadData() {
        list = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            if(i < 10) {
                list.add(new Clothes(String.valueOf(i), "giày " + i,"giay_0" + i + ".png",i+"00.000 VNĐ"));
            } else {
                int ndu = i % 10;
                int nNg = i / 10;
                list.add(new Clothes(String.valueOf(i), "giày " + i,"giay_0" + i + ".png",nNg + "." + ndu + "00.000 VNĐ"));
            }

        }
    }

    @Override
    public void onItemClick(String id) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

}