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
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    ArrayList<Question> arr_Ques;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    public int mPageNumber; // vị trí trang hiện tại
    public int checkAns;   // biến kiểm tra ...
    public String MyAns;
    TextView tvNum, tv_goiy;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView im_question, im_goiy;
   // MathView mv_AnsA, mv_AnsB, mv_AnsC, mv_AnsD;
    io.github.sidvenu.mathjaxview.MathJaxView mv_question,mv_AnsA, mv_AnsB, mv_AnsC, mv_AnsD, mv_goiy;
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        tvNum = rootView.findViewById(R.id.tvNum);
        mv_AnsA = rootView.findViewById(R.id.mv_AnsA);
        mv_AnsB = rootView.findViewById(R.id.mv_AnsB);
        mv_AnsC = rootView.findViewById(R.id.mv_AnsC);
        mv_AnsD = rootView.findViewById(R.id.mv_AnsD);
        mv_question = rootView.findViewById(R.id.mv_question);
        mv_goiy = rootView.findViewById(R.id.mv_goiy);
        im_goiy = rootView.findViewById(R.id.imv_goiy);
        tv_goiy = rootView.findViewById(R.id.tv_goiy);
        im_question = rootView.findViewById(R.id.imV_question);
        radA = rootView.findViewById(R.id.radA);
        radB =  rootView.findViewById(R.id.radB);
        radC =  rootView.findViewById(R.id.radC);
        radD =  rootView.findViewById(R.id.radD);
        radioGroup = rootView.findViewById(R.id.radG_Ans);
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

        final String base64image = arr_Ques.get(mPageNumber).getImage();
        final String base64image2 = arr_Ques.get(mPageNumber).getHuongdangiai_image();

        mv_question.setText(question);
        mv_goiy.setText(getItem(mPageNumber).getHuongdangiai());

        mv_AnsA.setText(getItem(mPageNumber).getAns_a());

        mv_AnsB.setText(getItem(mPageNumber).getAns_b());

        mv_AnsC.setText(getItem(mPageNumber).getAns_c());

        mv_AnsD.setText(getItem(mPageNumber).getAns_d());

        tv_goiy.setEnabled(false);
        tv_goiy.setVisibility(View.GONE);

        if(base64image2 == null)
        {
            im_goiy.setEnabled(false);
            im_goiy.setVisibility(View.GONE);
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
                    byte[] decodeString2 = Base64.decode(base64image2, Base64.DEFAULT);
                    Bitmap decoded2 = BitmapFactory.decodeByteArray(decodeString2, 0, decodeString2.length);
                    Bitmap bMapScaled2 = Bitmap.createScaledBitmap(decoded2, decoded2.getWidth()*2, decoded2.getHeight()*2, true);
                    im_goiy.setImageBitmap(bMapScaled2);
                    im_goiy.setVisibility(View.GONE);
                }
            }.execute();
        }

        mv_goiy.setEnabled(false);
        mv_goiy.setVisibility(View.GONE);

        if(mv_goiy.getText() == null) mv_goiy.setText("Câu này dễ quá bạn tự làm nha ^^");


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
                        Bitmap bMapScaled = Bitmap.createScaledBitmap(decoded, decoded.getWidth()*2, decoded.getHeight()*2, true);
                        im_question.setImageBitmap(bMapScaled);
                    }
                }.execute();
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(mPageNumber).choiceID = checkedId;
                getItem(mPageNumber).setDapAnChon(getChoiceFromID(checkedId));
            }
        });

        if(radA.isChecked())
        {
            getItem(mPageNumber).setDapAnChon(getChoiceFromID(R.id.radA));
        }
        else if(radB.isChecked())
        {
            getItem(mPageNumber).setDapAnChon(getChoiceFromID(R.id.radB));
        }
        else if(radC.isChecked())
        {
            getItem(mPageNumber).setDapAnChon(getChoiceFromID(R.id.radC));
        }
        else if(radD.isChecked())
        {
            getItem(mPageNumber).setDapAnChon(getChoiceFromID(R.id.radD));
        }

        if(checkAns!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }

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
    private void getCheckAns(String DapAnChon)
    {
        radioGroup.setVisibility(View.GONE);
        tv_goiy.setVisibility(View.VISIBLE);
        im_goiy.setVisibility(View.VISIBLE);
        mv_goiy.setVisibility(View.VISIBLE);
        if(DapAnChon.equals("A")==true)
        {
            mv_AnsA.setBackgroundColor(Color.rgb(0	,255 	,64));

        }
        if(DapAnChon.equals("B")==true)
        {
            mv_AnsA.setBackgroundColor(Color.rgb(0	,255 	,64));
        }
        if(DapAnChon.equals("C")==true)
        {
            mv_AnsA.setBackgroundColor(Color.rgb(0	,255 	,64));
        }
        if(DapAnChon.equals("D")==true)
        {
            mv_AnsA.setBackgroundColor(Color.rgb(0	,255 	,64));
        }
    }
}
