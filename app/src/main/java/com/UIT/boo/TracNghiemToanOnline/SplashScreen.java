package com.UIT.boo.TracNghiemToanOnline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar progressBar;
    private Handler handler;
    public static DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    public static int count1 = -1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.spin_kit);
        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isConnected(SplashScreen.this))
                {
                    GetInformation();
//                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
//                    finish();
                }
                else
                {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SplashScreen.this, SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Kiểm tra kết nối mạng của bạn !");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            finish();
                        }
                    });
                    sweetAlertDialog.show();
                }
            }
        },1000);
        
        //GetInformation();
    }

    public static boolean isConnected(Activity _context) {
        if (_context != null) {
            ConnectivityManager connMgr = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
            if (activeInfo != null && activeInfo.isConnected()) {
                boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
                boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
                if (wifiConnected || mobileConnected) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
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
                    if (count1 != -1) {
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        finish();
                    }
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
                //Log.w(TAG, "postComments:onCancelled", databaseError.toException());
            }
        });
    }
}
