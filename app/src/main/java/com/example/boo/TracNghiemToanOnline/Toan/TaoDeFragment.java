package com.example.boo.TracNghiemToanOnline.Toan;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boo.TracNghiemToanOnline.MainActivity;
import com.example.boo.TracNghiemToanOnline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaoDeFragment extends Fragment {

    EditText edtTenDeThi, edtThoiGian;
    CheckBox cB1, cB2, cB3, cB4, cB5, cB6, cB7, cB8, cB9;
    NumberPicker nP1, nP2, nP3, nP4, nP5, nP6, nP7, nP8, nP9;
    Button btnTaoDe;
    String tende;
    TextView Tong;
    public TaoDeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Tạo đề thi");
        View view = inflater.inflate(R.layout.fragment_tao_de, container, false);
        init(view);
        SetNumPick();
        check();
        btnTaoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoSuuTap_Fragment boSuuTap_fragment = new BoSuuTap_Fragment();
                Bundle bundle = new Bundle();
                tende = String.valueOf(edtTenDeThi.getText().toString());
                bundle.putString("Name", tende);
                boSuuTap_fragment.setArguments(bundle);
                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                Toast.makeText(getContext(),cB1.getText() + " số câu "+nP1.getValue() +"\n"+cB2.getText() + " số câu "+nP2.getValue()+"\n" +cB3.getText() + " số câu "+nP3.getValue()+"\n" +cB4.getText() + " số câu "+nP4.getValue()+"\n" +cB5.getText() + " số câu "+nP5.getValue()+"\n" +cB6.getText() + " số câu "+nP6.getValue()+"\n" +cB7.getText() + " số câu "+nP7.getValue()+"\n" +cB8.getText() + " số câu "+nP8.getValue()+"\n"
                        +cB9.getText() + " số câu "+nP9.getValue(),Toast.LENGTH_LONG).show();
                int tongcau =  nP1.getValue() + nP2.getValue() + nP3.getValue()+nP4.getValue()+nP5.getValue()+nP6.getValue()+nP7.getValue()+nP8.getValue()+nP9.getValue();
                Tong.setText(""+tongcau);
                fr.replace(R.id.content_main, boSuuTap_fragment);
                fr.addToBackStack(null);
                fr.commit();

            }
        });
        return view;
    }
    public void init(View view){
        cB1=view.findViewById(R.id.cB_1);
        cB2=view.findViewById(R.id.cB_2);
        cB3=view.findViewById(R.id.cB_3);
        cB4=view.findViewById(R.id.cB_4);
        cB5=view.findViewById(R.id.cB_5);
        cB6=view.findViewById(R.id.cB_6);
        cB7=view.findViewById(R.id.cB_7);
        cB8=view.findViewById(R.id.cB_8);
        cB9=view.findViewById(R.id.cB_9);
        nP1=view.findViewById(R.id.numberPicker1);
        nP2=view.findViewById(R.id.numberPicker2);
        nP3=view.findViewById(R.id.numberPicker3);
        nP4=view.findViewById(R.id.numberPicker4);
        nP5=view.findViewById(R.id.numberPicker5);
        nP6=view.findViewById(R.id.numberPicker6);
        nP7=view.findViewById(R.id.numberPicker7);
        nP8=view.findViewById(R.id.numberPicker8);
        nP9=view.findViewById(R.id.numberPicker9);
        Tong=view.findViewById(R.id.TongCau);
        edtTenDeThi = view.findViewById(R.id.edtTenDe);
        edtThoiGian = view.findViewById(R.id.edtThoiGian);
        btnTaoDe = view.findViewById(R.id.btn_taode);
    }

    public void check() {
        cB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cB1.isChecked()) {
                    nP1.setEnabled(true);
                } else {
                    nP1.setEnabled(false);
                    nP1.setValue(0);
                }
            }
        });
        cB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cB2.isChecked()) {
                    nP2.setEnabled(true);
                } else {
                    nP2.setEnabled(false);
                    nP2.setValue(0);
                }
            }
        });
        cB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cB3.isChecked()) {
                    nP3.setEnabled(true);
                } else {
                    nP3.setEnabled(false);
                    nP3.setValue(0);
                }
            }
        });
        cB4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cB4.isChecked()) {
                    nP4.setEnabled(true);
                } else {
                    nP4.setEnabled(false);
                    nP4.setValue(0);
                }
            }
        });
        cB5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cB5.isChecked()) {
                    nP5.setEnabled(true);
                }
                else {
                    nP5.setEnabled(false);
                    nP5.setValue(0);
                }
            }
        });
        cB6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cB6.isChecked()) {
                    nP6.setEnabled(true);
                }
                else {
                    nP6.setEnabled(false);
                    nP6.setValue(0);
                }
            }
        });
        cB7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cB7.isChecked()) {
                    nP7.setEnabled(true);
                }
                else {
                    nP7.setEnabled(false);
                    nP7.setValue(0);
                }
            }
        });
        cB8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cB8.isChecked()) {
                    nP2.setEnabled(true);
                }
                else {
                    nP8.setEnabled(false);
                    nP8.setValue(0);
                }
            }
        });
        cB9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cB9.isChecked()) {
                    nP9.setEnabled(true);
                }
                else {
                    nP9.setEnabled(false);
                    nP9.setValue(0);
                }
            }
        });
    }
    public void SetNumPick(){
        nP1.setMinValue(0);
        nP1.setMaxValue(7);
        nP1.setEnabled(false);
        nP2.setMinValue(0);
        nP2.setMaxValue(7);
        nP2.setEnabled(false);
        nP3.setMinValue(0);
        nP3.setMaxValue(7);
        nP3.setEnabled(false);
        nP4.setMinValue(0);
        nP4.setMaxValue(7);
        nP4.setEnabled(false);
        nP5.setMinValue(0);
        nP5.setMaxValue(7);
        nP5.setEnabled(false);
        nP6.setMinValue(0);
        nP6.setMaxValue(7);
        nP6.setEnabled(false);
        nP7.setMinValue(0);
        nP7.setMaxValue(7);
        nP7.setEnabled(false);
        nP8.setMinValue(0);
        nP8.setMaxValue(7);
        nP8.setEnabled(false);
        nP9.setMinValue(0);
        nP9.setMaxValue(7);
        nP9.setEnabled(false);
    }
}
