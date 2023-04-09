package com.example.projectcuoikhoa.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.activity.DetailActivity;
import com.example.projectcuoikhoa.OptionFragment.FirstFragment;
import com.example.projectcuoikhoa.OptionFragment.FouthFragment;
import com.example.projectcuoikhoa.OptionFragment.SecondFragment;
import com.example.projectcuoikhoa.OptionFragment.ThirdFragment;
import com.example.projectcuoikhoa.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListFragment extends Fragment implements ShoesGridAdapter.UserGridCallBack {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(String param1, String param2) {
        ProductListFragment fragment = new ProductListFragment();
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

    LinearLayout firstOption, secondOption, thirdOption, fouthOption;
    ArrayList<Shoes> list;
    Boolean checkButtonCLick;
    String type;
    int op;
    ImageButton ivBackFrag;
    ShoesGridAdapter shoesGridAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        rvProductList = view.findViewById(R.id.rvGridProductList);

        list = ShoeDataQuery.getAll(getActivity());

        shoesGridAdapter = new ShoesGridAdapter(list, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rvProductList.setAdapter(shoesGridAdapter);
        rvProductList.setLayoutManager(gridLayoutManager);

        ivBackFrag = view.findViewById(R.id.ivBackFrag);
        ivBackFrag.setOnClickListener(view1 -> getParentFragmentManager().popBackStack());
        //anh xa 4 options
        anhXaOption();
        makeBtnDefault2(firstOption,secondOption,thirdOption,fouthOption);
        //onClickListener
        getOptionsListener();
        return view;
    }

    public static final String tag = ProductListFragment.class.getName();
    @NonNull
    private View.OnClickListener getListenerOption() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.firstOption:
                        type = "run";
                        BtnClick(firstOption);
                        makeBtnDefault(secondOption,thirdOption, fouthOption);
                        loadFragment(new FirstFragment(type));
                        break;
                    case R.id.secondOption:
                        type = "walk";
                        op = 2;
                        BtnClick(secondOption);
                        makeBtnDefault(firstOption,thirdOption, fouthOption);
                        loadFragment(new SecondFragment(type,op));
                        break;
                    case R.id.thirdOption:
                        BtnClick(thirdOption);
                        makeBtnDefault(secondOption,firstOption, fouthOption);
                        loadFragment(new ThirdFragment());
                        break;
                    case R.id.fouthOption:
                        BtnClick(fouthOption);
                        makeBtnDefault(secondOption,thirdOption, firstOption);
                        loadFragment(new FouthFragment());
                        break;
                }
            }
        };
    }



    void anhXaOption() {
        firstOption = getActivity().findViewById(R.id.firstOption);
        secondOption = getActivity().findViewById(R.id.secondOption);
        thirdOption = getActivity().findViewById(R.id.thirdOption);
        fouthOption = getActivity().findViewById(R.id.fouthOption);
    }

    void loadFragment(Fragment fmNew) {
        FragmentTransaction fmOld = getParentFragmentManager().beginTransaction();
        fmOld.replace(R.id.main_fragment, fmNew);
        fmOld.addToBackStack(null);
        fmOld.commit();
    }

    void loadBackFragment() {
        getActivity().getSupportFragmentManager().popBackStack();

    }

    void getOptionsListener() {
        firstOption.setOnClickListener(getListenerOption());
        secondOption.setOnClickListener(getListenerOption());
        thirdOption.setOnClickListener(getListenerOption());
        fouthOption.setOnClickListener(getListenerOption());
    }

    @Override
    public void onItemClick(String id) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    void BtnClick(LinearLayout option){
        checkButtonCLick=true;
//        option.findViewById(R.id.firstOptionName).setTextColor(Color.parseColor("#F5F5F5"));
        option.setBackgroundResource(R.drawable.custom_btn_border);

    }

    void makeBtnDefault(LinearLayout option2,LinearLayout option3, LinearLayout option4){
        option2.setBackgroundResource(R.drawable.btn_size_default);
        option3.setBackgroundResource(R.drawable.btn_size_default);
        option4.setBackgroundResource(R.drawable.btn_size_default);
    }

    void makeBtnDefault2(LinearLayout option1, LinearLayout option2,LinearLayout option3, LinearLayout option4){
        option1.setBackgroundResource(R.drawable.btn_size_default);
        option2.setBackgroundResource(R.drawable.btn_size_default);
        option3.setBackgroundResource(R.drawable.btn_size_default);
        option4.setBackgroundResource(R.drawable.btn_size_default);
    }
}