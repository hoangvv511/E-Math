package com.example.boo.TracNghiemToanOnline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class following extends AppCompatActivity {

    private TextView textViewUsername;
    private ImageButton imageButtonFollow;
    private TextView textViewCount1;

    private TextView textViewUsername2;
    private ImageButton imageButtonFollow2;
    private TextView textViewCount2;

    private boolean image1 = false;
    private boolean image2 = false;

    private Button btnTrangCaNhan;
    private Button btnBoSuuTap;

    private String[] name = new String[10000];
    private int n = 0;

    private int count1 = 0;
    private int count2 = 0;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);

        textViewUsername = (TextView)findViewById(R.id.textViewUsername);
        imageButtonFollow = (ImageButton)findViewById(R.id.imageButtonFollow);
        textViewCount1 = (TextView)findViewById(R.id.textCount1);

        textViewUsername2 = (TextView)findViewById(R.id.textViewUsername2);
        imageButtonFollow2 = (ImageButton)findViewById(R.id.imageButtonFollow2);
        textViewCount2 = (TextView)findViewById(R.id.textCount2);

        databaseRefence.child("Đề thi").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String a = String.valueOf(dataSnapshot.getKey());
                if (!a.equals(ProfileActivity.username)) {
                    if (n == 0) {
                        textViewUsername.setText(a);
                        name[0] = a;
                        databaseRefence.child("Đề thi").child(a).child("Followed").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                count1++;
                                textViewCount1.setText(String.valueOf(count1) + "");
                            }
                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                count1--;
                                textViewCount1.setText(String.valueOf(count1) + "");
                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    if (n == 1) {
                        textViewUsername2.setText(a);
                        name[1] = a;
                        databaseRefence.child("Đề thi").child(name[1]).child("Followed").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                count2++;
                                textViewCount2.setText(String.valueOf(count2) + "");
                            }
                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                count2--;
                                textViewCount2.setText(String.valueOf(count2) + "");
                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    n++;
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


        databaseRefence.child("Users").child(user.getUid()).child("Following").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String a = String.valueOf(dataSnapshot.getKey());
                if(a.equals(name[0]))
                {
                    image1 = true;
                    imageButtonFollow.setImageResource(R.drawable.follow);
                }
                if(a.equals(name[1])) {
                    image2 = true;
                    imageButtonFollow2.setImageResource(R.drawable.follow);

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


        imageButtonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1)
                {
                    image1 = false;
                    imageButtonFollow.setImageResource(R.drawable.notfollow);
                    databaseRefence.child("Users").child(user.getUid()).child("Following").child(name[0]).removeValue();
                    databaseRefence.child("Đề thi").child(name[0]).child("Followed").child(ProfileActivity.username).removeValue();
                }
                else
                {
                    image1 = true;
                    imageButtonFollow.setImageResource(R.drawable.follow);
                    databaseRefence.child("Users").child(user.getUid()).child("Following").child(name[0]).setValue("true");
                    databaseRefence.child("Đề thi").child(name[0]).child("Followed").child(ProfileActivity.username).setValue("true");
                }
            }
        });

        imageButtonFollow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image2)
                {
                    image2 = false;
                    imageButtonFollow2.setImageResource(R.drawable.notfollow);
                    databaseRefence.child("Users").child(user.getUid()).child("Following").child(name[1]).removeValue();
                    databaseRefence.child("Đề thi").child(name[1]).child("Followed").child(ProfileActivity.username).removeValue();
                }
                else
                {
                    image2 = true;
                    imageButtonFollow2.setImageResource(R.drawable.follow);
                    databaseRefence.child("Users").child(user.getUid()).child("Following").child(name[1]).setValue("true");
                    databaseRefence.child("Đề thi").child(name[1]).child("Followed").child(ProfileActivity.username).setValue("true");
                }
            }
        });
    }
}