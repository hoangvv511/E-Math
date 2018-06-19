package com.UIT.boo.TracNghiemToanOnline.slide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.UIT.boo.TracNghiemToanOnline.MainActivity;

import com.UIT.boo.TracNghiemToanOnline.R;
import com.UIT.boo.TracNghiemToanOnline.TaiLieu.TailieuItemActivity;
import com.UIT.boo.TracNghiemToanOnline.question.Question;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class TestDoneActivity extends AppCompatActivity {

    private PieChart mChart;
    private Typeface tf;

    private TextView tv_cd1, tv_cd2, tv_cd3, tv_cd4, tv_cd5, tv_cd6, tv_cd7, tv_cd8;
    int numNoAns=0;
    int numTrue=0;
    int numFalse=0;
    int numFalse_1 =0;
    int numFalse_2 =0;
    int numFalse_3 =0;
    int numFalse_4 =0;
    int numFalse_5 =0;
    int numFalse_6 =0;
    int numFalse_7 =0;
    int numFalse_8 =0;
    int numFalse_9 =0;
    int exam;
    private int numNoAns_1,numNoAns_2,numNoAns_3,numNoAns_4,numNoAns_5,numNoAns_6,numNoAns_7,numNoAns_8,numNoAns_9;
    private int numcd1 = 0, numcd2 =0 , numcd3 =0 , numcd4 =0, numcd5 =0, numcd6=0, numcd7=0, numcd8=0, numcd9=0;
    private Button btnAgain, btnBack;
    private double TongDiem;
    private String XepLoai, name_exam;
    private ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
    private ArrayList<Question> arr_QuesBegin2= new ArrayList<Question>();
    private String name, thoigian,tongsocau;
    private Map<String, Long> mapID;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        tf = Typeface.createFromAsset(this.getAssets(), "OpenSans-Regular.ttf");
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);

        Intent intent = getIntent();

        //De thi trong he thong
        exam = intent.getIntExtra("exam",0);
        name_exam = intent.getStringExtra("name_exam");
        arr_QuesBegin = (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");

        //De thi do nguoi dung tao
        name = intent.getStringExtra("TenDe");
        thoigian = intent.getStringExtra("Thoigian");
        tongsocau = intent.getStringExtra("SoCau");
        arr_QuesBegin2 = (ArrayList<Question>) intent.getExtras().getSerializable("Question");
        mapID = (Map<String, Long>) intent.getSerializableExtra("Cauhoi");

        begin();
        if(arr_QuesBegin!=null)
        {
            TinhSoCauChuyenDe(arr_QuesBegin);
            checkResult(arr_QuesBegin);
        }
        else
        {
            TinhSoCauChuyenDe(arr_QuesBegin2);
            checkResult(arr_QuesBegin2);
        }
        checkGoiy();
        clickGoiy();
        TongDiem= Tinhdiem(numTrue,(numTrue+numFalse+numNoAns));
        XepLoai= Xeploai(TongDiem);

        mChart.getDescription().setEnabled(false);
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "OpenSans-Light.ttf");
        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(7f);
        mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChart.setData(generatePieData());

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog sweetAlertDialog = new  SweetAlertDialog(TestDoneActivity.this, SweetAlertDialog.WARNING_TYPE);
                sweetAlertDialog.setTitleText("Bạn muốn làm lại đề thi này ?");
                sweetAlertDialog.setCancelText("No");
                sweetAlertDialog.setConfirmText("Yes");
                sweetAlertDialog.showCancelButton(true);
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        });
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent intent= new Intent(TestDoneActivity.this, ScreenSlideActivity.class);
                                if(name_exam!= null && arr_QuesBegin!=null)
                                {
                                    intent.putExtra("num_exam",exam);
                                    intent.putExtra("tendethi",name_exam );
                                }
                                else
                                {
                                    intent.putExtra("TenDe", name);
                                    intent.putExtra("Thoigian", thoigian);
                                    intent.putExtra("SoCau", tongsocau);
                                    intent.putExtra("Cauhoi", (Serializable) mapID);
                                }
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                sweetAlertDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(TestDoneActivity.this, SweetAlertDialog.WARNING_TYPE);
                sweetAlertDialog.setTitleText("Trở lại góc học tập ?");
                sweetAlertDialog.setCancelText("No");
                sweetAlertDialog.setConfirmText("Yes");
                sweetAlertDialog.showCancelButton(true);
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        });
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i=new Intent(TestDoneActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                sweetAlertDialog.show();
            }
        });
    }

    public void TinhSoCauChuyenDe(ArrayList<Question> questions)
    {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getChuyende() == 1) numcd1++; //1.	Ứng dụng đạo hàm khảo sát sự biến thiên và vẽ đồ thị hàm số.
            if (questions.get(i).getChuyende() == 2) numcd2++; //2.	Các bài toán liên quan đến khảo sát hàm số.
            if (questions.get(i).getChuyende() == 3) numcd3++; //3.	Hàm số lũy thừa, hàm số mũ, hàm số logarit, luong giác.
            if (questions.get(i).getChuyende() == 4) numcd4++; //4.	Nguyên hàm, tích phân.
            if (questions.get(i).getChuyende() == 5) numcd5++; //5.	Số phức.
            if (questions.get(i).getChuyende() == 6) numcd6++; //6.	Bài toán ứng dụng thật tiễn.
            if (questions.get(i).getChuyende() == 7) numcd7++; //7.	Hình học không gian.
            if (questions.get(i).getChuyende() == 8) numcd8++; //8.	Phương pháp tọa độ trong không gian.
            if (questions.get(i).getChuyende() == 9) numcd9++; //9.	Tổ hợp, xác suất.
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    protected PieData generatePieData() {

        ArrayList<PieEntry> entries1 = new ArrayList<PieEntry>();
        if(numFalse != 0 && numNoAns != 0 && numTrue !=0)
        {
            entries1.add(new PieEntry((int) (numTrue), "Đúng"));
            entries1.add(new PieEntry((int) (numFalse), "Sai"));
            entries1.add(new PieEntry((int) (numNoAns), "Chưa trả lời"));
        }
        //Không trả lời câu nào
        else if (numFalse == 0 && numNoAns != 0 &&  numTrue == 0)
        {
            entries1.add(new PieEntry((int) (numNoAns), "Chưa trả lời"));
        }
        //Trả lời toàn sai
        else if (numFalse != 0 && numNoAns != 0 &&  numTrue == 0)
        {
            entries1.add(new PieEntry((int) (numNoAns), "Chưa trả lời"));
            entries1.add(new PieEntry((int) (numFalse), "Sai"));
        }
        //Trả lời toàn đúng
        else if (numFalse == 0 && numNoAns != 0 &&  numTrue != 0)
        {
            entries1.add(new PieEntry((int) (numNoAns), "Chưa trả lời"));
            entries1.add(new PieEntry((int) (numTrue), "Đúng"));
        }
        else if (numFalse != 0 && numNoAns == 0 &&  numTrue != 0)
        {
            entries1.add(new PieEntry((int) (numFalse), "Sai"));
            entries1.add(new PieEntry((int) (numTrue), "Đúng"));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Loại đáp án");
        ds1.setColors(ColorTemplate.MATERIAL_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(10f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Tổng số câu : " + Math.round(numNoAns+numFalse+numTrue) + "\n" + "Tổng điểm : " +TongDiem + "\n" + "Xếp loại : " + XepLoai);
        s.setSpan(new RelativeSizeSpan(2f), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
        return s;
    }

    public void begin()
       {
           mChart = (PieChart) findViewById(R.id.chart1);
           btnAgain=(Button)findViewById(R.id.btnAgain);
           tv_cd1 = findViewById(R.id.tv_cd1);
           tv_cd2 = findViewById(R.id.tv_cd2);
           tv_cd3 = findViewById(R.id.tv_cd3);
           tv_cd4 = findViewById(R.id.tv_cd4);
           tv_cd5 = findViewById(R.id.tv_cd5);
           tv_cd6 = findViewById(R.id.tv_cd6);
           tv_cd7 = findViewById(R.id.tv_cd7);
           tv_cd8 = findViewById(R.id.tv_cd8);
           btnBack=(Button)findViewById(R.id.btnBack);
        }

    //PT Check kết quả
    public void checkResult(ArrayList<Question> questions){
        for(int i=0; i< questions.size(); i++)
        {
            if(questions.get(i).getDapAnChon().equals(""))
            {
                numNoAns++;
                if(questions.get(i).getChuyende() == 1) numNoAns_1++;
                if(questions.get(i).getChuyende() == 2) numNoAns_2++;
                if(questions.get(i).getChuyende() == 3) numNoAns_3++;
                if(questions.get(i).getChuyende() == 4) numNoAns_4++;
                if(questions.get(i).getChuyende() == 5) numNoAns_5++;
                if(questions.get(i).getChuyende() == 6) numNoAns_6++;
                if(questions.get(i).getChuyende() == 7) numNoAns_7++;
                if(questions.get(i).getChuyende() == 8) numNoAns_8++;
                if(questions.get(i).getChuyende() == 9) numNoAns_9++;
            }
            else if(questions.get(i).getResult().equals(questions.get(i).getDapAnChon())){
                numTrue++;
            }
            else
            {
                numFalse++;
                if(questions.get(i).getChuyende() == 1) numFalse_1++;
                if(questions.get(i).getChuyende() == 2) numFalse_2++;
                if(questions.get(i).getChuyende() == 3) numFalse_3++;
                if(questions.get(i).getChuyende() == 4) numFalse_4++;
                if(questions.get(i).getChuyende() == 5) numFalse_5++;
                if(questions.get(i).getChuyende() == 6) numFalse_6++;
                if(questions.get(i).getChuyende() == 7) numFalse_7++;
                if(questions.get(i).getChuyende() == 8) numFalse_8++;
                if(questions.get(i).getChuyende() == 9) numFalse_9++;
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

    public void clickGoiy()
    {
        tv_cd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //hamso
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd1");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ HÀM SỐ");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        tv_cd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd5");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ BÀI TOÁN THỰC TẾ");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        tv_cd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd2");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ MŨ-LÔGARIT");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        tv_cd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd3");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ NGUYÊN HÀM-TÍCH PHÂN");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        tv_cd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd6");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ SỐ PHỨC");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        tv_cd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd7");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ XÁC SUẤT");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        tv_cd7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd4");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ HÌNH HỌC KHÔNG GIAN");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        tv_cd8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestDoneActivity.this, TailieuItemActivity.class);
                intent.putExtra("cd", "cd8");
                intent.putExtra("chuyende", "CHUYÊN ĐỀ TỌA ĐỘ KHÔNG GIAN");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
    }

    //Gợi ý ôn tập
    public void checkGoiy()
    {
        //Số lượng câu sai nhỏ hơn 1/2 tổng số câu thuộc chuyên đề đó
        if((numFalse_1 +numFalse_2 + numNoAns_1 + numNoAns_2) <= ((numcd1 + numcd2)/2)) tv_cd1.setVisibility(View.GONE); //hàm số
        if((numFalse_3 +numNoAns_3) <= (numcd3/2)) tv_cd3.setVisibility(View.GONE); //logarit
        if((numFalse_4 +numNoAns_4) <= (numcd4/2)) tv_cd4.setVisibility(View.GONE); //nguyên hàm
        if((numFalse_5 +numNoAns_5) <= (numcd5/2)) tv_cd5.setVisibility(View.GONE); //số phức
        if((numFalse_6 +numNoAns_6) <= (numcd6/2)) tv_cd2.setVisibility(View.GONE); //thực tế
        if((numFalse_7 +numNoAns_7) <= (numcd7/2)) tv_cd7.setVisibility(View.GONE); //hình không gian
        if((numFalse_8 +numNoAns_8) <= (numcd8/2)) tv_cd8.setVisibility(View.GONE); //Oxyz
        if((numFalse_9 +numNoAns_9) <= (numcd9/2)) tv_cd6.setVisibility(View.GONE); //xác suất
    }
}
