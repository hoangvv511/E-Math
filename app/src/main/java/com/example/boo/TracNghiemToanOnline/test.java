package com.example.boo.TracNghiemToanOnline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class test extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    private Button btnLoad;
    private Button btnDownload;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnDownload = (Button)findViewById(R.id.btnDownload);
        textView = (TextView)findViewById(R.id.textViewLoad);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer a[] = new Integer[50];
                Integer n = 50;
                Integer dem = 0;
                String tendethi = "Đề thi thứ nhất";
                Integer thoigian = 120;
                for (int i = 0; i < 50; i++) {
                    dem++;
                    a[i] = dem;
                }

                Map<String, Integer> map = new HashMap<String, Integer>();

                UserExam userExam = new UserExam(a);

                for (int i = 0; i < n; i++) {
                    String x = "Câu " + i;
                    Integer y = userExam.cau[i];
                    map.put(x, y);
                    databaseRefence.child(user.getUid()).child("Đề thi").child(tendethi).child("Câu hỏi").setValue(map);
                }
                databaseRefence.child(user.getUid()).child("Đề thi").child(tendethi).child("Tên đề thi").setValue(tendethi);
                databaseRefence.child(user.getUid()).child("Đề thi").child(tendethi).child("Thời gian").setValue(thoigian);
            }
        });
    }
}
