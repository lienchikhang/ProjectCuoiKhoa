package com.example.projectcuoikhoa.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikhoa.Adapter.UserAdapter;
import com.example.projectcuoikhoa.ShoeDataQuery;
import com.example.projectcuoikhoa.Shoes;
import com.example.projectcuoikhoa.UserDBHelper;
import com.example.projectcuoikhoa.UserDataQuery;
import com.example.projectcuoikhoa.activity.MainActivity;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.Ultils;
import com.example.projectcuoikhoa.User;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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

    ArrayList<User> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView tvUsername = view.findViewById(R.id.tvUsername);
        TextView tvPass = view.findViewById(R.id.tvPassword);
        TextView tvPhone = view.findViewById(R.id.tvPhone);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnEditInfo = view.findViewById(R.id.btnEditInfo);
        String username = getActivity().getIntent().getStringExtra("UserName");
        String pass = getActivity().getIntent().getStringExtra("Password");
        tvUsername.setText(username);
        tvPass.setText(pass);
        btnLogout.setOnClickListener(getBtnClick());
        return view;
    }

    @NonNull
    private View.OnClickListener getBtnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnLogout:
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        SharedPreferences sharedPreferencesInfo = getActivity().getSharedPreferences("shared preferences Info", Context.MODE_PRIVATE);
                        sharedPreferencesInfo.edit().remove("id").apply();
                        SharedPreferences sharedPreferences =getActivity().getSharedPreferences("shared preferences Cart", Context.MODE_PRIVATE);
                        sharedPreferences.edit().remove("listCart").apply();
                        i.putExtra("bool", false);
                        startActivity(i);
                        break;

                }
            }
        };
    }
}