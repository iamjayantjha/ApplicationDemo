package com.manipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    TextView status;
    String uID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_pending);
        mAuth = FirebaseAuth.getInstance();
        status = findViewById(R.id.status);
        mCurrentUser = mAuth.getCurrentUser();
        uID = mCurrentUser.getUid();
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
                status.setText(user.getApproved());
                if (status.getText().equals("Y")||status.getText().equals("y")){
                    Intent main = new Intent(ApprovalPendingActivity.this,MainActivity.class);
                    main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(main);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}