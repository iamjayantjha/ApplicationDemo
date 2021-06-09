package com.manipal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.manipal.Model.User;

import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference reference;
    TextView name,email,dept;
    CircleImageView profilePic;
    Uri imageUri;
    String myUrl;
    Dialog dialog;
    StorageTask uploadTask;
    StorageReference storageReference;
    User user;
    ImageView logo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        logo = findViewById(R.id.logo);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {80,40};
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        dept = findViewById(R.id.dept);
        profilePic = findViewById(R.id.profilePic);
        reference = FirebaseDatabase.getInstance().getReference("Users").child(mCurrentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null) {
                    return;
                }
                user = dataSnapshot.getValue(User.class);
                assert user != null;
                name.setText("Name : "+user.getName());
                email.setText("Email : "+user.getEmail());
                dept.setText("Department : "+user.getDepartment());
                if (user.getImageURL().equals("https://firebasestorage.googleapis.com/v0/b/vani-chat-9b86a.appspot.com/o/profile.jpg?alt=media&token=9f42e809-127c-49e2-9a53-351cd8d38104")){
                    Glide.with(SettingsActivity.this).load(R.mipmap.ic_launcher_round).into(profilePic);
                }else {
                    Glide.with(SettingsActivity.this).load(user.getImageURL()).into(profilePic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        profilePic.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            choosePicture();
        });

        logo.setOnClickListener(v->{
            Intent main = new Intent(SettingsActivity.this, MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        SettingsActivity.this.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //startActivityForResult(intent, 1);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Photo"), 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void uploadPicture() {
        dialog = new Dialog(SettingsActivity.this);
        dialog.setContentView(R.layout.theme_dialog);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.picture_upload_bg));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        TextView title = dialog.findViewById(R.id.headerText);
        TextView message = dialog.findViewById(R.id.confirmText);
        title.setVisibility(View.VISIBLE);
        message.setVisibility(View.VISIBLE);
        title.setText(R.string.image_upload_title);
        message.setText(R.string.image_upload_message);
        dialog.show();

        StorageReference riversRef = storageReference.child("Profile Pictures").child(mCurrentUser.getUid()+".jpg");

        uploadTask = riversRef.putFile(imageUri);
        uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()){
                throw Objects.requireNonNull(task.getException());
            }
            return riversRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
        }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
            if (task.isSuccessful()){
                Uri downloadUri = task.getResult();
                myUrl = downloadUri.toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(mCurrentUser.getUid());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", mCurrentUser.getUid());
                hashMap.put("name", user.getName());
                hashMap.put("email", user.getEmail());
                hashMap.put("approved", user.getApproved());
                hashMap.put("imageURL",myUrl);
                hashMap.put("cycle",user.getCycle());
                hashMap.put("department",user.getDepartment());
                hashMap.put("username",user.getUsername());
                reference.setValue(hashMap);
                Glide.with(SettingsActivity.this).load(imageUri).into(profilePic);
                dialog.dismiss();
            }else {
                Toast.makeText(SettingsActivity.this,"Failed to upload image",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(SettingsActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show());
    }


}