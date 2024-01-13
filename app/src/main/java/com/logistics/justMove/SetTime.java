package com.logistics.justMove;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.logistics.justMove.Models.JustMoveTime;
import com.logistics.justMove.Utils.JustMoveDateFormat;
import com.logistics.justMove.Utils.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class SetTime extends AppCompatActivity {

    RecyclerView recyclerView;
    List<JustMoveTime> dayDates;
    TextView currDate;
    TextView currTime;
    Button continue_btn;
    ImageView back_btn;

    FrameLayout dateFragment;

    TextView timeTxt;

    TextView nav_txt;

    ImageView time_vehicle;
    TextView pk_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        currDate = findViewById(R.id.curr_date);
        dateFragment = findViewById(R.id.date_fragment);
       continue_btn = findViewById(R.id.time_continue);
       back_btn = findViewById(R.id.time_back_arrow);
        currTime = findViewById(R.id.curr_time);
        timeTxt = findViewById(R.id.time_txt);
        nav_txt = findViewById(R.id.time_nav_txt);
        time_vehicle = findViewById(R.id.time_vehicle_img);
        pk_txt = findViewById(R.id.pickup_txt);

        time_vehicle.setImageResource(getIntent().getExtras().getInt("vehicle"));
        currDate.setText(getIntent().getExtras().getString("price"));
        timeTxt.setText(getIntent().getExtras().getString("price"));

        replaceFragment(new SetDateFragment(findViewById(R.id.curr_date),findViewById(R.id.time_txt), findViewById(R.id.pickup_txt)), R.id.date_fragment);

       continue_btn.setOnClickListener(e ->
        {
            if(continue_btn.getTransitionName().equals("setTime")){
                replaceFragment(new SetTimeFragment(findViewById(R.id.curr_time),findViewById(R.id.time_txt), findViewById(R.id.pickup_txt)), R.id.date_fragment);
                continue_btn.setTransitionName("continue");
                continue_btn.setText("Continue");
                nav_txt.setText("Pickup Time");
                back_btn.setTransitionName("setDate");
            }
            else{
                startActivity(new Intent(SetTime.this, Item_Details.class));
            }
        });

       back_btn.setOnClickListener( e -> {
           if(back_btn.getTransitionName().equals("setDate")){
               replaceFragment(new SetDateFragment(findViewById(R.id.curr_date),findViewById(R.id.time_txt), findViewById(R.id.pickup_txt)), R.id.date_fragment);
               continue_btn.setTransitionName("setTime");
               nav_txt.setText("Pickup Date");
               continue_btn.setText("Set Time");
               back_btn.setTransitionName("goBack");
           }
       });


    }


    private void replaceFragment(Fragment fragment, int fragmentId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentId, fragment);
        fragmentTransaction.commit();
    }


}