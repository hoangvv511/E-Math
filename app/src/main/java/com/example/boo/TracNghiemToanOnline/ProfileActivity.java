package com.example.boo.TracNghiemToanOnline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private TextView textViewUserEmail;

    private Button buttonLogout;

    private DatabaseReference databaseRefence = FirebaseDatabase.getInstance().getReference();

    private EditText editTextName, editTextAddress, editTextUserName;

    private Button buttonSave;

    private ImageView imgViewAvatar;
    private ImageView img;

    private static int GALLERY_REQUEST = 1;

    public static String username;

    String imageAvatar;

    Uri uri;

    FirebaseUser user = firebaseAuth.getCurrentUser();

    FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        imgViewAvatar = (ImageView)findViewById(R.id.imgAvatar);

        img = (ImageView)findViewById(R.id.img);

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome " + user.getEmail());

        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

        imgViewAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent lấy ra gallery
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        storageRef = storage.getReferenceFromUrl("gs://myapplication-6d1ae.appspot.com");
    }

    private void saveUserInformation()
    {
        String name = editTextName.getText().toString().trim();
        String add = editTextAddress.getText().toString().trim();
        String username2 = editTextUserName.getText().toString().trim();
        username = username2;

        UserInformation userInformation = new UserInformation(name, add,  user.getEmail(), imageAvatar, username2);

        databaseRefence.child("Users").child(username2).child("Information").setValue(userInformation);

        Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(view == buttonSave)
        {
            StorageReference mountainsRef = storageRef.child(user.getEmail());

            // Get the data from an ImageView as bytes
            imgViewAvatar.setDrawingCacheEnabled(true);
            imgViewAvatar.buildDrawingCache();
            Bitmap bitmap = imgViewAvatar.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception exception)
                {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    imageAvatar = String.valueOf(downloadUrl);
                    saveUserInformation();

                    databaseRefence.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                UserInformation userInformation = data.getValue(UserInformation.class);

                                if(userInformation.email == user.getEmail()){
                                    Picasso.get().load(userInformation.imageAvatar).into(img);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            });
            startActivity(new Intent(this, test.class));
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            uri = data.getData(); //Lấy ra uri của image
            data.getData();
            imgViewAvatar.setImageURI(uri); //Set image lựa chọn theo uri
            //
        }
    }
}
