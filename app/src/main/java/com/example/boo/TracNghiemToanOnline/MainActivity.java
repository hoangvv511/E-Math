package com.example.boo.TracNghiemToanOnline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.boo.TracNghiemToanOnline.Toan.BoSuuTap_Fragment;
import com.example.boo.TracNghiemToanOnline.Toan.Profile;
import com.example.boo.TracNghiemToanOnline.Toan.DeThiFragment;
import com.example.boo.TracNghiemToanOnline.Toan.TaoDeFragment;
import com.example.boo.TracNghiemToanOnline.question.DBHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    private UserInformation userInformation;
    String email, fullname;
    public static String username,imageavatar;
    String phone;
    de.hdodenhof.circleimageview.CircleImageView profile_userimage;
    TextView tv_username;
    TextView tv_useremail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View header = navigationView.getHeaderView(0);
        profile_userimage = (de.hdodenhof.circleimageview.CircleImageView) header.findViewById(R.id.profile_image);
        tv_username = (TextView) header.findViewById(R.id.tv_username);
        tv_useremail = (TextView) header.findViewById(R.id.tv_useremail);

        databaseRefence.child("Users").child(user.getUid()).child("Information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInformation = dataSnapshot.getValue(UserInformation.class);
                email = userInformation.email.toString().trim();
                fullname = userInformation.fullname.toString().trim();
                phone = userInformation.phone.toString().trim();
                username = userInformation.username.toString().trim();
                imageavatar = userInformation.imageAvatar.toString().trim();

                tv_useremail.setText(email);
                tv_username.setText(username);
                byte[] decodeString2 = Base64.decode(imageavatar, Base64.DEFAULT);
                Bitmap decoded2 = BitmapFactory.decodeByteArray(decodeString2, 0, decodeString2.length);
                Bitmap bMapScaled2 = Bitmap.createScaledBitmap(decoded2, 100, 100, true);
                profile_userimage.setImageBitmap(bMapScaled2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DeThiFragment homeFragment = new DeThiFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();

        DBHelper db = new DBHelper(this);

        //v1.0

        try {
            db.createDataBase();
//            Toast.makeText(this, "Coppy thành công", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile)
        {
            // Handle the camera action
            Profile profile = new Profile();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, profile, profile.getTag()).commit();
        }
        else if (id == R.id.nav_dethi)
        {
            DeThiFragment deThi_fragment = new DeThiFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, deThi_fragment, deThi_fragment.getTag()).commit();

        }
        else if (id == R.id.nav_chuyende)
        {
            TaoDeFragment taoDeFragment = new TaoDeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, taoDeFragment, taoDeFragment.getTag()).commit();
        }
        else if (id == R.id.nav_bosuutap)
        {
            BoSuuTap_Fragment boSuuTap_fragment = new BoSuuTap_Fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, boSuuTap_fragment, boSuuTap_fragment.getTag()).commit();
        }
        else if (id == R.id.nav_box)
        {

        }
        else if (id == R.id.nav_share)
        {

        }
        else if(id == R.id.nav_danhgia)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
