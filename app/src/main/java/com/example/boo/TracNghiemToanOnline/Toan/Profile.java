package com.example.boo.TracNghiemToanOnline.Toan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.boo.TracNghiemToanOnline.MainActivity;
import com.example.boo.TracNghiemToanOnline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Thông tin cá nhân");
        return inflater.inflate(R.layout.profile, container, false);
    }

}
