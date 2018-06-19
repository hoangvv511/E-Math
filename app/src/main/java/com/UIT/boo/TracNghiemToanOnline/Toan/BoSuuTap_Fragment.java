package com.UIT.boo.TracNghiemToanOnline.Toan;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.UIT.boo.TracNghiemToanOnline.UserExam;
import com.UIT.boo.TracNghiemToanOnline.slide.ScreenSlideActivity;
import com.baoyz.widget.PullRefreshLayout;
import com.UIT.boo.TracNghiemToanOnline.MainActivity;
import com.UIT.boo.TracNghiemToanOnline.TaoDeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.FloatingActionButton;

import com.UIT.boo.TracNghiemToanOnline.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoSuuTap_Fragment extends Fragment {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private ExamAdapter examAdapter;
    private ListView lvCreateTest;
    private ArrayList<Exam> arr_examcreate= new ArrayList<Exam>();
    private ArrayList<String> tendethi, thoigian, tongsocau;
    private ArrayList<Map<String, Long>> idcauhoi;
    private String dethi, time, tongcau;
    private TextView tv_notest;
    private FloatingActionButton btn_taode;
    private PullRefreshLayout layout;
    private TextView tv_soluongde;

    Map<String, Integer> map = new HashMap<String, Integer>();
    public BoSuuTap_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bo_suu_tap_, container, false);
        tv_notest = view.findViewById(R.id.tv_notest);
        btn_taode = view.findViewById(R.id.btnTaode);
        lvCreateTest= view.findViewById(R.id.gvBoSuuTap);
        tv_soluongde = view.findViewById(R.id.tv_soluongde2);

        tongsocau = new ArrayList<String>();
        thoigian = new ArrayList<String>();
        tendethi = new ArrayList<String>();
        idcauhoi = new ArrayList<Map<String, Long>>();
        layout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        btn_taode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), TaoDeActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });

        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                Refresh();
            }
        });
        return view;
    }

    public void Refresh()
    {
        arr_examcreate.clear();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setRefreshing(false);
            }
        }, 1500);
        try
        {
            CapNhatDanhSachDe();
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Refresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //CapNhatDanhSachDe();
    }

    public void CapNhatDanhSachDe()
    {
        tendethi.clear();
        thoigian.clear();
        tongsocau.clear();
        idcauhoi.clear();
        databaseRefence.child("Đề thi").child(MainActivity.username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (final DataSnapshot data : dataSnapshot.getChildren()) {
                        final String tende = data.getKey();
                        databaseRefence.child("Đề thi").child(MainActivity.username).child(tende).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot != null) {
                                    UserExam userExam = dataSnapshot.getValue(UserExam.class);
                                    Map<String, Long> map = userExam.cauhoi;
                                    dethi = userExam.tendethi;
                                    time = userExam.thoigian;
                                    tongcau = userExam.socauhoi;
                                    tendethi.add(dethi);
                                    thoigian.add(time);
                                    tongsocau.add(tongcau);
                                    idcauhoi.add(map);
                                    arr_examcreate.add(new Exam(dethi, time + " phút", tongcau + " câu", MainActivity.imageavatar, MainActivity.username));

                                    if (getActivity() != null) {
                                        examAdapter = new ExamAdapter(getActivity(), arr_examcreate);
                                        lvCreateTest.setAdapter(examAdapter);
                                        lvCreateTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                                                intent.putExtra("TenDe", tendethi.get(i));
                                                intent.putExtra("Thoigian", thoigian.get(i));
                                                intent.putExtra("SoCau", tongsocau.get(i));
                                                intent.putExtra("Cauhoi", (Serializable) idcauhoi.get(i));
                                                startActivity(intent);
                                                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                                            }
                                        });
                                    }
                                    tv_soluongde.setText("KHO ĐỀ THI CỦA BẠN " + "( " + arr_examcreate.size() + " ĐỀ )");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                }
            }
                @Override
                public void onCancelled (DatabaseError databaseError){
                }
        });
    }
}
