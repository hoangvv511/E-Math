package com.UIT.boo.TracNghiemToanOnline;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.Lego.TracNghiemToanOnline.R;

import com.UIT.boo.TracNghiemToanOnline.TaiLieu.TaiLieuFragment;
import com.UIT.boo.TracNghiemToanOnline.Toan.BoSuuTap_Fragment;
import com.UIT.boo.TracNghiemToanOnline.Toan.Profile;
import com.UIT.boo.TracNghiemToanOnline.Toan.DeThiFragment;
import com.UIT.boo.TracNghiemToanOnline.question.DBHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    //private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    private UserInformation userInformation;
    public static String username,imageavatar,phone,email,fullname, user_id;
    TextView tv_username;
    TextView tv_useremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Handler handler = new Handler();
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Đang tải...");
        pDialog.setCancelable(false);
        pDialog.show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismissWithAnimation();
            }
        }, 3000);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(navListener);
        navigationView.setItemIconTintList(null);
        //navigationView.getMenu().removeItem(R.id.nav_bosuutap);

        user_id = user.getUid();
        SplashScreen.databaseRefence.child("Users").child(user_id).child("Information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                if(dataSnapshot != null)
                {
                    userInformation = dataSnapshot.getValue(UserInformation.class);
                    email = userInformation.email.toString().trim();
                    fullname = userInformation.fullname.toString().trim();
                    phone = userInformation.phone.toString().trim();
                    username = userInformation.username.toString().trim();
                    imageavatar = userInformation.imageAvatar.toString().trim();

                }
                else
                {
                    final SweetAlertDialog dialog = new SweetAlertDialog(getApplicationContext());
                    dialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Không có dữ liệu !!!");
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            final Handler handler1;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismissWithAnimation();
                                }
                            }, 1500);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DeThiFragment()).commit();

        DBHelper db = new DBHelper(this);

        //v1.0

        try {
            db.createDataBase();
//            Toast.makeText(this, "Coppy thành công", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Ấn nút Back lần nữa để thoát", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId())
            {
                case R.id.nav_dethi:
                    selectedFragment = new DeThiFragment();
                    break;
                case R.id.nav_bosuutap:
                    selectedFragment = new BoSuuTap_Fragment();
                    break;
                case R.id.nav_tailieu:
                    selectedFragment = new TaiLieuFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new Profile();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}
