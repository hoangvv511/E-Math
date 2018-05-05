package com.UIT.boo.TracNghiemToanOnline;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.Lego.TracNghiemToanOnline.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {

    public static DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    public static int count1 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3000);

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
//        do {
//            if (count1 != -1) {
//                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
//                startActivity(i);
//            }
//        }while (count1 == -1);
    }
}
