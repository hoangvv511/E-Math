package com.UIT.boo.TracNghiemToanOnline.slide;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.UIT.boo.TracNghiemToanOnline.R;
import com.UIT.boo.TracNghiemToanOnline.question.Question;

import java.util.ArrayList;

import de.timfreiheit.mathjax.android.MathJaxView;

import static com.UIT.boo.TracNghiemToanOnline.slide.ScreenSlideActivity.mPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    ArrayList<Question> arr_Ques;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    public int mPageNumber; // vị trí trang hiện tại
    public int checkAns;   // biến kiểm tra ...
    TextView tvNum, tv_goiy;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    private TextView tv_numpape;
    private ImageView imv_back, imv_go;
    String ansA, ansB, ansC, ansD,question, result, base64image, base64image2;
    ImageView im_question, im_goiy;
    public MathJaxView mv_question,mv_AnsA, mv_AnsB, mv_AnsC, mv_AnsD, mv_goiy;
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }
    final Handler handler = new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        //Khoi tao
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
        tv_numpape = rootView.findViewById(R.id.tv_numpage);
        imv_back = rootView.findViewById(R.id.imv_backpage);
        imv_go = rootView.findViewById(R.id.imv_gopage);
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
        question= arr_Ques.get(mPageNumber).getQuestion();
        base64image = arr_Ques.get(mPageNumber).getImage();
        base64image2 = arr_Ques.get(mPageNumber).getHuongdangiai_image();
        ansA = getItem(mPageNumber).getAns_a();
        ansB = getItem(mPageNumber).getAns_b();
        ansC = getItem(mPageNumber).getAns_c();
        ansD = getItem(mPageNumber).getAns_d();
        result = getItem(mPageNumber).getHuongdangiai();
    }


    public static ScreenSlidePageFragment create(int pageNumber, int checkAnswer) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_numpape.setText((mPageNumber+1) + "/" + arr_Ques.size());
        tvNum.setText("Câu " + (mPageNumber + 1));
        mv_goiy.setInputText(result);
        mv_question.setInputText(question);
        mv_AnsA.setInputText(ansA);
        mv_AnsB.setInputText(ansB);
        mv_AnsC.setInputText(ansC);
        mv_AnsD.setInputText(ansD);
        tv_goiy.setEnabled(false);
        tv_goiy.setVisibility(View.GONE);
        im_goiy.setEnabled(false);
        im_goiy.setVisibility(View.GONE);

        imv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem()+1);
            }
        });

        imv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem()-1);
            }
        });

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
                }
            }.execute();
        }

        mv_goiy.setEnabled(false);
        mv_goiy.setVisibility(View.GONE);

        if(mv_goiy.getInputText() == null) mv_goiy.setInputText("Câu này bạn tự suy nghĩ đáp án nha ^^");

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

        // Chọn đáp án
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
            //radioGroup.setVisibility(View.GONE);
            tv_goiy.setVisibility(View.VISIBLE);
            im_goiy.setVisibility(View.VISIBLE);
            mv_goiy.setVisibility(View.VISIBLE);
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
        String MyAns = getItem(mPageNumber).getDapAnChon();
        if(DapAnChon.equals("A") && MyAns.equals("A"))
        {
            mv_AnsA.setBackgroundColor(Color.rgb(0	,255 	,64));
        }
        if(DapAnChon.equals("A") && !MyAns.equals("A"))
        {
            mv_AnsA.setBackgroundColor(Color.rgb(0	,255 	,64));
            if(MyAns.equals("B"))  mv_AnsB.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("C"))  mv_AnsC.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("D"))  mv_AnsD.setBackgroundColor(Color.rgb(255	,0 	,0));
        }
        if(DapAnChon.equals("B") && MyAns.equals("B"))
        {
            mv_AnsB.setBackgroundColor(Color.rgb(0	,255 	,64));
        }
        if(DapAnChon.equals("B") && !MyAns.equals("B"))
        {
            mv_AnsB.setBackgroundColor(Color.rgb(0	,255 	,64));
            if(MyAns.equals("A"))  mv_AnsA.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("C"))  mv_AnsC.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("D"))  mv_AnsD.setBackgroundColor(Color.rgb(255	,0 	,0));
        }
        if(DapAnChon.equals("C") && MyAns.equals("C"))
        {
            mv_AnsC.setBackgroundColor(Color.rgb(0	,255 	,64));
        }
        if(DapAnChon.equals("C") && !MyAns.equals("C"))
        {
            mv_AnsC.setBackgroundColor(Color.rgb(0	,255 	,64));
            if(MyAns.equals("B"))  mv_AnsB.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("A"))  mv_AnsA.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("D"))  mv_AnsD.setBackgroundColor(Color.rgb(255	,0 	,0));
        }
        if(DapAnChon.equals("D") && MyAns.equals("D"))
        {
            mv_AnsD.setBackgroundColor(Color.rgb(0	,255 	,64));
        }
        if(DapAnChon.equals("D") && !MyAns.equals("D"))
        {
            mv_AnsD.setBackgroundColor(Color.rgb(0	,255 	,64));
            if(MyAns.equals("B"))  mv_AnsB.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("C"))  mv_AnsC.setBackgroundColor(Color.rgb(255	,0 	,0));
            if(MyAns.equals("A"))  mv_AnsA.setBackgroundColor(Color.rgb(255	,0 	,0));
        }
    }
}
