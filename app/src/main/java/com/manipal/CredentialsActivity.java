package com.manipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CredentialsActivity extends AppCompatActivity {
    TextInputEditText mUsername, mName, mEmail, mPassword, mDepartment;
    Button regBtn;
    TextView certcred;
    ProgressDialog pd;
    FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
        mUsername = findViewById(R.id.usernametext);
        mEmail = findViewById(R.id.emailtext);
        mPassword = findViewById(R.id.passwordtext);
        mName = findViewById(R.id.nametext);
        mDepartment = findViewById(R.id.departmenttext);
        regBtn = findViewById(R.id.regBtn);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        mAuth = FirebaseAuth.getInstance();
        certcred = findViewById(R.id.certifiedcredentials);
        certcred.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            Intent login = new Intent(CredentialsActivity.this, LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        });
        regBtn.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            pd = new ProgressDialog(CredentialsActivity.this);
            pd.setMessage("Please Wait..");
            pd.show();

            String str_username = mUsername.getText().toString().trim();
            String str_name = mName.getText().toString().trim();
            String str_email = mEmail.getText().toString().trim();
            String str_password = mPassword.getText().toString().trim();
            String str_department = mDepartment.getText().toString().trim();

            if (TextUtils.isEmpty(str_username)||TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_name)||TextUtils.isEmpty(str_password)||TextUtils.isEmpty(str_department)){
                pd.dismiss();
                Toast.makeText(CredentialsActivity.this, "All field are required..", Toast.LENGTH_LONG).show();
            }else if (str_password.length() < 6){
                pd.dismiss();
                Toast.makeText(CredentialsActivity.this,"Password must be 6 characters long..",Toast.LENGTH_LONG).show();
            }else {
                register(str_username,str_department,str_email,str_password,str_name);
            }
        });
    }

    private void register(String username, String department, String email, String password, String name) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                String userUid = firebaseUser.getUid();
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userUid);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id",firebaseUser.getUid());
                hashMap.put("name",name);
                hashMap.put("username",username);
                hashMap.put("department", department);
                hashMap.put("email", email);
                hashMap.put("approved", "N");
                hashMap.put("imageURL","https://firebasestorage.googleapis.com/v0/b/vani-chat-9b86a.appspot.com/o/profile.jpg?alt=media&token=9f42e809-127c-49e2-9a53-351cd8d38104");
                reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()){
                        pd.dismiss();
                        Intent main = new Intent(CredentialsActivity.this, ApprovalPendingActivity.class);
                        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(main);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                    }else {
                        pd.dismiss();
                        Toast.makeText(CredentialsActivity.this, "Something went wrong! Please try later", Toast.LENGTH_LONG).show();
                    }
                });
            }else
            {
                Toast.makeText(getApplicationContext(),"Error occurred",Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }
}