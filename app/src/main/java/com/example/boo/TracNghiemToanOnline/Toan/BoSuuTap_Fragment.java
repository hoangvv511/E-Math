package com.example.boo.TracNghiemToanOnline.Toan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boo.TracNghiemToanOnline.MainActivity;
import com.example.boo.TracNghiemToanOnline.R;
import com.example.boo.TracNghiemToanOnline.slide.ScreenSlideActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoSuuTap_Fragment extends Fragment {

    ExamAdapter examAdapter;
    ListView lvCreateTest;
    ArrayList<Exam> arr_examcreate= new ArrayList<Exam>();
    String name, tende, xacnhan;
    TextView tv_notest;
    public BoSuuTap_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Bộ sưu tập");
        View view = inflater.inflate(R.layout.fragment_bo_suu_tap_, container, false);
        tv_notest = view.findViewById(R.id.tv_notest);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try
        {
            Bundle bundle = this.getArguments();
            name = bundle.getString("Name");
            lvCreateTest= getActivity().findViewById(R.id.gvBoSuuTap);
            arr_examcreate.add(new Exam(name, "90", "http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));

            examAdapter=new ExamAdapter(getActivity(),arr_examcreate);
            lvCreateTest.setAdapter(examAdapter);
            lvCreateTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent= new Intent(getActivity(), ScreenSlideActivity.class);
                    tende = String.valueOf(name);
                    intent.putExtra("TenDe",tende);
                    startActivity(intent);
                }
            });

        }
        catch (Exception e)
        {
            tv_notest.setVisibility(getView().VISIBLE);
        }
    }
}
