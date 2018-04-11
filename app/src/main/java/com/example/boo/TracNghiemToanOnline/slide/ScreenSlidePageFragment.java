package com.example.boo.TracNghiemToanOnline.slide;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.boo.TracNghiemToanOnline.R;
import com.example.boo.TracNghiemToanOnline.question.Question;
import com.example.mathjaxandroid.MathJaxView;
import java.util.ArrayList;

import io.github.kexanie.library.MathView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    ArrayList<Question> arr_Ques;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    public int mPageNumber; // vị trí trang hiện tại
    public int checkAns;   // biến kiểm tra ...

    TextView tvNum;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView im_question;
    MathView mv_question, mv_AnsA, mv_AnsB, mv_AnsC, mv_AnsD;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        tvNum = (TextView) rootView.findViewById(R.id.tvNum);
        mv_AnsA = (MathView) rootView.findViewById(R.id.mv_AnsA);
        mv_AnsB = (MathView) rootView.findViewById(R.id.mv_AnsB);
        mv_AnsC = (MathView) rootView.findViewById(R.id.mv_AnsC);
        mv_AnsD = (MathView) rootView.findViewById(R.id.mv_AnsD);
        mv_question = (MathView) rootView.findViewById(R.id.mv_question);
        im_question = (ImageView) rootView.findViewById(R.id.imV_question);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radG_Ans);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr_Ques = new ArrayList<Question>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        arr_Ques = slideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns= getArguments().getInt(ARG_CHECKANSWER);


    }

    public static ScreenSlidePageFragment create(int pageNumber, int checkAnswer) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

//    public Question getItem(int position) {
//        return arr_Ques.get(position);
//    }
    @SuppressLint("StaticFieldLeak")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu " + (mPageNumber + 1));
        String question= arr_Ques.get(mPageNumber).getQuestion();
        mv_question.config(
                "MathJax.Hub.Config({\n"+
                        "  CommonHTML: { linebreaks: { automatic: true } },\n"+
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n"+
                        "         SVG: { linebreaks: { automatic: true } }\n"+
                        "});");
        mv_question.setText(question);
        final String base64image = arr_Ques.get(mPageNumber).getImage();

        if(base64image == null)
        {
            im_question.setEnabled(false);
            im_question.setVisibility(View.GONE);
        }
        else
        {
            new AsyncTask<Void, Void, String>()
                {
                    @Override
                    protected String doInBackground(Void... voids) {
                        return null;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        byte[] decodeString = Base64.decode(base64image, Base64.DEFAULT);
                        Bitmap decoded = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                        Bitmap bMapScaled = Bitmap.createScaledBitmap(decoded, 500, 500, true);
                        im_question.setImageBitmap(bMapScaled);


                    }
                }.execute();
        }

        //
        mv_AnsA.config(
                "MathJax.Hub.Config({\n"+
                        "  CommonHTML: { linebreaks: { automatic: true } },\n"+
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n"+
                        "         SVG: { linebreaks: { automatic: true } }\n"+
                        "});");
        mv_AnsA.setText(getItem(mPageNumber).getAns_a());
        mv_AnsB.config(
                "MathJax.Hub.Config({\n"+
                        "  CommonHTML: { linebreaks: { automatic: true } },\n"+
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n"+
                        "         SVG: { linebreaks: { automatic: true } }\n"+
                        "});");
        mv_AnsB.setText(getItem(mPageNumber).getAns_b());
        mv_AnsC.config(
                "MathJax.Hub.Config({\n"+
                        "  CommonHTML: { linebreaks: { automatic: true } },\n"+
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n"+
                        "         SVG: { linebreaks: { automatic: true } }\n"+
                        "});");
        mv_AnsC.setText(getItem(mPageNumber).getAns_c());
        mv_AnsD.config(
                "MathJax.Hub.Config({\n"+
                        "  CommonHTML: { linebreaks: { automatic: true } },\n"+
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n"+
                        "         SVG: { linebreaks: { automatic: true } }\n"+
                        "});");
        mv_AnsD.setText(getItem(mPageNumber).getAns_d());



        if(checkAns!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }

        if(radA.isChecked())
        {
            getItem(mPageNumber).setTraloi(getChoiceFromID(R.id.radA));
        }
        else if(radB.isChecked())
        {
            getItem(mPageNumber).setTraloi(getChoiceFromID(R.id.radB));
        }
        else if(radC.isChecked())
        {
            getItem(mPageNumber).setTraloi(getChoiceFromID(R.id.radC));
        }
        else if(radD.isChecked())
        {
            getItem(mPageNumber).setTraloi(getChoiceFromID(R.id.radD));
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(mPageNumber).choiceID = checkedId;
                getItem(mPageNumber).setTraloi(getChoiceFromID(checkedId));
            }
        });



    }

    public Question getItem(int posotion){
        return arr_Ques.get(posotion);
    }

    //Lấy giá trị (vị trí) radiogroup chuyển thành đáp án A/B/C/D
    private String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";
        } else if (ID == R.id.radD) {
            return "D";
        } else return "";
    }

    //Hàm kiểm tra câu đúng, nếu câu đúng thì đổi màu background radiobutton tương ứng
    private void getCheckAns(String ans){
        if(ans.equals("A")==true){
            mv_AnsA.setBackgroundColor(Color.rgb(0	,178	,191));
        }
        else if(ans.equals("B")==true){
            mv_AnsB.setBackgroundColor(Color.rgb(0	,178	,191));
        }else if(ans.equals("C")==true){
            mv_AnsC.setBackgroundColor(Color.rgb(0	,178	,191));
        }else if(ans.equals("D")==true){
            mv_AnsD.setBackgroundColor(Color.rgb(0	,178	,191));
        }else ;
    }


}
