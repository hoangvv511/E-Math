package com.UIT.boo.TracNghiemToanOnline.Toan;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
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
import com.UIT.boo.TracNghiemToanOnline.UserSetting;

import com.baoyz.widget.PullRefreshLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import com.UIT.boo.TracNghiemToanOnline.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */

public class Profile extends Fragment {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    public String image;
    public static CircleImageView cimv_avatar;
    private PullRefreshLayout pullRefreshLayout;
    TextView tv_fullname, tv_email, tv_phone, tv_nickname;
    ImageView imv_logout, imv_usersetting;
    TextView tv_numExam;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.profile, container, false);
        imv_logout = view.findViewById(R.id.imv_userlogout);
        imv_usersetting = view.findViewById(R.id.imv_usersetting);
        cimv_avatar = view.findViewById(R.id.cimv_useravatar);
        tv_email = view.findViewById(R.id.tv_email);
        tv_fullname = view.findViewById(R.id.tv_name);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        tv_numExam = view.findViewById(R.id.tv_numExam);

        pullRefreshLayout = view.findViewById(R.id.pullRefreshLayout);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                Refresh();
            }
        });

        imv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE);
                sweetAlertDialog.setTitleText("Bạn muốn đăng xuất ?");
                sweetAlertDialog.setCancelText("No");
                sweetAlertDialog.setConfirmText("Yes");
                sweetAlertDialog.showCancelButton(true);
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        });
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                firebaseAuth.signOut();
                                getActivity().finish();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                sweetAlertDialog.show();
            }
        });

        imv_usersetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSetting userSetting = new UserSetting();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                userSetting.show(manager, "Thông tin");
            }
        });

        GetInfor();
        return view;
    }

    public void GetInfor()
    {
        tv_fullname.setText(MainActivity.fullname);
        tv_email.setText(MainActivity.email);
        tv_phone.setText(MainActivity.phone);
        tv_nickname.setText(MainActivity.username);
        image = MainActivity.imageavatar;
        tv_numExam.setText(String.valueOf(MainActivity.numExam));
        byte[] decodeString2 = Base64.decode(image, Base64.DEFAULT);
        Bitmap decoded2 = BitmapFactory.decodeByteArray(decodeString2, 0, decodeString2.length);
        Bitmap bitmapUser = Bitmap.createScaledBitmap(decoded2, decoded2.getWidth(), decoded2.getHeight(), true);
        cimv_avatar.setImageBitmap(bitmapUser);
    }

    public void Refresh()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullRefreshLayout.setRefreshing(false);
            }
        }, 1500);
        try
        {
            GetInfor();
        }
        catch (Exception e)
        {

        }
    }
}
