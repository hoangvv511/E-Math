package com.example.boo.TracNghiemToanOnline.question;

import java.io.Serializable;



public class Question implements Serializable{
    private  int _id;
    private  String question;
    private  String ans_a;
    private  String ans_b;
    private  String ans_c;
    private  String ans_d;
    private  String result;
    private  int num_exam;
    private  int chuyende;
    private  String image;
    private String huongdangiai;
    private String huongdangiai_image;
    private String DapAnChon = "";
    public int choiceID= -1; //hỗ trợ check Id của radiogroup

    public Question(int anInt) {
        this._id = anInt;
    }

    public String getDapAnChon() {
        return DapAnChon;
    }

    public void setDapAnChon(String DapAnChon) {
        this.DapAnChon = DapAnChon;
    }

    public String getHuongdangiai() {
        return huongdangiai;
    }

    public void setHuongdangiai(String huongdangiai) {
        this.huongdangiai = huongdangiai;
    }

    public String getHuongdangiai_image() {
        return huongdangiai_image;
    }

    public void getHuongdangiai_image(String huongdangiai_image) {
        this.huongdangiai_image = huongdangiai_image;
    }

    public int getChuyende() {
        return chuyende;
    }

    public void setChuyende(int chuyende) {
        this.chuyende = chuyende;
    }

    public Question(int _id, String question, String ans_a, String ans_b, String ans_c, String ans_d, String result, int num_exam, int chuyende, String image, String huongdangiai, String huongdangiai_image, String dapAnChon) {
        this._id = _id;
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
        this.result = result;
        this.num_exam = num_exam;
        this.chuyende = chuyende;
        this.image = image;
        this.huongdangiai= huongdangiai;
        this.huongdangiai_image= huongdangiai_image;
        this.DapAnChon = dapAnChon;
    }

    public Question() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String getAns_d() {
        return ans_d;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getNum_exam() {
        return num_exam;
    }

    public void setNum_exam(int num_exam) {
        this.num_exam = num_exam;
    }

    public int getSubject() {
        return chuyende;
    }

    public void setSubject(String subject) {
        this.chuyende = chuyende;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
