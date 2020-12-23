package com.manipal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manipal.Model.User;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;
    DatabaseReference reference;
    TextView status;
    String uID;
    Dialog dialog;
    RelativeLayout rl;
    ListView lv;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    //String[] data = {"Giriraj Methi","Nilanjan Halder", "Alok Damre", "Prakash Ramani", "Prabhu Inbraj", "Tej Pal"};
    ImageView fi,si,pi,ri,anr,fui,pri,smi,logoutBtn,close;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        status = findViewById(R.id.status);
        mCurrentUser = mAuth.getCurrentUser();
        uID = mCurrentUser.getUid();
        fi = findViewById(R.id.fi);
        rl = findViewById(R.id.info);
        lv = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchBar);
        si = findViewById(R.id.si);
        pi = findViewById(R.id.pi);
        ri = findViewById(R.id.ri);
        close = findViewById(R.id.close);
        anr = findViewById(R.id.anr);
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.check_layout);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
       // dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        fui = findViewById(R.id.fui);
        logoutBtn = findViewById(R.id.logout);
        pri = findViewById(R.id.pri);
        smi = findViewById(R.id.smi);
        list = new ArrayList<String>();
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
                rl.setVisibility(View.GONE);
                lv.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {40, 80};
        TextView okay = dialog.findViewById(R.id.okayBtn);
        TextView cancel = dialog.findViewById(R.id.cancelBtn);
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
            searchView.setFocusable(false);
        });
        okay.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            dialog.dismiss();
            FirebaseAuth.getInstance().signOut();
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(login);
            finish();
        });
        cancel.setOnClickListener(v -> {
            vibrator.vibrate(pattern, -1);
            dialog.dismiss();
        });
        logoutBtn.setOnClickListener(v -> {
            title.setVisibility(View.VISIBLE);
            title.setText("Log Out");
            message.setVisibility(View.VISIBLE);
            message.setText("Are you sure you want to Log Out?");
            vibrator.vibrate(pattern, -1);
            dialog.show();
//            vibrator.vibrate(pattern, -1);
//            AlertDialog.Builder logOutConfirmation = new AlertDialog.Builder(MainActivity.this);
//            logOutConfirmation.setTitle("Log Out");
//            logOutConfirmation.setMessage("Are you sure you want to Log Out?");
//            logOutConfirmation.setPositiveButton("Yes", (dialog, which) -> {
//                vibrator.vibrate(pattern, -1);
//                FirebaseAuth.getInstance().signOut();
//                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
//                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(login);
//                finish();
//            });
//            logOutConfirmation.setNegativeButton("No", (dialog, which) -> {
//                vibrator.vibrate(pattern, -1);
//            });
//            logOutConfirmation.create().show();
        });
        fi.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
        si.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
        pi.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
        ri.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
        anr.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
        fui.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
        pri.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
        smi.setOnClickListener(v ->
                vibrator.vibrate(pattern, -1)
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
    if (mCurrentUser == null){
        Intent main = new Intent(MainActivity.this, LoginActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main);
        finish();
    }else
    {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(uID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getApplicationContext() == null){
                    return;
                }
                User user = dataSnapshot.getValue(User.class);
                assert user != null;
                status.setText(user.getApproved());
                if (status.getText().equals("N")||status.getText().equals("n")){
                    Intent main = new Intent(MainActivity.this,ApprovalPendingActivity.class);
                    main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(main);
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