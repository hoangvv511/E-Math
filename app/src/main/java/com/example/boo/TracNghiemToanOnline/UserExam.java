package com.example.boo.TracNghiemToanOnline;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserExam {
    public Integer cau[];
    public Integer n;
    public String tendethi;
    public String thoigian;
    public String socauhoi;
    public Map<String, Integer> cauhoi;

    public UserExam()
    {

    }

    public UserExam(Integer a[], Integer n)
    {
        this.cau = a;
        this.n = n;
    }

    public String getTendethi() {
        return tendethi;
    }

    public void setTendethi(String tendethi) {
        this.tendethi = tendethi;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getSocauhoi() {
        return socauhoi;
    }

    public void setSocauhoi(String socauhoi) {
        this.socauhoi = socauhoi;
    }

    public Map<String, Integer> getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(HashMap<String, Integer> cauhoi) {
        this.cauhoi = cauhoi;
    }
}