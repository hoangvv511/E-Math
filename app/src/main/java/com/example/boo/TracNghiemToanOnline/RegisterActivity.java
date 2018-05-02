package com.example.boo.TracNghiemToanOnline;

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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextUsername;
    private TextView textViewSignin;
    private boolean isUsername = false;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser() != null){ // nếu đăng ký xong thì vào màn hình Profile
//            finish();
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername2);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser()
    {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String username2 = editTextUsername.getText().toString().trim();

        if(TextUtils.isEmpty(email)) // k nhập tài khoản sẽ báo lỗi
        {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) // k nhập pass sẽ báo lỗi
        {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(username2)) // k nhập pass sẽ báo lỗi
        {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
            return;
        }

        databaseRefence.child("Username").addChildEventListener(new ChildEventListener() { // kiểm tra database username vừa nhấp có trùng không
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String a = String.valueOf(dataSnapshot.getValue());
                if (a.equals(username2)) {
                    isUsername = true;
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

        if(isUsername)
        {
            Toast.makeText(RegisterActivity.this, "Username exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            databaseRefence.child("Username").push().setValue(username2);

            progressDialog.setMessage("Registering User...  ");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (firebaseAuth.getCurrentUser() != null) { // tạo xong sẽ vào màn hình Profile
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
    public void onClick(View view) {
        if(view == buttonRegister)
        {
            isUsername = false;
            registerUser();
        }
        if(view == textViewSignin)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
