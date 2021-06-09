package com.manipal;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manipal.Model.Grades;
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
    ListView lv;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    MaterialCardView card1,card2,card3,card4,card5,card6;
    ImageView logoutBtn,close,settings,notification;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        status = findViewById(R.id.status);
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        mCurrentUser = mAuth.getCurrentUser();
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        notification = findViewById(R.id.notification);
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.check_layout);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        logoutBtn = findViewById(R.id.logout);
        lv = findViewById(R.id.listView);
        settings = findViewById(R.id.settings);
        searchView = findViewById(R.id.searchBar);
        close = findViewById(R.id.close);
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
        list.add("Bhavana Saini");
        list.add("Arpit Kothari");
        notification.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
            startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_top);
        });
        card1.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
            startActivity(new Intent(MainActivity.this,AttendanceActivity.class));
            overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_bottom);
        });
        card2.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
            startActivity(new Intent(MainActivity.this, MarksActivity.class));
            overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_bottom);
        });
        card3.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
        });
        card4.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
            startActivity(new Intent(MainActivity.this, GradesActivity.class));
            overridePendingTransition(R.anim.slide_in_top,R.anim.slide_out_bottom);
        });
        card5.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
        });
        card6.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
        });
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
                settings.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        settings.setOnClickListener(v -> {
            vibrator.vibrate(pattern,-1);
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(settingsIntent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        });
        Button okay = dialog.findViewById(R.id.okayBtn);
        Button cancel = dialog.findViewById(R.id.cancelBtn);
        TextView title = dialog.findViewById(R.id.headerText);
        TextView message = dialog.findViewById(R.id.confirmText);
        close.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            lv.setVisibility(View.GONE);
            settings.setVisibility(View.VISIBLE);
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

    }

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
