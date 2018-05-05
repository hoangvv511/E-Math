package com.UIT.boo.TracNghiemToanOnline.Toan;


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

import com.UIT.boo.TracNghiemToanOnline.LoginActivity;
import com.UIT.boo.TracNghiemToanOnline.MainActivity;
import com.Lego.TracNghiemToanOnline.R;
import com.UIT.boo.TracNghiemToanOnline.UserInformation;
import com.UIT.boo.TracNghiemToanOnline.UserSetting;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    private UserInformation userInformation;
    String  image;
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

        View view = inflater.inflate(R.layout.profile, container, false);

        imv_logout = view.findViewById(R.id.imv_userlogout);
        imv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Bạn muốn đăng xuất ?")
                        .setCancelText("No")
                        .setConfirmText("Yes")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                firebaseAuth.signOut();
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }
                        })
                        .show();
            }
        });

        imv_usersetting = view.findViewById(R.id.imv_usersetting);
        imv_usersetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSetting userSetting = new UserSetting();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                userSetting.show(manager, "Thông tin");
            }
        });

        cimv_avatar = view.findViewById(R.id.cimv_useravatar);
        tv_email = view.findViewById(R.id.tv_email);
        tv_fullname = view.findViewById(R.id.tv_name);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_nickname = view.findViewById(R.id.tv_nickname);

        tv_fullname.setText(MainActivity.fullname);
        tv_email.setText(MainActivity.email);
        tv_phone.setText(MainActivity.phone);
        tv_nickname.setText(MainActivity.username);
        image = MainActivity.imageavatar;
        byte[] decodeString2 = Base64.decode(image, Base64.DEFAULT);
        Bitmap decoded2 = BitmapFactory.decodeByteArray(decodeString2, 0, decodeString2.length);
        Bitmap bitmapUser = Bitmap.createScaledBitmap(decoded2, decoded2.getWidth(), decoded2.getHeight(), true);
        cimv_avatar.setImageBitmap(bitmapUser);
        return view;
    }

}
