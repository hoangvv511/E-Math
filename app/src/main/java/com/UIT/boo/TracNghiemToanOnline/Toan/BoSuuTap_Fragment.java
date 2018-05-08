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

import com.UIT.boo.TracNghiemToanOnline.SplashScreen;
import com.baoyz.widget.PullRefreshLayout;
import com.UIT.boo.TracNghiemToanOnline.MainActivity;
import com.Lego.TracNghiemToanOnline.R;
import com.UIT.boo.TracNghiemToanOnline.TaoDeActivity;
import com.UIT.boo.TracNghiemToanOnline.slide.ScreenSlideActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoSuuTap_Fragment extends Fragment {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    ExamAdapter examAdapter;
    ListView lvCreateTest;
    ArrayList<Exam> arr_examcreate= new ArrayList<Exam>();
    private ArrayList<String> tendethi, thoigian, tongsocau;
    private ArrayList<ArrayList<Long>> idcauhoi;
    String dethi, time, tongcau;
    TextView tv_notest;
    FloatingActionButton btn_taode;
    PullRefreshLayout layout;
    private ArrayList<Long> cauhoi;
    Boolean isRefresh;

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
        tongsocau = new ArrayList<String>();
        thoigian = new ArrayList<String>();
        tendethi = new ArrayList<String>();
        cauhoi = new ArrayList<Long>();
        idcauhoi = new ArrayList<ArrayList<Long>>();
        layout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);


        //databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").keepSynced(true);
        btn_taode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), TaoDeActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
//        CapNhatDanhSachDe();
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                arr_examcreate.clear();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 3000);
                try
                {
                    CapNhatDeThi();
                }
                catch (Exception e)
                {

                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void CapNhatDeThi()
    {
        arr_examcreate.clear();
        SplashScreen.databaseRefence.child("Đề thi").child(MainActivity.username).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(final DataSnapshot data : dataSnapshot.getChildren()) {
                    final String tende = data.getKey();
                    SplashScreen.databaseRefence.child("Đề thi").child(MainActivity.username).child(tende).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            if(dataSnapshot != null)
                            {
                                Map<String , Long> map = (Map<String, Long>) data.child("Câu hỏi").getValue();
                                for (Map.Entry<String, Long> entry : map.entrySet()) {
                                    cauhoi.add(entry.getValue());
                                }
                                dethi = data.child("Tên đề thi").getValue().toString();
                                time = data.child("Thời gian").getValue().toString();
                                tongcau = data.child("Tổng số câu").getValue().toString();
                                tendethi.add(dethi);
                                thoigian.add(time);
                                tongsocau.add(tongcau);
                                idcauhoi.add(cauhoi);
                                arr_examcreate.add(new Exam(dethi,time + " phút",tongcau + " câu", MainActivity.imageavatar, MainActivity.username));
                                examAdapter = new ExamAdapter(getActivity(), arr_examcreate);
                                lvCreateTest.setAdapter(examAdapter);
                                lvCreateTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                                        intent.putExtra("TenDe", tendethi.get(i));
                                        intent.putExtra("Thoigian", thoigian.get(i));
                                        intent.putExtra("SoCau", tongsocau.get(i));
                                        intent.putExtra("Cauhoi", idcauhoi.get(i));
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            if(dataSnapshot != null)
                            {
                                Map<String , Long> map = (Map<String, Long>) data.child("Câu hỏi").getValue();
                                for (Map.Entry<String, Long> entry : map.entrySet()) {
                                    cauhoi.add(entry.getValue());
                                }
                                dethi = data.child("Tên đề thi").getValue().toString();
                                time = data.child("Thời gian").getValue().toString();
                                tongcau = data.child("Tổng số câu").getValue().toString();
                                tendethi.add(dethi);
                                thoigian.add(time);
                                tongsocau.add(tongcau);
                                idcauhoi.add(cauhoi);
                                arr_examcreate.add(new Exam(dethi,time + " phút",tongcau + " câu", MainActivity.imageavatar, MainActivity.username));
                                examAdapter = new ExamAdapter(getActivity(), arr_examcreate);
                                lvCreateTest.setAdapter(examAdapter);
                                lvCreateTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                                        intent.putExtra("TenDe", tendethi.get(i));
                                        intent.putExtra("Thoigian", thoigian.get(i));
                                        intent.putExtra("SoCau", tongsocau.get(i));
                                        intent.putExtra("Cauhoi", idcauhoi.get(i));
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            }
        });
    }

//    public void CapNhatDanhSachDe()
//    {
//
//        databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(final DataSnapshot data : dataSnapshot.getChildren()){
//                    final String tende = data.getKey();
//                    databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").child(tende).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        }
//                        @Override
//                        public void onCancelled(DatabaseError databaseError)
//                        {
//                        }
//                    });
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
}
