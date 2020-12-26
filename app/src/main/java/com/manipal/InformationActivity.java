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

public class InformationActivity extends AppCompatActivity {
    ImageView tileImg,logo;
    String tile;
    TextView status,txt1,txt2,txt3;
    String text1,text2,text3;
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
        //back = findViewById(R.id.back);
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
            tileImg.setImageResource(R.drawable.fi);
        } else if (status.getText().equals("si")){
            tileImg.setImageResource(R.drawable.si);
        } else if (status.getText().equals("pi")){
            tileImg.setImageResource(R.drawable.pi);
        } else if (status.getText().equals("ri")){
            tileImg.setImageResource(R.drawable.ri);
        }else if (status.getText().equals("anr")){
            tileImg.setImageResource(R.drawable.anr);
        }else if (status.getText().equals("fui")){
            tileImg.setImageResource(R.drawable.fui);
        }else if (status.getText().equals("pri")){
            tileImg.setImageResource(R.drawable.pri);
        }else if (status.getText().equals("smi")){
            tileImg.setImageResource(R.drawable.smi);
        }

    /*    back.setOnClickListener(v ->{
            vibrator.vibrate(pattern, -1);
//            Pair[] pairs = new Pair[1];
//            pairs[0] = new Pair<View, String>(tileImg, "tile");
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InformationActivity.this, pairs);
//            Intent main = new Intent(InformationActivity.this,MainActivity.class);
//            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
//            startActivity(main);
//            finish();
//            Intent mainIntent = new Intent(InformationActivity.this, MainActivity.class);
//            InformationActivity.this.startActivity(mainIntent);
//            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//            InformationActivity.this.finish();
        });*/

        txt1.setOnClickListener(v -> {
            Intent details = new Intent(InformationActivity.this, DetailsActivity.class);
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(logo, "logo");
            pairs[1] = new Pair<View, String>(txt1,"type");
            pairs[2] = new Pair<View,String>(searchView, "search");
            details.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(InformationActivity.this, pairs);
            startActivity(details, options.toBundle());
        });
    }
}