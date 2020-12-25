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
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {
    ImageView tileImg,back;
    String tile;
    TextView status;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tileImg = findViewById(R.id.info);
        status = findViewById(R.id.status);
        back = findViewById(R.id.back);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        tile = getIntent().getStringExtra("tile");
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

        back.setOnClickListener(v ->{
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
        });
    }
}