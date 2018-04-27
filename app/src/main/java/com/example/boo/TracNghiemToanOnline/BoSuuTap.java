package com.example.boo.TracNghiemToanOnline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BoSuuTap extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    private Button btnDe1;
    private Button btnDe2;

    private TextView textViewLoad1;
    private TextView textViewLoad2;

    private Integer n = 0;
    private String[] name = new String[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_suu_tap);

        btnDe1 = (Button)findViewById(R.id.btnDe1);
        btnDe2 = (Button)findViewById(R.id.btnDe2);

        textViewLoad1 = (TextView)findViewById(R.id.textViewLoad1);
        textViewLoad2 = (TextView)findViewById(R.id.textViewLoad2);

        databaseRefence.child("Đề thi").child(ProfileActivity.username).child("Đề").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                n++;
                String a = String.valueOf(dataSnapshot.getKey());
                if(n==1)
                {
                    btnDe1.setText(a + "");
                    name[n] = a;
                }
                if(n==2)
                {
                    btnDe2.setText(a + "");
                    name[n] = a;
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

            }
        });
    }
}
