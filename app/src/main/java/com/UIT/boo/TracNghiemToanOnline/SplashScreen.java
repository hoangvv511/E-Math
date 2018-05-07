package com.UIT.boo.TracNghiemToanOnline;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Lego.TracNghiemToanOnline.R;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private Handler handler;
    public static DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    public static int count1 = -1;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.spin_kit);
        CubeGrid wave = new CubeGrid();
        progressBar.setIndeterminateDrawable(wave);
        handler = new Handler();
        handler.postDelayed(runnable, 4000);

        GetInformation();
    }

    public void GetInformation()
    {
        databaseRefence.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String a = String.valueOf(dataSnapshot.getKey());
                if(a.equals("Username"))
                {
                    count1 = (int) dataSnapshot.getChildrenCount();
                    String b = String.valueOf(count1) + "";
                    Toast.makeText(SplashScreen.this, b, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String a = String.valueOf(dataSnapshot.getKey());
                if(a.equals("Username"))
                {
                    count1 = (int) dataSnapshot.getChildrenCount();
                    String b = String.valueOf(count1) + "";
                    Toast.makeText(SplashScreen.this, b, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String a = String.valueOf(dataSnapshot.getKey());
                if(a.equals("Username"))
                {
                    count1 = (int) dataSnapshot.getChildrenCount();
                    String b = String.valueOf(count1) + "";
                    Toast.makeText(SplashScreen.this, b, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                String a = String.valueOf(dataSnapshot.getKey());
                if(a.equals("Username"))
                {
                    count1 = (int) dataSnapshot.getChildrenCount();
                    String b = String.valueOf(count1) + "";
                    Toast.makeText(SplashScreen.this, b, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            }
        });
    }
}
