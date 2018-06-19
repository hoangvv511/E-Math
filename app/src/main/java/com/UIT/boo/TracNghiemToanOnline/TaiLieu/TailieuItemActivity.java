package com.UIT.boo.TracNghiemToanOnline.TaiLieu;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.UIT.boo.TracNghiemToanOnline.R;

import java.util.ArrayList;
import java.util.List;

public class TailieuItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChuyenDeAdapter adapter;
    private List<ItemChuyenDe> itemChuyenDeList;
    private TextView tv_chuyende;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailieu_item);

        Intent intent = getIntent();
        tv_chuyende = findViewById(R.id.tv_tenchuyende);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_item);
        btn_back = findViewById(R.id.btn_back);

        tv_chuyende.setText(intent.getStringExtra("chuyende"));
        itemChuyenDeList = new ArrayList<>();
        adapter = new ChuyenDeAdapter(this, itemChuyenDeList);
        prepareAlbums();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        Intent intent = getIntent();
        String chuyende = intent.getStringExtra("cd");

        if(chuyende.equals("cd1"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề hàm số – Bùi Trần Duy Tuấn", "https://drive.google.com/file/d/124pDMNWPY8BYjaPfV7e4koYwK7TKgZSe/view",R.drawable.hamso1));
            itemChuyenDeList.add(new ItemChuyenDe("Trắc nghiệm nâng cao hàm số – Đặng Việt Đông", "https://drive.google.com/file/d/1JZyZjkoymaHIndp2lw_lO0HDeespqm_Z/view",R.drawable.hamso2));
            itemChuyenDeList.add(new ItemChuyenDe("Các bài toán điển hình ôn thi THPTQG 2018: Chuyên đề hàm số", "https://drive.google.com/file/d/1Gxg0o1-O388xWF0aHKlv08kQ60k2rv7T/view",R.drawable.hamso3));
            itemChuyenDeList.add(new ItemChuyenDe("Nâng cao kỹ năng giải toán trắc nghiệm 100% dạng bài hàm số và các bài toán liên quan – Tô Thị Nga", "https://drive.google.com/file/d/0B8wVelaD8LHqUHhPR3VHbWpoTVU/view",R.drawable.hamso4));
            itemChuyenDeList.add(new ItemChuyenDe("Các dạng toán đường tiệm cận của đồ thị hàm số – Lê Bá Bảo", "https://drive.google.com/file/d/0B8wVelaD8LHqX3pGY0dTeUlFQTA/view",R.drawable.hamso5));
            itemChuyenDeList.add(new ItemChuyenDe("Hướng dẫn giải các dạng toán cực trị của hàm số – Đặng Việt Đông", "https://drive.google.com/file/d/0B8wVelaD8LHqZ3VlU1BaRTF4Znc/view",R.drawable.hamso6));
        }
        else if(chuyende.equals("cd2"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Trắc nghiệm nâng cao mũ – logarit – Đặng Việt Đông", "https://drive.google.com/file/d/1EaoYrKrza8buKQ25C_gnGlKcoYB06v9M/view",R.drawable.mu1));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề lũy thừa, mũ và logarit – Bùi Trần Duy Tuấn", "https://drive.google.com/file/d/1oSWd8JYx_5s4D5CuA6savpq7YCfnuW9R/view",R.drawable.mu2));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề hàm số luỹ thừa, hàm số mũ và hàm số logarit – Trần Quốc Nghĩa", "https://drive.google.com/file/d/0B8wVelaD8LHqckVRbGFxQWZMems/view",R.drawable.mu3));
            itemChuyenDeList.add(new ItemChuyenDe("Bài tập trắc nghiệm chuyên đề mũ và logarit – Đặng Việt Đông", "https://drive.google.com/file/d/0B1NeyB1HG2f4WGc2RWdaQ2F4QmM/view",R.drawable.mu4));
            itemChuyenDeList.add(new ItemChuyenDe("Phân loại dạng và phương pháp giải nhanh chuyên đề mũ và logarit – Nguyễn Vũ Minh", "https://drive.google.com/file/d/0B8wVelaD8LHqSEQ0MTRoMVNBems/view?usp=drive_web",R.drawable.mu5));
            itemChuyenDeList.add(new ItemChuyenDe("Các phương pháp giải PT – BPT – HPT Mũ và Logarit – Nguyễn Trung Kiên", "https://drive.google.com/open?id=0B1NeyB1HG2f4djRpSHFjRXN3bFk",R.drawable.mu6));
        }
        else if(chuyende.equals("cd3"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Trắc nghiệm nâng cao nguyên hàm, tích phân và ứng dụng – Đặng Việt Đông", "https://drive.google.com/file/d/1F4Qy5CYguO0gBIUgQdHNlOpUpekW6hPS/view",R.drawable.tichphan1));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề nguyên hàm, tích phân và ứng dụng – Lê Văn Đoàn", "https://drive.google.com/file/d/1cY6z9ORtv5Xwm5uzweb8FL3uKZzB6fve/view",R.drawable.tichphan2));
            itemChuyenDeList.add(new ItemChuyenDe("Tuyển tập câu hỏi trắc nghiệm nguyên hàm – tích phân dùng Casio", "https://drive.google.com/file/d/1uP83Kp8e5z8sZoehSnCkNtvCr7pfCDgW/view",R.drawable.tichphan3));
            itemChuyenDeList.add(new ItemChuyenDe("1287 bài tập trắc nghiệm nguyên hàm, tích phân và ứng dụng có đáp án trong các đề thi thử môn Toán", "https://drive.google.com/file/d/1qF_WI2Xe5iSBKu-D8fadqMNZqunpDSKj/view",R.drawable.tichphan4));
            itemChuyenDeList.add(new ItemChuyenDe("Phân loại dạng và phương pháp giải nhanh nguyên hàm – tích phân – Nguyễn Vũ Minh (Tập 1)", "https://drive.google.com/file/d/1dMgBHPZxd7tOfjJrnw-OStqu1YQgbc0b/view",R.drawable.tichphan5));
            itemChuyenDeList.add(new ItemChuyenDe("8 kỹ thuật đạt điểm tối đa nguyên hàm – tích phân – Nguyễn Tiến Đạt", "https://drive.google.com/file/d/0B1NeyB1HG2f4czgwWWptZDhJRms/view",R.drawable.tichphan6));
        }
        else if(chuyende.equals("cd4"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Tài liệu tự học chuyên đề đa diện và thể tích khối đa diện – Lê Minh Cường", "https://drive.google.com/file/d/1GLAecd9yaNwJ7wZJBtqWWxk24zlC7fcB/view",R.drawable.hinhhoc1));
            itemChuyenDeList.add(new ItemChuyenDe("Trắc nghiệm nâng cao khối đa diện – Đặng Việt Đông", "https://drive.google.com/file/d/14griVo8OmT-dVMVvadNzzkpoE5_MDEJq/view",R.drawable.hinhhoc2));
            itemChuyenDeList.add(new ItemChuyenDe("Bài tập trắc nghiệm Hình học 12 chuyên đề nón – trụ – cầu", "https://drive.google.com/file/d/1GKb8xcAmBLKUvxFtyZE_Jazl_ZlKyCbY/view",R.drawable.hinhhoc3));
            itemChuyenDeList.add(new ItemChuyenDe("Phân loại dạng và phương pháp giải nhanh hình không gian – Nguyễn Vũ Minh, Lê Thị Phượng (Tập 2)", "https://drive.google.com/file/d/0B8wVelaD8LHqU21GZnNWYWUwdFE/view",R.drawable.hinhhoc4));
            itemChuyenDeList.add(new ItemChuyenDe("Giải nhanh hình học không gian bằng máy tính Casio – Hà Ngọc Toàn", "https://drive.google.com/file/d/0B1NeyB1HG2f4RW45UlNjT2d6a0U/view",R.drawable.hinhhoc5));
            itemChuyenDeList.add(new ItemChuyenDe("Phương pháp giải nhanh hình không gian – Trần Duy Thúc", "https://drive.google.com/file/d/0B1NeyB1HG2f4R1BzeGlBd3J4RUk/view",R.drawable.hinhhoc6));
        }
        else if(chuyende.equals("cd5"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Toán thực tế 12 có đáp án và lời giải chi tiết – Đặng Việt Đông, Ngọc Huyền LB", "https://drive.google.com/file/d/1nHQseAlW5TjVPO1KRBdxkuCMVPprbCCz/view",R.drawable.thucte1));
            itemChuyenDeList.add(new ItemChuyenDe("Rèn luyện kỹ năng giải quyết bài toán trắc nghiệm thực tế – Hứa Lâm Phong", "https://drive.google.com/file/d/0B8wVelaD8LHqMkZnUEJPQTZVRGc/view",R.drawable.thucte2));
            itemChuyenDeList.add(new ItemChuyenDe("Bài tập trắc nghiệm các dạng toán ứng dụng thực tế – Đặng Việt Đông", "https://drive.google.com/file/d/0B1NeyB1HG2f4TW1SdTJuX3Uybjg/view",R.drawable.thucte3));
            itemChuyenDeList.add(new ItemChuyenDe("Tuyển tập và giải chi tiết các bài toán thực tiễn trong đề thi thử – Trần Văn Tài", "https://drive.google.com/file/d/0B1NeyB1HG2f4X01yM2hnMUFTeVk/view",R.drawable.thucte4));
            itemChuyenDeList.add(new ItemChuyenDe("Bài toán thực tế và bài toán tối ưu min – max – Lê Viết Nhơn", "https://drive.google.com/file/d/0B1NeyB1HG2f4aUhHLXRnY1Zfa3c/view",R.drawable.thucte5));
            itemChuyenDeList.add(new ItemChuyenDe("Tuyển chọn 151 bài tập trắc nghiệm toán ứng dụng – Đặng Việt Đông", "https://drive.google.com/file/d/0B1NeyB1HG2f4NkU1aEQ4ZEctZDg/view",R.drawable.thucte6));
        }
        else if(chuyende.equals("cd6"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Trắc nghiệm nâng cao số phức – Đặng Việt Đông", "https://drive.google.com/file/d/1QC1HdQeMqjdN9zhUJKY63XPt-XR2JrLO/view",R.drawable.sophuc1));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề số phức – Bùi Trần Duy Tuấn", "https://drive.google.com/file/d/12HpzPf0twR-LszbC_xaXTlPKZM8pHh06/view",R.drawable.sophuc2));
            itemChuyenDeList.add(new ItemChuyenDe("Các dạng toán và bài tập số phức có lời giải chi tiết – Nguyễn Bảo Vương", "https://drive.google.com/file/d/1W0SnhcVY_FHlJha0lTjJ9uM0x_Qq9FGM/view",R.drawable.sophuc3));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề trắc nghiệm số phức – Ngô Nguyên", "https://drive.google.com/file/d/0B1NeyB1HG2f4ZnRleFFDMWwtNm8/view",R.drawable.sophuc4));
            itemChuyenDeList.add(new ItemChuyenDe("Phương pháp giải nhanh bài toán số phức bằng máy tính Casio – Nguyễn Việt Anh", "https://drive.google.com/file/d/0B1NeyB1HG2f4cG13SGhFYlN0eHc/view",R.drawable.sophuc5));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề trắc nghiệm số phức – Phạm Văn Huy", "https://drive.google.com/file/d/0B1NeyB1HG2f4cG13SGhFYlN0eHc/view",R.drawable.sophuc6));
        }
        else if(chuyende.equals("cd7"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề tổ hợp – xác suất – Bùi Trần Duy Tuấn", "https://drive.google.com/file/d/1x0XHdjPMILV_kwOg7_UpMtgFKlDU1ZfR/view",R.drawable.xacsuat1));
            itemChuyenDeList.add(new ItemChuyenDe("Bài tập trắc nghiệm tổ hợp và xác suất có đáp án và lời giải chi tiết – Đặng Việt Đông", "https://drive.google.com/file/d/0B8wVelaD8LHqLXhvMjh6SC0tWHM/view",R.drawable.xacsuat2));
            itemChuyenDeList.add(new ItemChuyenDe("250 bài tập trắc nghiệm chủ đề tổ hợp – xác suất có đáp án và lời giải chi tiết", "https://drive.google.com/file/d/0B8wVelaD8LHqeEJRMmN4RUZnNU0/view",R.drawable.xacsuat3));
            itemChuyenDeList.add(new ItemChuyenDe("Thủ thuật casio tìm hệ số trong khai triển nhị thức Newton – Bùi Thế Việt", "https://drive.google.com/file/d/0B1NeyB1HG2f4eFdRRDVtS0pfZ1U/view",R.drawable.xacsuat4));
            itemChuyenDeList.add(new ItemChuyenDe("Bài tập Quy tắc đếm và Nhị thức Newton – Trần Sĩ Tùng", "https://drive.google.com/file/d/0B1NeyB1HG2f4WHpSTUN6eVp3T0E/view",R.drawable.xacsuat5));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề hoán vị – chỉnh hợp – tổ hợp – Nguyễn Hữu Biển", "https://drive.google.com/file/d/0B1NeyB1HG2f4Sml5U3k0bWo1eDg/view",R.drawable.xacsuat6));
        }
        else if(chuyende.equals("cd8"))
        {
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề phương pháp tọa độ trong không gian – Bùi Trần Duy Tuấn", "https://drive.google.com/file/d/1L8k7lMVqaehV10DFSRP6ySkhmJUimkBj/view",R.drawable.toado1));
            itemChuyenDeList.add(new ItemChuyenDe("Trắc nghiệm nâng cao hình học tọa độ Oxyz – Đặng Việt Đông", "https://drive.google.com/file/d/1DrdFtQO15hI4LvHfGd7lbpevd30jZKSQ/view",R.drawable.toado2));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề phương pháp tọa độ trong không gian – Nguyễn Vũ Minh (Tập 2)", "https://drive.google.com/file/d/15EJpv4mPly0q_rdCVfRPUhd0jzmc6S0-/view",R.drawable.toado3));
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề trắc nghiệm phương pháp tọa độ trong không gian – Ngô Nguyên", "https://drive.google.com/file/d/0B1NeyB1HG2f4blhIZVA4Y016aWs/view",R.drawable.toado4));
            itemChuyenDeList.add(new ItemChuyenDe("Tổng hợp câu hỏi trắc nghiệm hay chương tọa độ không gian – Nguyễn Quang Hưng, Nguyễn Thành Tiến", "https://drive.google.com/file/d/0B1NeyB1HG2f4NXh5a3pHVzZTbU0/view",R.drawable.toado5));
            itemChuyenDeList.add(new ItemChuyenDe("Các dạng bài tập phương trình đường thằng trong không gian – Đặng Ngọc Hiền, Lê Bá Bảo", "https://drive.google.com/file/d/0B1NeyB1HG2f4Uk5aV3luakNQcGs/view",R.drawable.toado6));
        }
        else if (chuyende.equals("cd9")) //casio
        {
            itemChuyenDeList.add(new ItemChuyenDe("Casio luyện đề 2018 ver 1.0 – Nguyễn Thế Lực", "https://drive.google.com/file/d/1kI6SaxA97-uoMjfNXVGSd-EEtBwvrGTu/view",R.drawable.casio1));
            itemChuyenDeList.add(new ItemChuyenDe("Kỹ thuật sử dụng Casio – Vinacal hỗ trợ giải nhanh đề thi môn Toán 12", "https://drive.google.com/file/d/1_M_HqeNod4eus0WDtATtbhK8I-rcLxAb/view",R.drawable.casio2));
            itemChuyenDeList.add(new ItemChuyenDe("Bí kíp Thế Lực 2018 ver 1.0 (Chinh phục điểm 5 – 8)", "https://drive.google.com/file/d/0B8wVelaD8LHqYzVHVXRob2ZQWVE/view",R.drawable.casio3));
            itemChuyenDeList.add(new ItemChuyenDe("Bí kíp Thế Lực 2018 ver 1.0 (Chinh phục điểm 8 – 9 – 10)", "https://drive.google.com/file/d/0B8wVelaD8LHqeXRBMm9WdDM5dUE/view",R.drawable.casio4));
            itemChuyenDeList.add(new ItemChuyenDe("23 kỹ thuật sử dụng máy tính cầm tay Casio – Vinacal giải nhanh Toán 12 – Nguyễn Chiến", "https://drive.google.com/file/d/0B8wVelaD8LHqeXV3YmRHbFE4S0E/view",R.drawable.casio5));
            itemChuyenDeList.add(new ItemChuyenDe("Kỹ thuật và sai lầm khi sử dụng máy tính bỏ túi trong giải toán – Đoàn Văn Bộ, Huỳnh Anh Kiệt", "https://drive.google.com/file/d/0B1NeyB1HG2f4dlpnb01EVGtXbFk/view",R.drawable.casio6));
            itemChuyenDeList.add(new ItemChuyenDe("Bí kíp Thế Lực ver 3.0 – Nguyễn Thế Lực", "https://drive.google.com/file/d/0B1NeyB1HG2f4cDk4QS1fOUxYbjQ/view",R.drawable.casio7));
        }
        else if (chuyende.equals("cd10")) //csc-csn
        {
            itemChuyenDeList.add(new ItemChuyenDe("Chuyên đề quy nạp toán học, dãy số, cấp số cộng và cấp số nhân – Nguyễn Bảo Vương", "https://drive.google.com/file/d/1P16tn6F792lsyG2JFKi3UmMtZNCt4Qo7/view",R.drawable.csc1));
            itemChuyenDeList.add(new ItemChuyenDe("Hướng dẫn giải các dạng toán dãy số, cấp số cộng và cấp số nhân – Đặng Việt Đông", "https://drive.google.com/file/d/0B8wVelaD8LHqUHpqM2JBVWJJeUE/view",R.drawable.csc2));
            itemChuyenDeList.add(new ItemChuyenDe("Cách tìm công thức tổng quát của dãy số cho bởi công thức truy hồi – Phạm Thị Thu Huyền", "https://drive.google.com/file/d/1Fo847zDh3bcgdQdFru2QWtxPp5lSgYqO/view",R.drawable.csc3));
            itemChuyenDeList.add(new ItemChuyenDe("Dãy số và giới hạn của dãy số – Nguyễn Tất Thu", "https://drive.google.com/file/d/0B8wVelaD8LHqN3FNNENxcHNWcGc/view",R.drawable.csc4));
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}

