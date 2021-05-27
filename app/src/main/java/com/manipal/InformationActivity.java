package com.manipal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

/**Coded By iamjayantjha on 26/04/2021
 * **/

public class InformationActivity extends AppCompatActivity {
    ImageView tileImg,logo;
    String tile;
    TextView status,txt1,txt2,txt3;
    String text1,text2,text3,headerText;
    SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tileImg = findViewById(R.id.info);
        status = findViewById(R.id.status);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        tile = getIntent().getStringExtra("tile");
        text1 = getIntent().getStringExtra("txt1");
        text2 = getIntent().getStringExtra("txt2");
        text3 = getIntent().getStringExtra("txt3");
        txt1.setText(text1);
        logo = findViewById(R.id.logo);
        txt2.setText(text2);
        txt3.setText(text3);
        searchView = findViewById(R.id.searchBar);
        status.setText(tile);
        if (status.getText().equals("fi")){
            headerText = "FACULTY INFORMATION";
            tileImg.setImageResource(R.drawable.fi);
        } else if (status.getText().equals("si")){
            headerText = "STUDENT INFORMATION";
            tileImg.setImageResource(R.drawable.si);
        } else if (status.getText().equals("pi")){
            headerText = "PLACEMENT INFORMATION";
            tileImg.setImageResource(R.drawable.pi);
        } else if (status.getText().equals("ri")){
            headerText = "RESEARCH INFORMATION";
            tileImg.setImageResource(R.drawable.ri);
        }else if (status.getText().equals("anr")){
            headerText = "AWARDS AND RECOGNITION";
            tileImg.setImageResource(R.drawable.anr);
        }else if (status.getText().equals("fui")){
            headerText = "FUNCTIONAL UNITS INFORMATION";
            tileImg.setImageResource(R.drawable.fui);
        }else if (status.getText().equals("pri")){
            headerText = "PROGRAM INFORMATION";
            tileImg.setImageResource(R.drawable.pri);
        }else if (status.getText().equals("smi")){
            headerText = "STATUTORY MEETING INFORMATION";
            tileImg.setImageResource(R.drawable.smi);
        }



        txt1.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            Intent details = new Intent(InformationActivity.this, DetailsActivity.class);
            Pair[] pairs = new Pair[3];
            details.putExtra("text",text1);
            details.putExtra("headerText",headerText);
            pairs[0] = new Pair<View, String>(logo, "logo");
            pairs[1] = new Pair<View, String>(txt1,"type");
            pairs[2] = new Pair<View,String>(searchView, "search");
           // pairs[3] = new Pair<View,String>(tileImg, "tile");
            details.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InformationActivity.this, pairs);
            startActivity(details, options.toBundle());
        });
        txt2.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            Intent details = new Intent(InformationActivity.this, DetailsActivity.class);
            Pair[] pairs = new Pair[3];
            details.putExtra("text",text2);
            details.putExtra("headerText",headerText);
            pairs[0] = new Pair<View, String>(logo, "logo");
            pairs[1] = new Pair<View, String>(txt2,"type");
            pairs[2] = new Pair<View,String>(searchView, "search");
            // pairs[3] = new Pair<View,String>(tileImg, "tile");
            details.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InformationActivity.this, pairs);
            startActivity(details, options.toBundle());
        });
        txt3.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            Intent details = new Intent(InformationActivity.this, DetailsActivity.class);
            Pair[] pairs = new Pair[3];
            details.putExtra("text",text3);
            details.putExtra("headerText",headerText);
            pairs[0] = new Pair<View, String>(logo, "logo");
            pairs[1] = new Pair<View, String>(txt3,"type");
            pairs[2] = new Pair<View,String>(searchView, "search");
            // pairs[3] = new Pair<View,String>(tileImg, "tile");
            details.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InformationActivity.this, pairs);
            startActivity(details, options.toBundle());
        });
    }
}