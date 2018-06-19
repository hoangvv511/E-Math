package com.UIT.boo.TracNghiemToanOnline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private EditText editTextUserName;
    private EditText editTextConfirmPassword;
    private EditText editTextFullname;
    private EditText editTextPhone;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private CircleImageView cimv_useravatar;
    private Uri filepath;
    private Button btn_xoay;
    private int check = 0;
    private String email,password,username,confirmpassword,fullname,phone;
    private final int PICK_IMAGE_REQUEST = 71;
    private String imagebase64;
    private boolean isUsername;
    private int count2 = 0;

    Handler handler;
    SweetAlertDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editUserName);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextFullname = (EditText) findViewById(R.id.editTextFullname);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        cimv_useravatar = findViewById(R.id.cimv_useravatar);
        btn_xoay = findViewById(R.id.btn_xoay);
        btn_xoay.setVisibility(View.GONE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_user);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,200,200,true);
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        cimv_useravatar.setImageBitmap(scaledBitmap);
        byte[] imageBytes = baos.toByteArray();
        imagebase64 = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        cimv_useravatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                username = editTextUserName.getText().toString().trim();
                confirmpassword = editTextConfirmPassword.getText().toString().trim();
                fullname = editTextFullname.getText().toString().trim();
                phone = editTextPhone.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu ít nhất 6 kí tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) // k nhập tài khoản sẽ báo lỗi
                {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) // k nhập pass sẽ báo lỗi
                {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập mật khẩu xác thực", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmpassword)) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(fullname)) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }
                Dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                Dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                Dialog.setTitleText("Đang đăng kí....");
                Dialog.setCancelable(false);
                Dialog.show();
                handler = new Handler();
                SplashScreen.databaseRefence.child("Username").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String a = String.valueOf(dataSnapshot.getValue());
                        if (a.equals(username)) {
                            isUsername = true;
                        }
                        count2++;
                        if (count2 >= SplashScreen.count1)
                        {
                            count2 = 0;
                            if (isUsername)
                            {
                                Dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                Dialog.setTitleText("Tên tài khoản đã tồn tại !");
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Dialog.dismissWithAnimation();
                                    }
                                }, 5000);
                                //Toast.makeText(RegisterActivity.this, "Username exists", Toast.LENGTH_SHORT).show();
                                isUsername = false;
                                //return;
                            } else {
                                // User does not exist. NOW call createUserWithEmailAndPassword
                                firebaseAuth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    String user_id = firebaseAuth.getCurrentUser().getUid();
                                                    final DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Information");
                                                    UserInformation userInformation = new UserInformation(email, fullname, imagebase64, phone, username);
                                                    current_user.setValue(userInformation);
                                                    SplashScreen.databaseRefence.child("Username").push().setValue(username);

                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            if (firebaseAuth.getCurrentUser() != null) {
                                                                finish();
                                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                                                                Dialog.dismissWithAnimation();
                                                            }
                                                        }
                                                    }, 1500);
                                                    Dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                    Dialog.setTitleText("Đăng kí thành công!");
                                                } else { // tạo không thành công
                                                    Dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                                    Dialog.setTitleText("Email đã tồn tại!");
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Dialog.dismissWithAnimation();
                                                        }
                                                    }, 1500);
                                                }
                                            }
                                        });
                                // Your previous code here.
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        textViewSignin.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() != null)
        {
            filepath = data.getData();
            try
            {
                final Bitmap bmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);

                int x = bmap.getWidth();
                int y = bmap.getHeight();
                if(x <= 200 && y <= 200)
                {
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmap,x,y,true);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                    cimv_useravatar.setImageBitmap(scaledBitmap);
                    byte[] b = baos.toByteArray();
                    imagebase64= Base64.encodeToString(b, Base64.DEFAULT);
                }
                else
                {
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmap,200,200,true);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                    cimv_useravatar.setImageBitmap(scaledBitmap);
                    byte[] b = baos.toByteArray();
                    imagebase64= Base64.encodeToString(b, Base64.DEFAULT);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        if(view == textViewSignin)
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        }
    }

}
