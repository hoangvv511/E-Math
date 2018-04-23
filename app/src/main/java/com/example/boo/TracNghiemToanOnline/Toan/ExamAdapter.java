package com.example.boo.TracNghiemToanOnline.Toan;

import android.content.Context;
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
        Exam p= getItem(position);
        if(p!=null){
            tvName.setText(p.getName());
            tvTime.setText(p.getTime());
            tvNameUser.setText(p.getNameuser());
            Picasso.get().load(p.getAvataruser()).into(imvAvatarUser);
        }
        return convertView;
    }
}
