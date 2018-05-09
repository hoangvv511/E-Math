package com.UIT.boo.TracNghiemToanOnline;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.Lego.TracNghiemToanOnline.R;

import com.UIT.boo.TracNghiemToanOnline.Toan.BoSuuTap_Fragment;
import com.UIT.boo.TracNghiemToanOnline.question.Question;
import com.UIT.boo.TracNghiemToanOnline.question.QuestionController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaoDeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    EditText edtTenDeThi, edtThoiGian;
    CheckBox cB1, cB2, cB3, cB4, cB5, cB6, cB7, cB8, cB9;
    Button tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    TextView tv_sum;
    Button btnTaoDe;
    String tende;
    int check = 0;
    ArrayList<Question> questions;
    QuestionController questionController;
    private String tendethi,thoigian,tongsocau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_de);
        edtTenDeThi = findViewById(R.id.edtTenDe);
        edtThoiGian = findViewById(R.id.edtThoiGian);
        btnTaoDe = findViewById(R.id.btn_taode);
        cB1 = findViewById(R.id.cB_1);
        cB2 = findViewById(R.id.cB_2);
        cB3 = findViewById(R.id.cB_3);
        cB4 = findViewById(R.id.cB_4);
        cB5 = findViewById(R.id.cB_5);
        cB6 = findViewById(R.id.cB_6);
        cB7 = findViewById(R.id.cB_7);
        cB8 = findViewById(R.id.cB_8);
        cB9 = findViewById(R.id.cB_9);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv5 = findViewById(R.id.tv_5);
        tv6 = findViewById(R.id.tv_6);
        tv7 = findViewById(R.id.tv_7);
        tv8 = findViewById(R.id.tv_8);
        tv9 = findViewById(R.id.tv_9);
        tv_sum = findViewById(R.id.tv_sumquestion);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        cB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB1.isChecked()) tv1.setText("0");
            }
        });

        cB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB2.isChecked()) tv2.setText("0");
            }
        });

        cB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB3.isChecked()) tv3.setText("0");
            }
        });

        cB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB3.isChecked()) tv3.setText("0");
            }
        });

        cB4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB4.isChecked()) tv4.setText("0");
            }
        });

        cB5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB5.isChecked()) tv5.setText("0");
            }
        });

        cB6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB6.isChecked()) tv6.setText("0");
            }
        });

        cB7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB7.isChecked()) tv7.setText("0");
            }
        });

        cB8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB8.isChecked()) tv8.setText("0");
            }
        });

        cB9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!cB9.isChecked()) tv9.setText("0");
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(1,54);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(2,133);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(3,194);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(4,97);
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(5,58);
            }
        });

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(6,12);
            }
        });

        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(7,215);
            }
        });

        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(8,71);
            }
        });

        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(9,66);
            }
        });

        btnTaoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PullTest();
            }
        });


    }

    public void PullTest()
    {
        tendethi = edtTenDeThi.getText().toString().trim();
        thoigian = edtThoiGian.getText().toString().trim();
        int socau1 = Integer.valueOf(tv1.getText().toString());
        int socau2 = Integer.valueOf(tv2.getText().toString());
        int socau3 = Integer.valueOf(tv3.getText().toString());
        int socau4 = Integer.valueOf(tv4.getText().toString());
        int socau5 = Integer.valueOf(tv5.getText().toString());
        int socau6 = Integer.valueOf(tv6.getText().toString());
        int socau7 = Integer.valueOf(tv7.getText().toString());
        int socau8 = Integer.valueOf(tv8.getText().toString());
        int socau9 = Integer.valueOf(tv9.getText().toString());
        int sum = socau1 + socau2 + socau3 + socau4 + socau5 + socau6 + socau7 + socau8 + socau9;
        tongsocau = String.valueOf(sum);

        questions = new ArrayList<Question>();
        questionController = new QuestionController(this);
        questions = questionController.getQuestionById(socau1,socau2,socau3,socau4,socau5,socau6,socau7,socau8,socau9);
        Integer n = questions.size();
        Integer a[] = new Integer[n];
        for(int i = 0 ; i < questions.size(); i++)
        {
            a[i] = questions.get(i).get_id();
        }

        Map<String, Long> map = new HashMap<String, Long>();

        UserExam userExam = new UserExam(a,n);

        for (int i = 1; i <= n; i++) {
            String x = "Câu " + i;
            Long y = Long.valueOf(userExam.cau[i-1]);
            map.put(x, y);
            //databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").child(tendethi).child("Câu hỏi").setValue(map);
        }
//        databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").child(tendethi).child("Tên đề thi").setValue(tendethi);
//        databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").child(tendethi).child("Thời gian").setValue(thoigian);
//        databaseRefence.child("Đề thi").child(MainActivity.username).child("Đề").child(tendethi).child("Tổng số câu").setValue(tongsocau);

        final Handler handler = new Handler();
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Đang tạo đề...");
        pDialog.setCancelable(false);
        pDialog.show();
        UserExam userExam1 = new UserExam(tendethi,thoigian,tongsocau,map);
//        TaoDe(tendethi,"Tên đề thi", tendethi);
//        TaoDe(tendethi,"Thời gian", thoigian);
//        TaoDe(tendethi,"Tổng số câu", tongsocau);
        SplashScreen.databaseRefence.child("Đề thi").child(MainActivity.username).child(tendethi)
                .setValue(userExam1)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    pDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    pDialog.setTitleText("Tạo đề thành công!");
                                    pDialog.setConfirmText("Về kho đề");
                                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            finish();
                                            pDialog.dismissWithAnimation();
                                            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                                        }
                                    });
                                }
                            },2000);

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                    pDialog.dismissWithAnimation();
                                    overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                                }
                            }, 5000);

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("Tạo đề thất bại!");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.dismissWithAnimation();
                            }
                        }, 1500);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    public void UpdateSumQuesttion()
    {
        int socau1 = Integer.valueOf(tv1.getText().toString());
        int socau2 = Integer.valueOf(tv2.getText().toString());
        int socau3 = Integer.valueOf(tv3.getText().toString());
        int socau4 = Integer.valueOf(tv4.getText().toString());
        int socau5 = Integer.valueOf(tv5.getText().toString());
        int socau6 = Integer.valueOf(tv6.getText().toString());
        int socau7 = Integer.valueOf(tv7.getText().toString());
        int socau8 = Integer.valueOf(tv8.getText().toString());
        int socau9 = Integer.valueOf(tv9.getText().toString());
        int sum = socau1 + socau2 + socau3 + socau4 + socau5 + socau6 + socau7 + socau8 + socau9;
        String s = String.valueOf("Tổng số câu : " + sum);
        tv_sum.setText(s);
    }

    private void numberPickerDialog(final int chuyende, final int maxQuestion)
    {
        NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMaxValue(maxQuestion);
        numberPicker.setMinValue(0);
        numberPicker.setValue(0);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch(chuyende)
                {
                    case 1:
                        if(cB1.isChecked()==false) tv1.setText("0");
                        else tv1.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 2:
                        if(cB2.isChecked()==false) tv2.setText("0");
                        else tv2.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 3:
                        if(cB3.isChecked()==false) tv3.setText("0");
                        else tv3.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 4:
                        if(cB4.isChecked()==false) tv4.setText("0");
                        else tv4.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 5:
                        if(cB5.isChecked()==false) tv5.setText("0");
                        else tv5.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 6:
                        if(cB6.isChecked()==false) tv6.setText("0");
                        else tv6.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 7:
                        if(cB7.isChecked()==false) tv7.setText("0");
                        else tv7.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 8:
                        if(cB8.isChecked()==false) tv8.setText("0");
                        else tv8.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 9:
                        if(cB9.isChecked()==false) tv9.setText("0");
                        else tv9.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                }
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(numberPicker);
        builder.setTitle("Số câu hỏi").setIcon(R.drawable.question);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
