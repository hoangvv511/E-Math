package com.example.boo.TracNghiemToanOnline.Toan;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
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
import com.example.boo.TracNghiemToanOnline.UserInformation;
import com.example.boo.TracNghiemToanOnline.slide.ScreenSlideActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoSuuTap_Fragment extends Fragment {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    ExamAdapter examAdapter;
    ListView lvCreateTest;
    ArrayList<Exam> arr_examcreate= new ArrayList<Exam>();
    public String tendethi, thoigian, tongsocau;
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
            lvCreateTest= getActivity().findViewById(R.id.gvBoSuuTap);

            databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(final DataSnapshot data : dataSnapshot.getChildren()){
                        final String tende = data.getKey();
                        databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").child(tende).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                tendethi = data.child("Tên đề thi").getValue().toString();
                                thoigian = data.child("Thời gian").getValue().toString();
                                tongsocau = data.child("Tổng số câu").getValue().toString();
                                arr_examcreate.add(new Exam(tendethi,thoigian,tongsocau,MainActivity.imageavatar, MainActivity.username));
                                examAdapter=new ExamAdapter(getActivity(),arr_examcreate);
                                lvCreateTest.setAdapter(examAdapter);
                                lvCreateTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                                        intent.putExtra("TenDe", tendethi);
                                        intent.putExtra("Thoigian", thoigian);
                                        intent.putExtra("SoCau", tongsocau);
                                        startActivity(intent);
                                    }
                                });
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        catch (Exception e)
        {
            tv_notest.setVisibility(getView().VISIBLE);
        }
    }
}
