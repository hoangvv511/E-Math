package com.example.boo.TracNghiemToanOnline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;
    private String imagebase64;

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

        cimv_useravatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextEmail.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();
                final String username = editTextUserName.getText().toString().trim();
                final String confirmpassword = editTextConfirmPassword.getText().toString().trim();
                final String fullname = editTextFullname.getText().toString().trim();
                final String phone = editTextPhone.getText().toString().trim();
                Query usernamequery = FirebaseDatabase.getInstance().getReference().child("Users").child("Information").orderByChild("username").equalTo(username);
                usernamequery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getChildrenCount()>0)
                        {
                            Toast.makeText( RegisterActivity.this, "Choose a different username", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(TextUtils.isEmpty(username))
                            {
                                Toast.makeText(RegisterActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(username.length()<6)
                            {
                                Toast.makeText(RegisterActivity.this, "Username must at least 6 character", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(TextUtils.isEmpty(email)) // k nhập tài khoản sẽ báo lỗi
                            {
                                Toast.makeText(RegisterActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(TextUtils.isEmpty(password)) // k nhập pass sẽ báo lỗi
                            {
                                Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                                return;
                            }
                            if(TextUtils.isEmpty(confirmpassword))
                            {
                                Toast.makeText(RegisterActivity.this, "Please enter confirm password", Toast.LENGTH_LONG).show();
                                return;
                            }
                            if(!password.equals(confirmpassword))
                            {
                                Toast.makeText(RegisterActivity.this, "Password must be same", Toast.LENGTH_LONG).show();
                                return;
                            }
                            if(TextUtils.isEmpty(fullname))
                            {
                                Toast.makeText(RegisterActivity.this, "Please enter your fullname", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(TextUtils.isEmpty(phone))
                            {
                                Toast.makeText(RegisterActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            progressDialog.setMessage("Registering User...  ");
                            progressDialog.show();

                            firebaseAuth.createUserWithEmailAndPassword(email,password)
                                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                String user_id = firebaseAuth.getCurrentUser().getUid();
                                                final DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Information");
                                                UserInformation userInformation = new UserInformation(email, fullname, imagebase64, phone, username);
                                                current_user.setValue(userInformation);
                                                if(firebaseAuth.getCurrentUser() != null){ // tạo xong sẽ vào màn hình Profile
                                                    finish();
                                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                }
                                            } else { // tạo không thành công ( do tài khoản hay mật khẩu không đúng định dạng )
                                                Toast.makeText(RegisterActivity.this, "Could not register...please try again", Toast.LENGTH_SHORT).show();
                                            }
                                            progressDialog.dismiss();
                                        }
                                    });
                        }
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
                final Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                cimv_useravatar.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                imagebase64 = Base64.encodeToString(b, Base64.DEFAULT);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view == textViewSignin)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
