package com.manipal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manipal.Model.Attendance;
import com.manipal.Model.User;

/**Coded By iamjayantjha on 14/12/2020
 * **/

public class AttendanceActivity extends AppCompatActivity {
    ImageView logo;
    TextView header, rowHeading,rowHeading_2, col1, col2, col3, col4, col5, col6, col7, val1, val2, val3, val4, val5, val6, val7;
    RelativeLayout table5, table6, table7, table8;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference reference,newReference;
    Attendance attendance;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        header = findViewById(R.id.header);
        table5 = findViewById(R.id.table5);
        table6 = findViewById(R.id.table6);
        rowHeading = findViewById(R.id.rowHeading);
        rowHeading_2 = findViewById(R.id.rowHeading_2);
        col1 = findViewById(R.id.col1);
        col2 = findViewById(R.id.col2);
        col3 = findViewById(R.id.col3);
        col4 = findViewById(R.id.col4);
        logo = findViewById(R.id.logo);
        col5 = findViewById(R.id.col5);
        col6 = findViewById(R.id.col6);
        col7 = findViewById(R.id.col7);
        val1 = findViewById(R.id.val1);
        val2 = findViewById(R.id.val2);
        val3 = findViewById(R.id.val3);
        val4 = findViewById(R.id.val4);
        val5 = findViewById(R.id.val5);
        val6 = findViewById(R.id.val6);
        val7 = findViewById(R.id.val7);
        table7 = findViewById(R.id.table7);
        table8 = findViewById(R.id.table8);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        newReference = FirebaseDatabase.getInstance().getReference("Attendance").child(mCurrentUser.getUid());
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {80,40};
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null) {
                    return;
                }
                attendance = dataSnapshot.getValue(Attendance.class);
                assert attendance != null;
                val1.setText(attendance.getSubject_1());
                val2.setText(attendance.getSubject_2());
                val3.setText(attendance.getSubject_3());
                val4.setText(attendance.getSubject_4());
                val5.setText(attendance.getSubject_5());
                val6.setText(attendance.getSubject_6());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference = FirebaseDatabase.getInstance().getReference("Users").child(mCurrentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if (getApplicationContext() == null) {
                    return;
                }
                user = snapshot.getValue(User.class);
                assert user != null;
                header.setText(user.getName());
                if ((user.getCycle().equals("Physics"))||(user.getCycle().equals("Phy"))||(user.getCycle().equals("physics"))||(user.getCycle().equals("P"))||(user.getCycle().equals("p"))||(user.getCycle().equals("phy"))){
                    table7.setVisibility(View.GONE);
                    table8.setVisibility(View.GONE);
                    col1.setText("Engineering Mathematics");
                    col2.setText("Engineering Physics");
                    col3.setText("Environmental Studies");
                    col4.setText("Civil Engineering");
                    col5.setText("Electrical Engineering");
                }else if ((user.getCycle().equals("Chemistry"))||(user.getCycle().equals("Chem"))||(user.getCycle().equals("chemistry"))||(user.getCycle().equals("c"))||user.getCycle().equals("C")||(user.getCycle().equals("chem"))){
                    table8.setVisibility(View.GONE);
                    table7.setVisibility(View.VISIBLE);
                    col1.setText("Engineering Mathematics");
                    col2.setText("Engineering Chemistry");
                    col3.setText("Computers");
                    col4.setText("Mechanical Engineering");
                    col5.setText("Electrical Technology");
                    col6.setText("English");
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        logo.setOnClickListener(v->{
            Intent main = new Intent(AttendanceActivity.this,MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AttendanceActivity.this.finish();
        overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
    }
}