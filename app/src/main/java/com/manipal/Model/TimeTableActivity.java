package com.manipal.Model;

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
import com.manipal.MarksActivity;
import com.manipal.R;

public class TimeTableActivity extends AppCompatActivity {
    TextView heading,link;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference reference,newReference;
    Time_Table time_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        heading = findViewById(R.id.Heading);
        link = findViewById(R.id.link);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        newReference = FirebaseDatabase.getInstance().getReference("Time Table").child(mCurrentUser.getUid());
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null) {
                    return;
                }
                time_table = dataSnapshot.getValue(Time_Table.class);
                assert time_table != null;
                if (!time_table.getLink().equals("")){
                    heading.setText("Download your time table from the link below");
                    link.setText(time_table.getLink());
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
        TimeTableActivity.this.finish();
        overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
    }
}