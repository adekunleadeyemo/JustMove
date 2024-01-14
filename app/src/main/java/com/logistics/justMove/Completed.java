package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Completed extends AppCompatActivity {

    TextView pk_addr_1;
    TextView pk_addr_2;
    ImageView vehicle_img;
    TextView complete_date;
    TextView complete_time;

    Button complete_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);

        vehicle_img = findViewById(R.id.complete_vehicle_img);
        complete_date = findViewById(R.id.complete_date);
        complete_time = findViewById(R.id.complete_time);
        pk_addr_1 = findViewById(R.id.complete_pickup_addr_1);
        pk_addr_2 = findViewById(R.id.complete_pickup_addr_2);
        complete_btn = findViewById(R.id.complete_continue);

        vehicle_img.setImageResource(getIntent().getExtras().getInt("vehicle"));
        complete_date.setText(getIntent().getExtras().getString("date"));
        complete_time.setText(getIntent().getExtras().getString("time"));
        pk_addr_1.setText(getIntent().getExtras().getString("pk_addr_1"));
        pk_addr_2.setText(getIntent().getExtras().getString("pk_addr_2"));

        complete_btn.setOnClickListener(e -> startActivity(new Intent(Completed.this, Location.class).putExtra("change", "pickup")));


    }
}