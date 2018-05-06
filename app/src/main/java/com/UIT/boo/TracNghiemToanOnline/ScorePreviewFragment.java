package com.UIT.boo.TracNghiemToanOnline;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lego.TracNghiemToanOnline.R;
import com.UIT.boo.TracNghiemToanOnline.question.Question;
import com.UIT.boo.TracNghiemToanOnline.slide.TestDoneActivity;
import com.amulyakhare.textdrawable.TextDrawable;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScorePreviewFragment extends DialogFragment {
    public static final String TAG = "SCORE";

    private Button btn_huy, btn_xemchitiet;
    private TextView tv_socaudung;
    private ImageView imv_diem, imv_xeploai;
    int numNoAns=0;
    int numTrue=0;
    int numFalse=0;
    int exam;
    private ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
    private String XepLoai;
    private double TongDiem;
    private String name;

    public ScorePreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_score_preview, container, false);
        btn_huy = view.findViewById(R.id.btn_huy);
        btn_xemchitiet = view.findViewById(R.id.btn_xemchitiet);
        tv_socaudung = view.findViewById(R.id.tv_socaudung);
        imv_diem = view.findViewById(R.id.imv_diem);
        imv_xeploai = view.findViewById(R.id.imv_xeploai);

        Bundle bundle = getArguments();
        exam = bundle.getInt("exam2",0);
        arr_QuesBegin = (ArrayList<Question>) bundle.getSerializable("arr_Ques2");
        name = bundle.getString("name_exam");
        checkResult();

        TongDiem= Tinhdiem(numTrue,(numTrue+numFalse+numNoAns));
        XepLoai= Xeploai(TongDiem);

        tv_socaudung.setText("Bạn trả lời đúng : " + numTrue + " câu");
        TextDrawable drawable1 = TextDrawable.builder()
                .buildRoundRect(TongDiem+"", Color.rgb(191,0,255), 10); // radius in px
        TextDrawable drawable2 = TextDrawable.builder()
                .buildRoundRect(XepLoai, Color.rgb(215,223,1), 10); // radius in px
        imv_diem.setImageDrawable(drawable1);
        imv_xeploai.setImageDrawable(drawable2);

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        btn_xemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TestDoneActivity.class);
                intent.putExtra("arr_Ques", arr_QuesBegin);
                intent.putExtra("exam", exam);
                intent.putExtra("name_exam", name);
                startActivity(intent);
                getDialog().dismiss();
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });

        return view;
    }

    //PT Check kết quả
    public void checkResult(){
        for(int i=0; i< arr_QuesBegin.size(); i++)
        {
            if(arr_QuesBegin.get(i).getDapAnChon().equals(""))
            {
                numNoAns++;
            }
            else if(arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getDapAnChon())){
                numTrue++;
            }
            else
            {
                numFalse++;
            }
        }
    }

    private static String Xeploai(double diem)
    {
        if (diem >= 9)
            return "Xuất sắc";
        if (diem >= 8 && diem < 9)
            return "Giỏi";
        if (diem >= 6.5 && diem < 8)
            return "Khá";
        if (diem >= 5 && diem < 6.5)
            return "Trung bình";
        if (diem >= 3.5 && diem < 5)
            return "Yếu";
        if (diem < 3.5)
            return "Kém";
        return "";
    }

    private static double Tinhdiem(double socaudung, double tongsocau)
    {
        double a = (socaudung / tongsocau) * 10;
        a = (double) Math.round(a * 10) / 10;
        return a;
    }

}
