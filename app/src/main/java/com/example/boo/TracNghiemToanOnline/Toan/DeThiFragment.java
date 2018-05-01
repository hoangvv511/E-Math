package com.example.boo.TracNghiemToanOnline.Toan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.boo.TracNghiemToanOnline.MainActivity;
import com.example.boo.TracNghiemToanOnline.R;
import com.example.boo.TracNghiemToanOnline.slide.ScreenSlideActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeThiFragment extends Fragment {

    ExamAdapter examAdapter;
    ListView gvExam;
    ArrayList<Exam> arr_exam= new ArrayList<Exam>();

    public DeThiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Đề thi tổng hợp");
        return inflater.inflate(R.layout.fragment_de_thi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gvExam=(ListView) getActivity().findViewById(R.id.gvExam);
        arr_exam.add(new Exam("Đề thi Minh họa kỳ thi THPT Quốc Gia năm 2018 số 01", "90 phút", "50 câu" ,"http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia năm 2018 - THPT Chuyên Thái Nguyên lần 1","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia năm 2018 - THPT Chuyên Thái Bình lần 3","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - Đại học Vinh","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia năm 2018 - VIETMATHS.NET - Đề 02","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - THPT Lê Văn Thịnh - Bắc Ninh lần 1","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi THPT Quốc Gia Lần 1 - THPT Kim Liên - Hà Nội","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia năm 2018 - VIETMATHS.NET - Đề 01","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi Minh họa kỳ thi THPT Quốc Gia năm 2018 - Bộ GD và ĐT","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - THPT Chuyên Hùng Vương - Bình Dương","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));

        examAdapter=new ExamAdapter(getActivity(),arr_exam);
        gvExam.setAdapter(examAdapter);
        gvExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getActivity(), ScreenSlideActivity.class);
                intent.putExtra("num_exam",i);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });

    }
}
