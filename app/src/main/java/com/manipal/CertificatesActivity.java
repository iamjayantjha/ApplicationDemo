package com.manipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manipal.Model.Certificates;
import com.manipal.Model.TimeTableActivity;
import com.manipal.Model.Time_Table;

public class CertificatesActivity extends AppCompatActivity {
    TextView heading,link;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference newReference;
    Certificates certificates;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates);
        logo = findViewById(R.id.logo);
        heading = findViewById(R.id.Heading);
        link = findViewById(R.id.link);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        newReference = FirebaseDatabase.getInstance().getReference("Certificates").child(mCurrentUser.getUid());
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null) {
                    return;
                }
                certificates = dataSnapshot.getValue(Certificates.class);
                assert certificates != null;
                if (!certificates.getLink().equals("")){
                    heading.setText("Download your certificates from the link below");
                    link.setText(certificates.getLink());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logo.setOnClickListener(v->{
            Intent main = new Intent(CertificatesActivity.this, MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        CertificatesActivity.this.finish();
        overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
    }
}