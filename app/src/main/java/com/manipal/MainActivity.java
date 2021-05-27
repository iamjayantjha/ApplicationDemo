package com.manipal;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manipal.Model.User;

import java.util.ArrayList;

/**Coded By iamjayantjha on 18/04/2021
 * **/

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference reference;
    TextView status;
    Dialog dialog;
    RelativeLayout rl;
    ListView lv;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    String tile,txt1,txt2,txt3;
    ImageView fi,si,pi,ri,anr,fui,pri,smi,logoutBtn,close,logo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        status = findViewById(R.id.status);
        mCurrentUser = mAuth.getCurrentUser();

    }
      /*  fi = findViewById(R.id.fi);
        rl = findViewById(R.id.info);
        lv = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchBar);
        si = findViewById(R.id.si);
        pi = findViewById(R.id.pi);
        ri = findViewById(R.id.ri);
        close = findViewById(R.id.close);
        logo = findViewById(R.id.logo);
        anr = findViewById(R.id.anr);
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.check_layout);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
       // dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        fui = findViewById(R.id.fui);
        logoutBtn = findViewById(R.id.logout);
        pri = findViewById(R.id.pri);
        smi = findViewById(R.id.smi);
        list = new ArrayList<>();
        list.add("Giriraj Methi");
        list.add("Nilanjan Halder");
        list.add("Alok Damre");
        list.add("Prakash Ramani");
        list.add("Prabhu Inbaraj");
        list.add("Tej Pal");
        list.add("Pushpendra Kumar");
        list.add("Vishal Das");
        list.add("Rashi Nathawat");
        list.add("Naveen Kumar Singh");
        list.add("Sourav Kumar Das");
        list.add("Reenu Gill");
        list.add("Vikram Singh Kashyap");
        list.add("Bhoopendra Panchri");
        list.add("Dr. Dinesh Yadav");
        list.add("Mahesh Kumar Dubey");
        list.add("Lav Maheshwari");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                close.setVisibility(View.VISIBLE);
                rl.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        Button okay = dialog.findViewById(R.id.okayBtn);
        Button cancel = dialog.findViewById(R.id.cancelBtn);
        TextView title = dialog.findViewById(R.id.headerText);
        TextView message = dialog.findViewById(R.id.confirmText);
//        searchView.setOnClickListener(v -> {
//            vibrator.vibrate(pattern, -1);
//            rl.setVisibility(View.GONE);
//            lv.setVisibility(View.VISIBLE);
//        });
        close.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            rl.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);
            close.setVisibility(View.GONE);
            searchView.setFocusable(false);
        });
        okay.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            dialog.dismiss();
            FirebaseAuth.getInstance().signOut();
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            finish();
        });
        cancel.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            dialog.dismiss();
        });
        logoutBtn.setOnClickListener(v -> {
            title.setVisibility(View.VISIBLE);
            title.setText(R.string.logout);
            message.setVisibility(View.VISIBLE);
            message.setText(R.string.message);
            vibrator.vibrate(pattern, -1);
            dialog.show();
        });
        fi.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="fi";
            txt1="FACULTY WISE";
            txt2 = "SCHOOL WISE";
            txt3= "DEPARTMENT WISE";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(fi, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
          //  pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
        si.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="si";
            txt1="FACULTY WISE";
            txt2 = "SCHOOL WISE";
            txt3= "DEPARTMENT WISE";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(si, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
           // pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
        pi.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="pi";
            txt1="FACULTY WISE";
            txt2 = "SCHOOL WISE";
            txt3= "DEPARTMENT WISE";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(pi, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
           // pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
        ri.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="ri";
            txt1="FACULTY WISE";
            txt2 = "SCHOOL WISE";
            txt3= "DEPARTMENT WISE";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(ri, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
            //pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
        anr.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="anr";
            txt1="FACULTY WISE";
            txt2 = "SCHOOL WISE";
            txt3= "DEPARTMENT WISE";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(anr, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
           // pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
        fui.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="fui";
            txt1="DIRECTOR";
            txt2 = "DEPUTY DIRECTOR";
            txt3= "ASSISTANT DIRECTOR";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(fui, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
           // pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
        pri.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="pri";
            txt1="FACULTY WISE";
            txt2 = "SCHOOL WISE";
            txt3= "DEPARTMENT WISE";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(pri, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
           // pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
        smi.setOnClickListener(v ->{
            Intent info = new Intent(MainActivity.this, InformationActivity.class);
            tile ="smi";
            txt1="BOARD OF MANAGEMENT";
            txt2 = "EXECUTIVE COUNCIL";
            txt3= "ACADEMIC COUNCIL";
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair<View, String>(smi, "tile");
            pairs[1] = new Pair<View, String>(logo,"logo");
          //  pairs[2] = new Pair<View, String>(logoutBtn, "back");
            pairs[2] = new Pair<View, String>(searchView, "search");
            info.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            info.putExtra("tile",tile);
            info.putExtra("txt1",txt1);
            info.putExtra("txt2",txt2);
            info.putExtra("txt3",txt3);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(info, options.toBundle());
            vibrator.vibrate(pattern, -1);
        });
    }
*/

        @Override
        protected void onStart () {
            super.onStart();
            if (mCurrentUser == null) {
                Intent main = new Intent(MainActivity.this, LoginActivity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(main);
                finish();
            } else {
                reference = FirebaseDatabase.getInstance().getReference("Users").child(mCurrentUser.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (getApplicationContext() == null) {
                            return;
                        }
                        User user = dataSnapshot.getValue(User.class);
                        assert user != null;
                        status.setText(user.getApproved());
                        if (status.getText().equals("N") || status.getText().equals("n") || status.getText().equals("No") || status.getText().equals("no")) {
                            Intent main = new Intent(MainActivity.this, ApprovalPendingActivity.class);
                            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(main);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
    }
