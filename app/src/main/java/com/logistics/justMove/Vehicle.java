package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Vehicle extends AppCompatActivity {

    TextView sm_car_sx;
    TextView md_car_sx;
    TextView lg_car_sx;
    LinearLayout sm_car;
    LinearLayout md_car;
    LinearLayout lg_car;
    ImageView car_img;
    LinearLayout car_desc;
    TextView car_nav_txt;

    TextView car_discount;
    TextView car_labour;
    TextView car_info;
    TextView car_price;
    SwitchCompat car_discount_btn;

    Intent intent;

    TextView next_btn;
    Button vehicleContinue;
    ImageView vehicle_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 100);
        LinearLayout.LayoutParams invisibleLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0);
        sm_car = findViewById(R.id.small_vehicle);
        md_car = findViewById(R.id.medium_vehicle);
        lg_car = findViewById(R.id.large_vehicle);
        car_img = findViewById(R.id.vehicle_img);
        sm_car_sx = findViewById(R.id.sm_vehicle_size);
        md_car_sx = findViewById(R.id.md_vehicle_size);
        lg_car_sx = findViewById(R.id.lg_vehicle_size);
        car_desc = findViewById(R.id.vehicle_desc);
        car_nav_txt = findViewById(R.id.vehicle_nav_txt);
        car_discount = findViewById(R.id.vehicle_discount);
        car_labour = findViewById(R.id.car_labour);
        car_info = findViewById(R.id.vehicle_info);
        car_price = findViewById(R.id.vehicle_pricing);
        car_discount_btn = findViewById(R.id.set_vehicle_discount);
        next_btn = findViewById(R.id.vehicle_next);
        vehicleContinue = findViewById(R.id.vehicle_continue);
        vehicle_back = findViewById(R.id.vehicle_back_arrow);

        intent = new Intent(Vehicle.this, SetTime.class);
        intent.putExtra("vehicle",-1);


        sm_car.setOnClickListener(e -> {
            sm_car.setBackgroundResource(R.drawable.sm_vehicle_active);
            md_car.setBackgroundResource(R.drawable.md_vehicle_not_active);
            lg_car.setBackgroundResource(R.drawable.lg_vehicle_not_active);
            car_desc.setLayoutParams(layoutParams);
            car_img.setImageResource(R.drawable.small_vehicle);
            sm_car_sx.setLayoutParams(invisibleLayout);
            md_car_sx.setLayoutParams(invisibleLayout);
            lg_car_sx.setLayoutParams(invisibleLayout);
            car_discount_btn.setVisibility(View.VISIBLE);
            car_nav_txt.setText("Pickup Truck");
            car_discount.setText("Save 35%  ");
            car_info.setText("Save by getting a single Lugger\n"+
                    "Be ready to help if its too heavy.");
            car_labour.setText("Get 1 Lugger");
            car_price.setText("$58 + $1.62 per labor min");
            car_price.setTextSize(24);
            intent.putExtra("vehicle",R.drawable.small_vehicle);
            intent.putExtra("price","$58 + $1.62 per labor min" );


        });

        md_car.setOnClickListener(e -> {
            md_car.setBackgroundResource(R.drawable.md_vehicle_active);
            sm_car.setBackgroundResource(R.drawable.sm_vehicle_not_active);
            lg_car.setBackgroundResource(R.drawable.lg_vehicle_not_active);
            car_desc.setLayoutParams(layoutParams);
            car_img.setImageResource(R.drawable.medium_vehicle);
            sm_car_sx.setLayoutParams(invisibleLayout);
            md_car_sx.setLayoutParams(invisibleLayout);
            lg_car_sx.setLayoutParams(invisibleLayout);
            car_discount_btn.setVisibility(View.INVISIBLE);
            car_nav_txt.setText("Cargo Van");
            car_discount.setText("");
            car_info.setText("Sit back & relax while 2 Luggers do all\n"+
                    "the heavy work for you.");
            car_labour.setText("Comes with 2 Luggers");
            car_price.setText("$95 + $2.02 per labor min");
            car_price.setTextSize(24);
            intent.putExtra("vehicle",R.drawable.medium_vehicle);
            intent.putExtra("price","$95 + $2.02 per labor min" );
        });

        lg_car.setOnClickListener(e -> {
            lg_car.setBackgroundResource(R.drawable.lg_vehicle_active);
            md_car.setBackgroundResource(R.drawable.md_vehicle_not_active);
            sm_car.setBackgroundResource(R.drawable.sm_vehicle_not_active);
            car_desc.setLayoutParams(layoutParams);
            car_img.setImageResource(R.drawable.large_vehicle);
            sm_car_sx.setLayoutParams(invisibleLayout);
            md_car_sx.setLayoutParams(invisibleLayout);
            lg_car_sx.setLayoutParams(invisibleLayout);
            car_discount_btn.setVisibility(View.INVISIBLE);
            car_nav_txt.setText("Sprinter Van");
            car_discount.setText("");
            car_info.setText("Sit back & relax while 2 Luggers do all\n"+
                    "the heavy work for you.");
            car_labour.setText("Comes with 2 Luggers");
            car_price.setText("$349.4 + $2.76 per labor min");
            car_price.setTextSize(24);
            intent.putExtra("vehicle",R.drawable.large_vehicle);
            intent.putExtra("price","$349.4 + $2.76 per labor min");
        });

        next_btn.setOnClickListener( e -> proceedToNextActivity());
        vehicleContinue.setOnClickListener(e -> proceedToNextActivity());
        vehicle_back.setOnClickListener(e -> {
            Intent itn2 = new Intent(Vehicle.this, Location_Select.class);
            if(getIntent().getExtras() != null){
                itn2.putExtras(getIntent().getExtras());
            }
            startActivity(itn2);
        });


    }

    private  void proceedToNextActivity(){
        if(intent.getExtras().getInt("vehicle") != -1){
            if(getIntent().getExtras() != null){
                intent.putExtras(getIntent().getExtras());
            }

            startActivity(intent);
        }
        else{
            Toast.makeText(Vehicle.this,"Select Vehicle",Toast.LENGTH_LONG).show();
        }
    }
}