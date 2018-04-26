package com.example.boo.TracNghiemToanOnline.Toan;


import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.boo.TracNghiemToanOnline.MainActivity;
import com.example.boo.TracNghiemToanOnline.R;
import com.shawnlin.numberpicker.NumberPicker;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaoDeFragment extends Fragment {

    EditText edtTenDeThi, edtThoiGian;
    CheckBox cB1, cB2, cB3, cB4, cB5, cB6, cB7, cB8, cB9;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv_sum;
    Button btnTaoDe;
    String tende;
    public TaoDeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tao_de, container, false);

        edtTenDeThi = view.findViewById(R.id.edtTenDe);
        edtThoiGian = view.findViewById(R.id.edtThoiGian);
        btnTaoDe = view.findViewById(R.id.btn_taode);
        cB1 = view.findViewById(R.id.cB_1);
        cB2 = view.findViewById(R.id.cB_2);
        cB3 = view.findViewById(R.id.cB_3);
        cB4 = view.findViewById(R.id.cB_4);
        cB5 = view.findViewById(R.id.cB_5);
        cB6 = view.findViewById(R.id.cB_6);
        cB7 = view.findViewById(R.id.cB_7);
        cB8 = view.findViewById(R.id.cB_8);
        cB9 = view.findViewById(R.id.cB_9);
        tv1 = view.findViewById(R.id.tv_1);
        tv2 = view.findViewById(R.id.tv_2);
        tv3 = view.findViewById(R.id.tv_3);
        tv4 = view.findViewById(R.id.tv_4);
        tv5 = view.findViewById(R.id.tv_5);
        tv6 = view.findViewById(R.id.tv_6);
        tv7 = view.findViewById(R.id.tv_7);
        tv8 = view.findViewById(R.id.tv_8);
        tv9 = view.findViewById(R.id.tv_9);
        tv_sum = view.findViewById(R.id.tv_sumquestion);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(1);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(2);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(3);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(4);
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(5);
            }
        });

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(6);
            }
        });

        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(7);
            }
        });

        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(8);
            }
        });

        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberPickerDialog(9);
            }
        });

        btnTaoDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BoSuuTap_Fragment boSuuTap_fragment = new BoSuuTap_Fragment();
//                Bundle bundle = new Bundle();
//                tende = String.valueOf(edtTenDeThi.getText().toString());
//                bundle.putString("Name", tende);
//                boSuuTap_fragment.setArguments(bundle);
//                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.content_main, boSuuTap_fragment);
//                fr.addToBackStack(null);
//                fr.commit();
            }
        });

        return view;
    }

    public void UpdateSumQuesttion()
    {
        int socau1 = Integer.valueOf(tv1.getText().toString());
        int socau2 = Integer.valueOf(tv2.getText().toString());
        int socau3 = Integer.valueOf(tv3.getText().toString());
        int socau4 = Integer.valueOf(tv4.getText().toString());
        int socau5 = Integer.valueOf(tv5.getText().toString());
        int socau6 = Integer.valueOf(tv6.getText().toString());
        int socau7 = Integer.valueOf(tv7.getText().toString());
        int socau8 = Integer.valueOf(tv8.getText().toString());
        int socau9 = Integer.valueOf(tv9.getText().toString());
        int sum = socau1 + socau2 + socau3 + socau4 + socau5 + socau6 + socau7 + socau8 + socau9;
        String s = String.valueOf("Tổng số câu : " + sum);
        tv_sum.setText(s);
    }

    private void numberPickerDialog(final int chuyende)
    {
        NumberPicker numberPicker = new NumberPicker(getActivity());
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch(chuyende)
                {
                    case 1:
                        if(cB1.isChecked()==false) tv1.setText("0");
                        else tv1.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 2:
                        if(cB2.isChecked()==false) tv2.setText("0");
                        else tv2.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 3:
                        if(cB3.isChecked()==false) tv3.setText("0");
                        else tv3.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 4:
                        if(cB4.isChecked()==false) tv4.setText("0");
                        else tv4.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 5:
                        if(cB5.isChecked()==false) tv5.setText("0");
                        else tv5.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 6:
                        if(cB6.isChecked()==false) tv6.setText("0");
                        else tv6.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 7:
                        if(cB7.isChecked()==false) tv7.setText("0");
                        else tv7.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 8:
                        if(cB8.isChecked()==false) tv8.setText("0");
                        else tv8.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                    case 9:
                        if(cB9.isChecked()==false) tv9.setText("0");
                        else tv9.setText(newVal + "");
                        UpdateSumQuesttion();
                        break;
                }
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(numberPicker);
        builder.setTitle("Số câu hỏi").setIcon(R.drawable.question);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
