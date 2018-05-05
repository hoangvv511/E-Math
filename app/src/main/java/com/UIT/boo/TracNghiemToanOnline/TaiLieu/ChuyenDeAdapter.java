package com.UIT.boo.TracNghiemToanOnline.TaiLieu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lego.TracNghiemToanOnline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChuyenDeAdapter extends RecyclerView.Adapter<ChuyenDeAdapter.MyViewHolder>{
    private Context mContext;
    private List<ItemChuyenDe> itemChuyenDeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public ImageView imv_title;
        public Button btn_title;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            imv_title = (ImageView) view.findViewById(R.id.imv_title);
            btn_title = view.findViewById(R.id.btn_title);
        }
    }

    public ChuyenDeAdapter(Context mContext, List<ItemChuyenDe> itemChuyenDeList) {
        this.mContext = mContext;
        this.itemChuyenDeList = itemChuyenDeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_chuyen_de, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ItemChuyenDe itemChuyenDe = itemChuyenDeList.get(position);
        holder.tv_title.setText(itemChuyenDe.getName());
        Picasso.with(mContext).load(itemChuyenDe.getHinhanh()).into(holder.imv_title);

        holder.btn_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(mContext, TaiLieuWebView.class);
                intent.putExtra("link", itemChuyenDe.getUrl());
                intent.putExtra("ten", itemChuyenDe.getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemChuyenDeList.size();
    }
}
