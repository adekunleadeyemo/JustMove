package com.logistics.justMove;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Location extends AppCompatActivity {
    TextView addr_1;
    TextView addr_2;
    TextView addr_pin_txt;
    ConstraintLayout addr_pin;
    Button set_loc;
    ImageView set_addr;

    @Nullable String pickup_addr;
    @Nullable String delivery_addr;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        intent = new Intent(Location.this, locationSearch.class);

        Bundle extras  = getIntent().getExtras();
        @Nullable String changeType = extras.getString("change");


        addr_1 = findViewById(R.id.addr_1);
        addr_2 = findViewById(R.id.addr_2);
        set_addr = findViewById(R.id.set_addr);
        set_loc = findViewById(R.id.set_location);
        addr_pin = findViewById(R.id.location_pin);
        addr_pin_txt = findViewById(R.id.location_pin_text);


        if(changeType.equals("delivery")) {
            delivery_addr = extras.getString("delivery");
            String [] dl_Arr = delivery_addr.split(",");
            addr_pin.setVisibility(View.VISIBLE);
            addr_pin_txt.setText(extras.getString("pickup").split(",")[0]);
            addr_1.setText(dl_Arr[0]);
            addr_2.setText(dl_Arr[1] + dl_Arr[2]);
            set_loc.setTransitionName("continue");
        }
        else {
            pickup_addr = extras.getString("pickup");
            String [] pk_Arr = pickup_addr.split(",");
            addr_1.setText(pk_Arr[0]);
            addr_2.setText(pk_Arr[1]+ pk_Arr[2]);

        }

        set_addr.setOnClickListener(e -> {
            if(changeType.equals("pickup")) {
                pickup_addr = extras.getString("pickup");
                String [] pk_Arr = pickup_addr.split(",");
                addr_1.setText(pk_Arr[0]);
                addr_2.setText(pk_Arr[1] + pk_Arr[2]);
                intent.putExtra("change","pickup");
                startActivity(intent);
            }
            else {
                @Nullable String addr = extras.getString("delivery");
                String [] addrArr = addr.split(",");
                addr_1.setText(addrArr[0]);
                addr_2.setText(addrArr[1] + addrArr[2]);
                intent.putExtra("change","delivery");
                startActivity(intent);
            }
        });
        set_loc.setOnClickListener( e -> {
            if(set_loc.getTransitionName().equals("pickup")) {
                pickup_addr = extras.getString("pickup");
                String [] pk_Arr = pickup_addr.split(",");
                addr_1.setText("Enter Destination");
                addr_2.setText("");
                addr_pin.setVisibility(View.VISIBLE);
                addr_pin_txt.setText(pk_Arr[0]);
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