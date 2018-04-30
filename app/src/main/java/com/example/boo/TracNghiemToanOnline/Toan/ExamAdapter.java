package com.example.boo.TracNghiemToanOnline.Toan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.boo.TracNghiemToanOnline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ExamAdapter extends ArrayAdapter<Exam> {
    public ExamAdapter(Context context, ArrayList<Exam> exam) {
        super(context, 0, exam);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.item_gridview, parent, false);
        }
        TextView tvName= (TextView) convertView.findViewById(R.id.tvNumExam);
        TextView tvNameUser = (TextView) convertView.findViewById(R.id.tv_username);
        CircleImageView imvAvatarUser = (CircleImageView) convertView.findViewById(R.id.imv_avataruser);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        TextView tvnumber = convertView.findViewById(R.id.tv_numberQuestion);
        Exam p= getItem(position);
        if(p!=null){
            tvName.setText(p.getName());
            tvTime.setText(p.getTime());
            tvNameUser.setText(p.getNameuser());
            tvnumber.setText(p.getNumberquestion() +"");
            if(p.getAvataruser().length() > 100)
            {
                byte[] decodeString2 = Base64.decode(p.getAvataruser(), Base64.DEFAULT);
                Bitmap decoded2 = BitmapFactory.decodeByteArray(decodeString2, 0, decodeString2.length);
                Bitmap bMapScaled2 = Bitmap.createScaledBitmap(decoded2, decoded2.getWidth(), decoded2.getHeight(), true);
                imvAvatarUser.setImageBitmap(bMapScaled2);
            }
            else
            {
                Picasso.get().load(p.getAvataruser()).into(imvAvatarUser);
            }
        }
        return convertView;
    }
}
