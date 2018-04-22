package com.example.boo.TracNghiemToanOnline.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class QuestionController {
    private DBHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper= new DBHelper(context);
    }

    //Lấy danh sách câu hỏi
    public ArrayList<Question> getQuestion(int num_exam){
        ArrayList<Question> lsData= new ArrayList<Question>();
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM tracnghiem WHERE num_exam = '"+num_exam+"'",null);
        cursor.moveToFirst();
        do {
            Question item;
            item= new Question(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),"");
            lsData.add(item);
        }while (cursor.moveToNext());
        return lsData;
    }

    //Lay danh sach cau hoi theo chuyen de
    public ArrayList<Question> getQuestionByChuyenDe()
    {
        ArrayList<Question> lsData= new ArrayList<Question>();
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 1\n" +
                "\t  order by random() limit 10)\n" +
                "union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 2\n" +
                "\t  order by random() limit 5)\n" +
                "\t  union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 3\n" +
                "\t  order by random() limit 5)\n" +
                "\t  union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 4\n" +
                "\t  order by random() limit 5)\n" +
                "\t  union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 5\n" +
                "\t  order by random() limit 5)\n" +
                "\t  union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 6\n" +
                "\t  order by random() limit 5)\n" +
                "\t  union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 7\n" +
                "\t  order by random() limit 5)\n" +
                "\t  union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 8\n" +
                "\t  order by random() limit 5)\n" +
                "\t  union\n" +
                "select * \n" +
                "from (select * from tracnghiem\n" +
                "\t  where num_chuyende = 9\n" +
                "\t  order by random() limit 5)\n",null);
        cursor.moveToFirst();
        do {
            Question item;
            item= new Question(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),"");
            lsData.add(item);
        }while (cursor.moveToNext());
        return lsData;
    }



}
