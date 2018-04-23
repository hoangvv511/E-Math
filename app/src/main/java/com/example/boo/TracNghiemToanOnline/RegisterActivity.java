package com.example.boo.TracNghiemToanOnline;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private EditText editTextUserName;
    private EditText editTextConfirmPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser() != null){ // nếu đăng ký xong thì vào màn hình Profile
//            finish();
//            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editUserName);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextEmail.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();
                final String username = editTextUserName.getText().toString().trim();
                final String confirmpassword = editTextConfirmPassword.getText().toString().trim();
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
                            progressDialog.setMessage("Registering User...  ");
                            progressDialog.show();

                            firebaseAuth.createUserWithEmailAndPassword(email,password)
                                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                String user_id = firebaseAuth.getCurrentUser().getUid();
                                                final DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Information");
                                                Map newPost = new HashMap();
                                                newPost.put("username", username);
                                                newPost.put("email", email);
                                                current_user.setValue(newPost);
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
//                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(!task.isSuccessful())
//                        {
//                            Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            String user_id = firebaseAuth.getCurrentUser().getUid();
//                            DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Information");
//
//                            Map newPost = new HashMap();
//                            newPost.put("username", username);
//                            newPost.put("email", email);
//                            current_user.setValue(newPost);
//                        }
//                    }
//                });
            }
        });
        textViewSignin.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
//        if(view == buttonRegister)
//        {
//            registerUser();
//        }
        if(view == textViewSignin)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
