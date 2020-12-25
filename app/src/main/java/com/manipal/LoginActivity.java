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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mUsername, mEmail, mPassword;
    Button loginBtn;
    TextView certcred;
    ProgressDialog pd;
    FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername = findViewById(R.id.usernametext);
        mEmail = findViewById(R.id.emailtext);
        mPassword = findViewById(R.id.passwordtext);
        loginBtn = findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        certcred = findViewById(R.id.certifiedcredentials);
        certcred.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            Intent credIntent = new Intent(LoginActivity.this, CredentialsActivity.class);
            credIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(credIntent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        loginBtn.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            pd = new ProgressDialog(LoginActivity.this);
            pd.setMessage("Please Wait..");
            pd.show();
            String str_username = mUsername.getText().toString();
            String str_email = mEmail.getText().toString().trim();
            String str_password = mPassword.getText().toString().trim();
            if (TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_password)||TextUtils.isEmpty(str_username)){
                pd.dismiss();
                Toast.makeText(LoginActivity.this,"All fields are required..",Toast.LENGTH_LONG).show();
            }else {
                reference = FirebaseDatabase.getInstance().getReference();
                mAuth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()){
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                pd.dismiss();
                                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(main);
                                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                                finish();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                pd.dismiss();
                            }
                        });
                    }
                    else {
                        pd.dismiss();
                        Toast.makeText(LoginActivity.this,"Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){
            Intent main = new Intent(LoginActivity.this,MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(main);
            finish();
        }
    }
}