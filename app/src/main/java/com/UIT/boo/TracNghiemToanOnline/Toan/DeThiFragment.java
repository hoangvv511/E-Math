package com.UIT.boo.TracNghiemToanOnline.Toan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.UIT.boo.TracNghiemToanOnline.R;
import com.UIT.boo.TracNghiemToanOnline.slide.ScreenSlideActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class DeThiFragment extends Fragment {

    ExamAdapter examAdapter;
    ListView gvExam;
    ArrayList<Exam> arr_exam= new ArrayList<Exam>();
    TextView tv_soluongde;
    public DeThiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_de_thi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_soluongde = getActivity().findViewById(R.id.tv_soluongde);
        gvExam=(ListView) getActivity().findViewById(R.id.gvExam);

        //Doi voi de thi goc thi chi can them ten, may thong tin sau giu nguyen hoac thay doi tuy de
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
        arr_exam.add(new Exam("Đề thi Minh họa kỳ thi THPT Quốc Gia năm 2018 số 14","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi THPT Chuyên Phan Bội Châu - Nghệ An - Năm 2018","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi THPT Chuyên Lê Quý Đôn - Quảng Trị lần 1","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi Minh họa kỳ thi THPT Quốc Gia năm 2018 số 93","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi Minh họa kỳ thi THPT Quốc Gia năm 2018 số 147","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Chuyên Quang Trung lần 1","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - THPT CHUYÊN Hoàng Văn Thụ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - THPT chuyên Thái Bình  - lần 1","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - THPT Bình Gianh  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - THPT Lê Khiết - Quảng Ngãi  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));

        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 21  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 22 ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 23 ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 24 ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 25  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 26  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 27  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 28  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 29  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 30  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));

        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 31  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 32  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 33  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 34  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 35  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));

        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 36  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 37  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 38  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 39  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));
        arr_exam.add(new Exam("Đề thi thử THPT Quốc Gia - 40  ","90 phút","50 câu","http://file.vforum.vn/hinh/2014/11/admin.png","Admin"));

        tv_soluongde.setText("ĐỀ THI TỔNG HỢP " + "( " + arr_exam.size() + " ĐỀ" + " )");
        examAdapter=new ExamAdapter(getActivity(),arr_exam);
        gvExam.setAdapter(examAdapter);
        gvExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getActivity(), ScreenSlideActivity.class);
                intent.putExtra("num_exam",i);
                intent.putExtra("tendethi", arr_exam.get(i).getName());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });

    }
}
