package com.manipal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    String text1,headerText;
    TextView header, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        text1 = getIntent().getStringExtra("text");
        header = findViewById(R.id.header);
        type = findViewById(R.id.type);
        type.setText(text1);
        headerText = getIntent().getStringExtra("headerText");
       header.setText(headerText);
    }
}