package com.example.boo.TracNghiemToanOnline.Toan;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;

import com.example.boo.TracNghiemToanOnline.MainActivity;
import com.example.boo.TracNghiemToanOnline.R;
import com.example.boo.TracNghiemToanOnline.slide.ScreenSlideActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaoDeFragment extends Fragment {

    EditText edtTenDeThi, edtSoCauHoi, edtThoiGian;
    CheckBox cB1, cB2, cB3, cB4, cB5, cB6, cB7, cB8, cB9;
    Button btnTaoDe;
    String tende;
    public TaoDeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Tạo đề thi");
        View view = inflater.inflate(R.layout.fragment_tao_de, container, false);
        edtTenDeThi = view.findViewById(R.id.edtTenDe);
        edtSoCauHoi = view.findViewById(R.id.edtSoCauHoi);
        edtThoiGian = view.findViewById(R.id.edtThoiGian);
        btnTaoDe = view.findViewById(R.id.btn_taode);
        btnTaoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoSuuTap_Fragment boSuuTap_fragment = new BoSuuTap_Fragment();
                Bundle bundle = new Bundle();
                tende = String.valueOf(edtTenDeThi.getText().toString());
                bundle.putString("Name", tende);
                boSuuTap_fragment.setArguments(bundle);
                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.content_main, boSuuTap_fragment);
                fr.addToBackStack(null);
                fr.commit();
            }
        });
        return view;
    }

}
