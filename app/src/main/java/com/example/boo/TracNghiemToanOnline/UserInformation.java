package com.example.boo.TracNghiemToanOnline;

import android.net.Uri;

/**
 * Created by Binh on 27/03/2018.
 */

public class UserInformation {
    public String name;
    public String address;
    public String email;
    public String imageAvatar = "https://firebasestorage.googleapis.com/v0/b/myapplication-6d1ae.appspot.com/o/image.png?alt=media&token=85a1059d-2481-4cf7-a489-3820b13e7517";

    public UserInformation(){

    }

    public UserInformation(String name, String address, String email, String imageAvatar){
        this.name = name;
        this.address = address;
        this.email = email;
        this.imageAvatar = imageAvatar;
    }

}
