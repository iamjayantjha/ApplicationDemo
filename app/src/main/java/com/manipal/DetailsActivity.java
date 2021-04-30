package com.manipal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**Coded By iamjayantjha on 14/12/2020
 * **/

public class DetailsActivity extends AppCompatActivity {
    String text1,headerText;
    ImageView logo;
    TextView header, type, rowHeading, col1, col2, col3, col4, col5, col6, col7, val1, val2, val3, val4, val5, val6, val7;
    RelativeLayout table5, table6, table7, table8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        text1 = getIntent().getStringExtra("text");
        header = findViewById(R.id.header);
        table5 = findViewById(R.id.table5);
        table6 = findViewById(R.id.table6);
        rowHeading = findViewById(R.id.rowHeading);
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
        type = findViewById(R.id.type);
        type.setText(text1);
        headerText = getIntent().getStringExtra("headerText");
       header.setText(headerText);
       if (headerText.equals("FACULTY INFORMATION")){
           rowHeading.setText("PROFESSOR");
           col1.setText("ASSISTANT PROFESSOR");
           col2.setText("ASSOCIATE PROFESSOR");
           col3.setText("PROFESSOR");
           val1.setText("220");
           val2.setText("300");
           val3.setText("354");
       }else if (headerText.equals("STUDENT INFORMATION")){
           table5.setVisibility(View.VISIBLE);
           rowHeading.setText("STUDENTS INFORMATION");
           col1.setText("STUDENTS ADMITTED");
           col2.setText("STUDENTS REGISTERED");
           col3.setText("WITHDRAWAL");
           col4.setText("PASSED OUT");
           val1.setText("220");
           val2.setText("300");
           val3.setText("80");
           val4.setText("120");
       } else if (headerText.equals("PLACEMENT INFORMATION")){
           table5.setVisibility(View.VISIBLE);
           table6.setVisibility(View.VISIBLE);
           table7.setVisibility(View.VISIBLE);
           rowHeading.setText("PLACEMENTS");
           col1.setText("STUDENTS PLACED");
           col2.setText("PERCENTAGE");
           col3.setText("COMPANIES VISITED");
           col4.setText("HIGHEST PACKAGE");
           col5.setText("AVERAGE PACKAGE");
           col6.setText("LOWEST PACKAGE");
           val1.setText("220");
           val2.setText("92%");
           val3.setText("150");
           val4.setText("1.5 Crore");
           val5.setText("20 Lakhs");
           val6.setText("12 Lakhs");
       }else if (headerText.equals("RESEARCH INFORMATION")){
           table5.setVisibility(View.VISIBLE);
           table6.setVisibility(View.VISIBLE);
           table7.setVisibility(View.VISIBLE);
           table8.setVisibility(View.VISIBLE);
           rowHeading.setText("RESEARCH");
           col1.setText("PUBLICATIONS");
           col2.setText("CONFERENCE PUBLICATIONS");
           col3.setText("SCOPUS PUBLICATIONS");
           col4.setText("SCI/SCIE PUBLICATIONS");
           col5.setText("UGC LISTED");
           col6.setText("FUNDINGS");
           col7.setText("IPRs");
           val1.setText("220");
           val2.setText("192");
           val3.setText("150");
           val4.setText("110");
           val5.setText("200");
           val6.setText("50 Lakhs");
           val7.setText("112");
       }else if (headerText.equals("AWARDS AND RECOGNITION")){
           rowHeading.setText("PROFESSOR");
           col1.setText("ASSISTANT PROFESSOR");
           col2.setText("ASSOCIATE PROFESSOR");
           col3.setText("PROFESSOR");
           val1.setText("220");
           val2.setText("300");
           val3.setText("354");
       }else if (headerText.equals("FUNCTIONAL UNITS INFORMATION")){
           rowHeading.setText("PROFESSOR");
           col1.setText("ASSISTANT PROFESSOR");
           col2.setText("ASSOCIATE PROFESSOR");
           col3.setText("PROFESSOR");
           val1.setText("220");
           val2.setText("300");
           val3.setText("354");
       }else if (headerText.equals("PROGRAM INFORMATION")){
           rowHeading.setText("PROFESSOR");
           col1.setText("ASSISTANT PROFESSOR");
           col2.setText("ASSOCIATE PROFESSOR");
           col3.setText("PROFESSOR");
           val1.setText("220");
           val2.setText("300");
           val3.setText("354");
       }else if (headerText.equals("STATUTORY MEETING INFORMATION")){
           rowHeading.setText("PROFESSOR");
           col1.setText("ASSISTANT PROFESSOR");
           col2.setText("ASSOCIATE PROFESSOR");
           col3.setText("PROFESSOR");
           val1.setText("220");
           val2.setText("300");
           val3.setText("354");
       }


        logo.setOnClickListener(v->{
            Intent main = new Intent(DetailsActivity.this,MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            finish();
        });

    }
}