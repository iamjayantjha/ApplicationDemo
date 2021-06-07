package com.manipal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AttendanceActivity.this.finish();
        overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
    }
}