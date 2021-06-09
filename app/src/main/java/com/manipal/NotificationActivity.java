package com.manipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manipal.Model.Marks;
import com.manipal.Model.Notification;

public class NotificationActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference newReference;
    Notification notification;
    TextView notificationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationText = findViewById(R.id.notification);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        newReference = FirebaseDatabase.getInstance().getReference("Notification").child(mCurrentUser.getUid());
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null) {
                    return;
                }
                notification = dataSnapshot.getValue(Notification.class);
                assert notification != null;
                if (!notification.getNotification().equals("")){
                    notificationText.setText(notification.getNotification());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        NotificationActivity.this.finish();
        overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_bottom);
    }
}