package com.UIT.boo.TracNghiemToanOnline.slide;

import android.app.Dialog;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.Lego.TracNghiemToanOnline.R;
import com.UIT.boo.TracNghiemToanOnline.ScorePreviewFragment;
import com.UIT.boo.TracNghiemToanOnline.question.Question;
import com.UIT.boo.TracNghiemToanOnline.question.QuestionController;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    public static int NUM_PAGES=50;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    public static ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    private TextView tvKiemtra, tvTimer, tvXemDiem;
    public int checkAns = 0;

    //CSDL
    private QuestionController questionController;
    private ArrayList<Question> arr_Ques;
    private CounterClass timer;
    private TextView tende;
    //String subject;
    private int num_exam;
    private int totalTimer;
    private String name_exam;
    private String name , thoigian, tongsocau;
    private Map<String, Long> mapID;
    private ArrayList<Long> listID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        tende = findViewById(R.id.tv_tendethi);

        Intent intent = getIntent();
        name_exam = intent.getStringExtra("tendethi");
        name = intent.getStringExtra("TenDe");
        thoigian = intent.getStringExtra("Thoigian");
        tongsocau = intent.getStringExtra("SoCau");
        listID = new ArrayList<>();
        mapID = (Map<String, Long>) intent.getSerializableExtra("Cauhoi");
        for(Long value : mapID.values())
             {
                 listID.add(value);
             }
        if(name_exam != null)
        {
            tende.setText(name_exam);
        }
        else if(name != null)
        {
            tende.setText(name);
        }
        tende.setSelected(true);

        if(thoigian!= null && tongsocau!=null )
        {
            totalTimer = Integer.valueOf(thoigian);
            NUM_PAGES = Integer.parseInt(tongsocau);

            timer = new CounterClass(totalTimer * 60 * 1000, 1000);
            questionController = new QuestionController(this);
            arr_Ques = new ArrayList<Question>();

            //Lay list cau hoi
            for(int i=0; i<listID.size();i++)
            {
                ArrayList<Question> itemcauhoi = new ArrayList<>();
                itemcauhoi = questionController.getQuestionByID(listID.get(i));
                arr_Ques.add(itemcauhoi.get(0));
            }

            tvKiemtra = (TextView) findViewById(R.id.tvKiemTra);
            tvTimer = (TextView) findViewById(R.id.tvTimer);
            tvXemDiem = (TextView) findViewById(R.id.tvScore);

            tvKiemtra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer();
                }
            });
            tvXemDiem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent1 = new Intent(ScreenSlideActivity.this, TestDoneActivity.class);
                    intent1.putExtra("arr_Ques", arr_Ques);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                }
            });

            timer.start();
        }
        else
        {
            num_exam = intent.getIntExtra("num_exam",0);
            totalTimer = 90;
            timer = new CounterClass(totalTimer * 60 * 1000, 1000);
            questionController = new QuestionController(this);
            arr_Ques = new ArrayList<Question>();
            arr_Ques = questionController.getQuestion(num_exam);
            tvKiemtra = (TextView) findViewById(R.id.tvKiemTra);
            tvTimer = (TextView) findViewById(R.id.tvTimer);
            tvXemDiem = (TextView) findViewById(R.id.tvScore);

            tvKiemtra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer();
                }
            });

            tvXemDiem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(ScreenSlideActivity.this, TestDoneActivity.class);
                    intent1.putExtra("arr_Ques", arr_Ques);
                    intent1.putExtra("exam", num_exam);
                    intent1.putExtra("name_exam", name_exam);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                }
            });
            timer.start();
        }

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
    }

    public ArrayList<Question> getData() {
        return arr_Ques;
    }

    @Override
    public void onBackPressed() {
//        if (mPager.getCurrentItem() == 0 ||  checkAns == 1) {
//            dialogExit();
//        } else {
//            // Otherwise, select the previous step.
//            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//        }
        dialogExit();
    }

    public void dialogExit(){
        new SweetAlertDialog(ScreenSlideActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Trở lại góc học tập ?")
                .setCancelText("No")
                .setConfirmText("Yes")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        timer.cancel();
                        finish();
                    }
                })
                .show();
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position, checkAns);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    //Dialog hiện thị danh sách những câu trả lời và chưa trả lời...
    public void checkAnswer() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);
        dialog.setTitle("Danh sách câu trả lời");

        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter(arr_Ques, this);
        GridView gvLsQuestion = (GridView) dialog.findViewById(R.id.gvLsQuestion);
        gvLsQuestion.setAdapter(answerAdapter);

        gvLsQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });

        final Button btnCancle, btnFinish;
        btnCancle = dialog.findViewById(R.id.btnCancle);
        btnFinish = dialog.findViewById(R.id.btnFinish);
        if(checkAns==1) btnFinish.setVisibility(View.GONE);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////
                timer.cancel();
                final SweetAlertDialog newDialog = new SweetAlertDialog(ScreenSlideActivity.this,SweetAlertDialog.PROGRESS_TYPE);
                newDialog.setTitleText("Đang tính điểm....");
                newDialog.show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newDialog.dismissWithAnimation();
                        ScorePreviewFragment fragment = new ScorePreviewFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("arr_Ques2", arr_Ques);
                        bundle.putInt("exam2", num_exam);
                        bundle.putString("name_exam", name_exam);
                        fragment.setArguments(bundle);
                        FragmentManager manager = getSupportFragmentManager();
                        fragment.show(manager, "SCORE");
                    }
                },3000);
                result();
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    public void result() {
        checkAns = 1;
        if (mPager.getCurrentItem() >= 4) mPager.setCurrentItem(mPager.getCurrentItem() - 4);
        else if (mPager.getCurrentItem() <= 4) mPager.setCurrentItem(mPager.getCurrentItem() + 4);
        tvXemDiem.setVisibility(View.VISIBLE);
    }

    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        //millisInFuture: 60*1000
        //countDownInterval:  1000
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
            result();
        }
    }

}