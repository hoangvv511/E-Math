package com.UIT.boo.TracNghiemToanOnline;

import java.util.Map;

public class UserExam {
    public Integer cau[];
    public Integer n;
    public String tendethi;
    public String thoigian;
    public String socauhoi;
    public String stt;
    public Map<String, Long> cauhoi;

    public Integer[] getCau() {
        return cau;
    }

    public UserExam(String tendethi, String thoigian, String socauhoi, Map<String, Long> cauhoi) {
        this.tendethi = tendethi;
        this.thoigian = thoigian;
        this.socauhoi = socauhoi;
        this.cauhoi = cauhoi;
    }

    public void setCau(Integer[] cau) {
        this.cau = cau;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public UserExam()
    {

    }

    public UserExam(String stt, Integer id)
    {
        this.stt = stt;
        this.n = id;
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

}