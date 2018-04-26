package com.example.boo.TracNghiemToanOnline.Toan;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boo.TracNghiemToanOnline.LoginActivity;
import com.example.boo.TracNghiemToanOnline.MainActivity;
import com.example.boo.TracNghiemToanOnline.R;
import com.example.boo.TracNghiemToanOnline.UserInformation;
import com.example.boo.TracNghiemToanOnline.UserSetting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    private UserInformation userInformation;
    String email, fullname, image, username;
    String phone;
    CircleImageView cimv_avatar;
    TextView tv_fullname, tv_email, tv_phone, tv_nickname;
    ImageView imv_logout, imv_usersetting;
    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Thông tin cá nhân");
        View view = inflater.inflate(R.layout.profile, container, false);

        imv_logout = view.findViewById(R.id.imv_userlogout);
        imv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        imv_usersetting = view.findViewById(R.id.imv_usersetting);
        imv_usersetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSetting userSetting = new UserSetting();
                Bundle bundle = new Bundle();
                bundle.putString("emailuser", email);
                userSetting.setArguments(bundle);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                userSetting.show(manager, "Thông tin");
            }
        });

        cimv_avatar = view.findViewById(R.id.cimv_useravatar);
        tv_email = view.findViewById(R.id.tv_email);
        tv_fullname = view.findViewById(R.id.tv_name);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_nickname = view.findViewById(R.id.tv_nickname);


        databaseRefence.child("Users").child(user.getUid()).child("Information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInformation = dataSnapshot.getValue(UserInformation.class);
                email = userInformation.email.toString().trim();
                fullname = userInformation.fullname.toString().trim();
                phone = userInformation.phone.toString().trim() ;
                username = userInformation.username.toString().trim();

                tv_fullname.setText(fullname);
                tv_email.setText(email);
                tv_phone.setText(phone);
                tv_nickname.setText(username);
                image = userInformation.imageAvatar.toString().trim();

                byte[] decodeString2 = Base64.decode(image, Base64.DEFAULT);
                Bitmap decoded2 = BitmapFactory.decodeByteArray(decodeString2, 0, decodeString2.length);
                Bitmap bMapScaled2 = Bitmap.createScaledBitmap(decoded2, 100, 100, true);
                cimv_avatar.setImageBitmap(bMapScaled2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

}
