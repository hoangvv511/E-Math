package com.UIT.boo.TracNghiemToanOnline.TaiLieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.Lego.TracNghiemToanOnline.R;

public class TaiLieuWebView extends AppCompatActivity {
    private WebView webView;
    private String url;
    private TextView tentailieu;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_lieu_web_view);
        webView = findViewById(R.id.webview_tailieu);
        tentailieu = findViewById(R.id.tv_tentailieu);
        btn_back = findViewById(R.id.btn_goback);

        Intent intent = getIntent();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        tentailieu.setText(intent.getStringExtra("ten"));
        tentailieu.setSelected(true);
        webView.loadUrl(intent.getStringExtra("link"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
