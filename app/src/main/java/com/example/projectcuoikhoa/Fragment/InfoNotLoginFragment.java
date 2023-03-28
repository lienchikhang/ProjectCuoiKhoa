package com.example.projectcuoikhoa.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectcuoikhoa.LoginActivity;
import com.example.projectcuoikhoa.R;
import com.example.projectcuoikhoa.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoNotLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoNotLoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Boolean isLogined;

    public InfoNotLoginFragment() {}
    public InfoNotLoginFragment(Boolean isLogin) {
        // Required empty public constructor
        isLogined = isLogin;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoNotLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoNotLoginFragment newInstance(String param1, String param2) {
        InfoNotLoginFragment fragment = new InfoNotLoginFragment();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_not_login, container, false);
        Button btnLogin = view.findViewById(R.id.btnLoginInfo);

        btnLogin.setOnClickListener(getListernerInfo());

        return view;
    }

    @NonNull
    private View.OnClickListener getListernerInfo() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnLoginInfo:
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        i.putExtra("bool",isLogined);
                        startActivity(i);
                        break;
                    case R.id.btnRegisInfo:
                        i = new Intent(getActivity(), RegisterActivity.class);
                        startActivity(i);
                        break;
                }
            }
        };
    }
}