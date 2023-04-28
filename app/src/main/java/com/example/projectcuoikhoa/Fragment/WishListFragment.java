package com.example.projectcuoikhoa.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projectcuoikhoa.Adapter.ShoesAdapter;
import com.example.projectcuoikhoa.Adapter.ShoesAdapterAdmin;
import com.example.projectcuoikhoa.Adapter.ShoesGridAdapter;
import com.example.projectcuoikhoa.Adapter.ShoesWishListAdapter;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.activity.DetailActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishListFragment extends Fragment implements ShoesAdapterAdmin.ShoesCallBackAdmin, ShoesAdapter.ShoesCallBack, ShoesGridAdapter.UserGridCallBack, ShoesWishListAdapter.UserGridCallBack{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WishListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishListFragment newInstance(String param1, String param2) {
        WishListFragment fragment = new WishListFragment();
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

    RecyclerView rvWishList;
    ArrayList<Shoes> list;
    ShoesAdapter shoesAdapter;
    ImageButton ivBackBtnWishList;
    ShoesGridAdapter shoesGridAdapter;
    ShoesWishListAdapter shoesWishListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        rvWishList = view.findViewById(R.id.rvWishList);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        int idUserIn = sharedPreferences.getInt("id",getActivity().MODE_PRIVATE);
        list = new ArrayList<>();
//        resetData(idUserIn);
        list = ShoeDataQuery.getAllWishList(getActivity(),idUserIn);
        shoesWishListAdapter = new ShoesWishListAdapter(list,this);
        shoesWishListAdapter = new ShoesWishListAdapter(list,this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rvWishList.setAdapter(shoesWishListAdapter);
        rvWishList.setLayoutManager(gridLayoutManager);

        return view;
    }

    @Override
    public void onItemClick(String id) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra("id", id);
        Toast.makeText(getActivity(), "idshoe: " + id, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    @Override
    public void onLikeBtnClick(String id, View view) {

    }
    int idUserIn;
    @Override
    public void onLikeCancel(String id) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
        idUserIn = sharedPreferences.getInt("id", getActivity().MODE_PRIVATE);
        if (idUserIn == 0) {
            Toast.makeText(getActivity(), "Vui long dang nhap", Toast.LENGTH_SHORT).show();
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
        shoesWishListAdapter.notifyDataSetChanged();
    }

    void resetData(int id) {
        list.clear();
        list.addAll(ShoeDataQuery.getAllWishList(getActivity(),id));
        shoesAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemDeleteClick(Shoes sh, int position) {

    }

    @Override
    public void onItemEditClick(Shoes sh, int position) {

    }
}