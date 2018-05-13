package com.UIT.boo.TracNghiemToanOnline.TaiLieu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.Lego.TracNghiemToanOnline.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaiLieuFragment extends Fragment {

    GridLayout tailieuGrid;
    public TaiLieuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tai_lieu, container, false);
        tailieuGrid = view.findViewById(R.id.mainGrid);

        setSingleEvent(tailieuGrid);
        //setToggleEvent(tailieuGrid);
        return view;
    }

    private void setSingleEvent(GridLayout mainGrid)
    {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int id = cardView.getId();
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(id == R.id.cv_cd1) //Ham so
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd1");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ HÀM SỐ");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd2) //Logarit
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd2");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ MŨ-LÔGARIT");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd3) //nguyen ham
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd3");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ NGUYÊN HÀM-TÍCH PHÂN");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd4) //hinh hoc khong gian
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd4");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ HÌNH HỌC KHÔNG GIAN");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd5) //thuc te
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd5");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ BÀI TOÁN THỰC TẾ");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd6) //so phuc
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd6");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ SỐ PHỨC");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd7) //xac suat
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd7");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ XÁC SUẤT");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd8) //toa do
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd8");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ TỌA ĐỘ KHÔNG GIAN");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd9) //casio
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd9");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ CASIO");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                    else if(id == R.id.cv_cd10) //csc
                    {
                        Intent intent = new Intent(getActivity(), TailieuItemActivity.class);
                        intent.putExtra("cd", "cd10");
                        intent.putExtra("chuyende", "CHUYÊN ĐỀ DÃY SỐ - CSC - CSN");
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                }
            });
        }
    }

    private void setToggleEvent (GridLayout mainGrid)
    {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
//                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
//                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
//                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
//                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
