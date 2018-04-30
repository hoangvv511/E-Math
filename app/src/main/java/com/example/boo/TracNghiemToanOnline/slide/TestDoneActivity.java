package com.example.boo.TracNghiemToanOnline.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boo.TracNghiemToanOnline.R;
import com.example.boo.TracNghiemToanOnline.question.Question;
import com.example.boo.TracNghiemToanOnline.score.ScoreController;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);
        scoreController=new ScoreController(TestDoneActivity.this);
        Intent intent= getIntent();
        arr_QuesBegin= (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");
        for(int i = 0; i <arr_QuesBegin.size() ; i++)
        {
            if(arr_QuesBegin.get(i).getChuyende() == 1) numcd1++;
            if(arr_QuesBegin.get(i).getChuyende() == 2) numcd2++;
            if(arr_QuesBegin.get(i).getChuyende() == 3) numcd3++;
            if(arr_QuesBegin.get(i).getChuyende() == 4) numcd4++;
            if(arr_QuesBegin.get(i).getChuyende() == 5) numcd5++;
            if(arr_QuesBegin.get(i).getChuyende() == 6) numcd6++;
            if(arr_QuesBegin.get(i).getChuyende() == 7) numcd7++;
            if(arr_QuesBegin.get(i).getChuyende() == 8) numcd8++;
            if(arr_QuesBegin.get(i).getChuyende() == 9) numcd9++;
        }
        begin();
        checkResult();
        totalScore= numTrue*10;
        tvNotAns.setText(""+numNoAns);
        tvFalse.setText(""+numFalse);
        tvTrue.setText(""+numTrue);
        tvTotalScore.setText(""+totalScore);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
            }
        });

        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(TestDoneActivity.this);
                LayoutInflater inflater=TestDoneActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.allert_dialog_save_score,null);
                builder.setView(view);

                final EditText edtName= (EditText) view.findViewById(R.id.edtName);
                final EditText edtRoom= (EditText) view.findViewById(R.id.edtRoom);
                TextView tvScore= (TextView) view.findViewById(R.id.tvScore);
                final int numTotal= numTrue*10;
                tvScore.setText(numTotal+" điểm");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name= edtName.getText().toString();
                        String room= edtRoom.getText().toString();
                        scoreController.insertScore(name,numTotal,room);
                        Toast.makeText(TestDoneActivity.this, "Lưu điểm thành công!",Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b= builder.create();
                b.show();
            }
        });

    }

    public void begin(){
        tvFalse= (TextView)findViewById(R.id.tvFalse);
        tvTrue= (TextView)findViewById(R.id.tvTrue);
        tvNotAns= (TextView)findViewById(R.id.tvNotAns);
        tvTotalScore= (TextView)findViewById(R.id.tvTotalPoint);
        btnAgain=(Button)findViewById(R.id.btnAgain);
        btnSaveScore=(Button)findViewById(R.id.btnSaveScore);
        btnExit=(Button)findViewById(R.id.btnExit);
    }

    //PT Check kết quả
    public void checkResult(){
        for(int i=0; i< arr_QuesBegin.size(); i++){
            if(arr_QuesBegin.get(i).getDapAnChon().equals("")){
                numNoAns++;
            }else if(arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getDapAnChon())){
                numTrue++;
            }else
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
