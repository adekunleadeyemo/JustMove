package com.logistics.justMove;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Location extends AppCompatActivity {
    TextView pk_addr_1;
   TextView pk_addr_2 ;
    TextView dl_addr_1 ;
    TextView dl_addr_2 ;
    ConstraintLayout pk_addr_box;
    ConstraintLayout dl_addr_box;
    ImageView pk_icon;
    ImageView dl_icon;
    Button set_loc;

    @Nullable String pickup_addr;
    @Nullable String delivery_addr;
    @Nullable String [] pk_Arr;
    @Nullable String [] dl_Arr;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        LinearLayout.LayoutParams unset_loc_view = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 66);
        LinearLayout.LayoutParams set_loc_view = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 33);

        intent = new Intent(Location.this, locationSearch.class);

        Bundle extras  = getIntent().getExtras();
        @Nullable String changeType = extras.getString("change");
        delivery_addr = extras.getString("delivery");
        pickup_addr = extras.getString("pickup");
        @Nullable String [] pk_Arr = pickup_addr.split(",");


        pk_addr_1 = findViewById(R.id.pickup_addr_1);
        pk_addr_2 = findViewById(R.id.pickup_addr_2);
        dl_addr_1 = findViewById(R.id.delivery_addr_1);
        dl_addr_2 = findViewById(R.id.delivery_addr_2);
        pk_addr_box = findViewById(R.id.pickup_addr_container);
        dl_addr_box = findViewById(R.id.delivery_addr_container);
        pk_icon = findViewById(R.id.pickup_addr_icon);
        dl_icon = findViewById(R.id.delivery_addr_icon);
        set_loc = findViewById(R.id.set_location);



        if(changeType.equals("delivery")) {
            dl_Arr = delivery_addr.split(",");
            dl_addr_box.setLayoutParams(unset_loc_view);
            pk_addr_1.setText(pk_Arr[0]);
            pk_addr_2.setText(pk_Arr[1]+ pk_Arr[2]);
            pk_addr_box.setLayoutParams(set_loc_view);
            dl_addr_1.setText(dl_Arr[0]);
            dl_addr_2.setText(dl_Arr[1] + dl_Arr[2]);
            set_loc.setTransitionName("continue");
        }
        else {
            pk_addr_1.setText(pk_Arr[0]);
            pk_addr_2.setText(pk_Arr[1]+ pk_Arr[2]);
            if(delivery_addr != null && !delivery_addr.isEmpty()){
                dl_Arr = delivery_addr.split(",");
                pk_addr_box.setLayoutParams(unset_loc_view);
                dl_addr_box.setLayoutParams(set_loc_view);
                dl_addr_1.setText(dl_Arr[0]);
                dl_addr_2.setText(dl_Arr[1] + dl_Arr[2]);
                set_loc.setTransitionName("continue");
            }



        }

        pk_addr_box.setOnClickListener(e -> {
            if(pk_addr_box.getTransitionName().equals("disabled") && delivery_addr != null && !delivery_addr.isEmpty()) {

                pk_addr_box.setLayoutParams(unset_loc_view);
                dl_addr_box.setLayoutParams(set_loc_view);
                dl_addr_box.setTransitionName("disabled");
                pk_addr_box.setTransitionName("enabled");
            }
            else {
                if(delivery_addr != null && !delivery_addr.isEmpty()){
                    intent.putExtra("delivery", delivery_addr);
                }
                intent.putExtra("change","pickup");
                startActivity(intent);
            }
        });

        dl_addr_box.setOnClickListener(e -> {
            if(dl_addr_box.getTransitionName().equals("disabled") && delivery_addr != null && !delivery_addr.isEmpty()) {

                dl_addr_box.setLayoutParams(unset_loc_view);
                pk_addr_box.setLayoutParams(set_loc_view);
                pk_addr_box.setTransitionName("disabled");
                dl_addr_box.setTransitionName("enabled");
            }
            else {
                intent.putExtra("pickup", pickup_addr);
                intent.putExtra("change","delivery");
                startActivity(intent);
            }
        });


        set_loc.setOnClickListener( e -> {
            if(set_loc.getTransitionName().equals("pickup")) {
                pk_addr_box.setLayoutParams(set_loc_view);
                dl_addr_box.setLayoutParams(unset_loc_view);
                set_loc.setText("Enter Destination");
                set_loc.setTransitionName("delivery");
            }
            else if (set_loc.getTransitionName().equals("delivery")){
                intent.putExtra("change","delivery");
                intent.putExtra("pickup", pickup_addr);
                startActivity(intent);
            }
            else {
                startActivity(new Intent(Location.this, Introduction.class));
            }

        });
    }
}