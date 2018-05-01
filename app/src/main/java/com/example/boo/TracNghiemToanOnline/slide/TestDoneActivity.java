package com.example.boo.TracNghiemToanOnline.slide;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boo.TracNghiemToanOnline.R;
import com.example.boo.TracNghiemToanOnline.question.Question;
import com.example.boo.TracNghiemToanOnline.score.ScoreController;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

    private PieChart mChart;
    private Typeface tf;
    ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
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
    int totalScore =0;
    int numcd1 = 0, numcd2 =0 , numcd3 =0 , numcd4 =0, numcd5 =0, numcd6=0, numcd7=0, numcd8=0, numcd9=0;
    ScoreController scoreController;

    TextView tvTrue, tvFalse, tvNotAns, tvTotalScore;
    Button btnSaveScore, btnAgain, btnExit;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        tf = Typeface.createFromAsset(this.getAssets(), "OpenSans-Regular.ttf");
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);

        scoreController = new ScoreController(TestDoneActivity.this);
        Intent intent = getIntent();
        arr_QuesBegin = (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");

        for (int i = 0; i < arr_QuesBegin.size(); i++) {
            if (arr_QuesBegin.get(i).getChuyende() == 1) numcd1++;
            if (arr_QuesBegin.get(i).getChuyende() == 2) numcd2++;
            if (arr_QuesBegin.get(i).getChuyende() == 3) numcd3++;
            if (arr_QuesBegin.get(i).getChuyende() == 4) numcd4++;
            if (arr_QuesBegin.get(i).getChuyende() == 5) numcd5++;
            if (arr_QuesBegin.get(i).getChuyende() == 6) numcd6++;
            if (arr_QuesBegin.get(i).getChuyende() == 7) numcd7++;
            if (arr_QuesBegin.get(i).getChuyende() == 8) numcd8++;
            if (arr_QuesBegin.get(i).getChuyende() == 9) numcd9++;
        }

        begin();
        checkResult();
        totalScore = numTrue * 10;

        mChart.getDescription().setEnabled(false);
        Typeface tf = Typeface.createFromAsset(this.getAssets(), "OpenSans-Light.ttf");
        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(8f);
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

//        btnExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
//                builder.setIcon(R.drawable.exit);
//                builder.setTitle("Thông báo");
//                builder.setMessage("Bạn có muốn thoát hay không?");
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                builder.show();
//            }
//        });

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
        SpannableString s = new SpannableString("Tổng số câu : 50 \n Tổng điểm : 10.0 \n Xếp loại : Trung bình");
        s.setSpan(new RelativeSizeSpan(2f), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
        return s;
    }

    public void begin()
       {
           mChart = (PieChart) findViewById(R.id.chart1);
//        tvFalse= (TextView)findViewById(R.id.tvFalse);
//        tvTrue= (TextView)findViewById(R.id.tvTrue);
//        tvNotAns= (TextView)findViewById(R.id.tvNotAns);
//        tvTotalScore= (TextView)findViewById(R.id.tvTotalPoint);
//        btnAgain=(Button)findViewById(R.id.btnAgain);
//        btnSaveScore=(Button)findViewById(R.id.btnSaveScore);
//        btnExit=(Button)findViewById(R.id.btnExit);
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
                if(arr_QuesBegin.get(i).getChuyende() == 1) numFalse_1++;
                if(arr_QuesBegin.get(i).getChuyende() == 2) numFalse_2++;
                if(arr_QuesBegin.get(i).getChuyende() == 3) numFalse_3++;
                if(arr_QuesBegin.get(i).getChuyende() == 4) numFalse_4++;
                if(arr_QuesBegin.get(i).getChuyende() == 5) numFalse_5++;
                if(arr_QuesBegin.get(i).getChuyende() == 6) numFalse_6++;
                if(arr_QuesBegin.get(i).getChuyende() == 7) numFalse_7++;
                if(arr_QuesBegin.get(i).getChuyende() == 8) numFalse_8++;
                if(arr_QuesBegin.get(i).getChuyende() == 9) numFalse_9++;
            }
        }
    }
}
