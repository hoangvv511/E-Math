package com.example.boo.TracNghiemToanOnline.Toan;

public class Exam {
    private String name;
    private String time;
    private String numberQuestion;
    private String avataruser;
    private String nameuser;

    public Exam(String name, String time, String numberQuestion, String avataruser, String nameuser) {
        this.name = name;
        this.time = time;
        this.numberQuestion = numberQuestion;
        this.avataruser = avataruser;
        this.nameuser = nameuser;
    }

    public Exam() {
    }

    public String getAvataruser() {return  avataruser;}
    public void setAvataruser(String avataruser) {this.avataruser = avataruser;}

    public String getNameuser() {return  nameuser;}
    public void setNameuser(String nameuser) {this.nameuser = nameuser;}

    public String getTime() {
        return time;
    }

    public void setTime(String name) {
        this.time = time;
    }

    public String getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(String numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
