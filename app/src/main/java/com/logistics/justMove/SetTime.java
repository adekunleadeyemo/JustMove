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
import android.widget.Toast;

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
    TextView time_next;
    ImageView back_btn;

    FrameLayout dateFragment;

    TextView timeTxt;

    TextView nav_txt;

    ImageView time_vehicle;
    TextView pk_txt;
    Intent intent;

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
        time_next = findViewById(R.id.time_next);

        time_vehicle.setImageResource(getIntent().getExtras().getInt("vehicle"));
        currDate.setText(getIntent().getExtras().getString("price"));
        timeTxt.setText(getIntent().getExtras().getString("price"));

        intent = new Intent(SetTime.this, Item_Details.class);

        replaceFragment(new SetDateFragment(findViewById(R.id.curr_date),findViewById(R.id.time_txt), findViewById(R.id.pickup_txt)), R.id.date_fragment);

        time_next.setOnClickListener(e -> {
           if(continue_btn.getTransitionName().equals("continue") && !currTime.getText().toString().isEmpty()){
               intent.putExtra("date",currDate.getText().toString());
               intent.putExtra("time", currTime.getText().toString());
               intent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
               intent.putExtra("price",getIntent().getExtras().getString("price"));
               intent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
               intent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
               intent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
               intent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
               startActivity(intent);
            }
           else {
               Toast.makeText(SetTime.this,"Set Date and Time",Toast.LENGTH_LONG).show();
           }
        });

       continue_btn.setOnClickListener(e ->
        {
            if(continue_btn.getTransitionName().equals("setTime")){
                replaceFragment(new SetTimeFragment(findViewById(R.id.curr_time),findViewById(R.id.time_txt), findViewById(R.id.pickup_txt)), R.id.date_fragment);
                continue_btn.setTransitionName("continue");
                continue_btn.setText("Continue");
                nav_txt.setText("Pickup Time");
                back_btn.setTransitionName("setDate");
            }
            else if(!currTime.getText().toString().isEmpty()){
                intent.putExtra("date",currDate.getText().toString());
                intent.putExtra("time", currTime.getText().toString());
                intent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
                intent.putExtra("price",getIntent().getExtras().getString("price"));
                intent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
                intent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
                intent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
                intent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
                startActivity(intent);
            }
            else{
                Toast.makeText(SetTime.this,"Set Date and Time",Toast.LENGTH_LONG).show();
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
           else {
               Intent vehicleIntent = new Intent(SetTime.this,Vehicle.class);
               vehicleIntent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
               vehicleIntent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
               vehicleIntent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
               vehicleIntent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
               startActivity(vehicleIntent);
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