package com.example.boo.TracNghiemToanOnline.TaiLieu;


public class ItemChuyenDe {

    private String name;
    private String url;
    int hinhanh;

    public ItemChuyenDe(String name, String url, int hinhanh) {
        this.name = name;
        this.url = url;
        this.hinhanh = hinhanh;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public ItemChuyenDe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
