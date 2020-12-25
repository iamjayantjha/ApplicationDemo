package com.manipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manipal.Model.User;

public class ApprovalPendingActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference reference;
    TextView status,username;
    String uID;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_pending);
        mAuth = FirebaseAuth.getInstance();
        status = findViewById(R.id.status);
        username = findViewById(R.id.username);
        mCurrentUser = mAuth.getCurrentUser();
        uID = mCurrentUser.getUid();
        logoutBtn = findViewById(R.id.logoutBtn);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        logoutBtn.setOnClickListener(v ->{
            vibrator.vibrate(pattern, -1);
            FirebaseAuth.getInstance().signOut();
            Intent login = new Intent(ApprovalPendingActivity.this, LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(uID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null){
                    return;
                }

                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                username.setVisibility(View.VISIBLE);
                username.setText(user.getName()+" ,");
                status.setText(user.getApproved());
                if (status.getText().equals("Y")||status.getText().equals("y")||status.getText().equals("Yes")||status.getText().equals("yes")){
                    Intent main = new Intent(ApprovalPendingActivity.this,MainActivity.class);
                    main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(main);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}