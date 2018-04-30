package com.example.boo.TracNghiemToanOnline;

import android.net.Uri;

/**
 * Created by Binh on 27/03/2018.
 */

public class UserInformation {
    public String fullname;
    public String phone;
    public String email;
    public String imageAvatar;
    public String username;

    public UserInformation(){

    }

    public UserInformation(String email, String fullname, String imageAvatar, String phone, String username){
        this.email = email;
        this.fullname = fullname;
        this.imageAvatar = imageAvatar;
        this.phone = phone;
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(String imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
