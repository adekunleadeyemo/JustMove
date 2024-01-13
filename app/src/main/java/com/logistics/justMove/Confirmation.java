package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Confirmation extends AppCompatActivity {
    ImageView vehicle_img;
    TextView confirm_date;
    TextView confirm_time;
    TextView confirm_price;
    LinearLayout goToPayments;
    
    TextView next_btn;
    ImageView back_btn;
    Button continue_btn;

    TextView pk_addr_1;
    TextView pk_addr_2;
    TextView dl_addr_1;
    TextView dl_addr_2;

    List<String> cards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        cards = new ArrayList<>();


        goToPayments = findViewById(R.id.go_to_payments);
        vehicle_img = findViewById(R.id.confirm_vehicle_img);
        confirm_date = findViewById(R.id.confirm_date);
        confirm_price = findViewById(R.id.confirm_price);
        confirm_time = findViewById(R.id.confirm_time);
        pk_addr_1 = findViewById(R.id.confirm_pickup_addr_1);
        pk_addr_2 = findViewById(R.id.confirm_pickup_addr_2);
        dl_addr_1 = findViewById(R.id.confirm_delivery_addr_1);
        dl_addr_2 = findViewById(R.id.confirm_delivery_addr_2);
        next_btn = findViewById(R.id.confirm_nav_next);
        continue_btn = findViewById(R.id.confirm_continue);
        back_btn = findViewById(R.id.confirm_back_arrow);

        vehicle_img.setImageResource(getIntent().getExtras().getInt("vehicle"));
        confirm_price.setText(getIntent().getExtras().getString("price"));
        confirm_date.setText(getIntent().getExtras().getString("date"));
        confirm_time.setText(getIntent().getExtras().getString("time"));

        pk_addr_1.setText(getIntent().getExtras().getString("pk_addr_1"));
        pk_addr_2.setText(getIntent().getExtras().getString("pk_addr_2"));
        dl_addr_1.setText(getIntent().getExtras().getString("dl_addr_1"));
        dl_addr_2.setText(getIntent().getExtras().getString("dl_addr_2"));

        goToPayments.setOnClickListener(e -> {
            Intent paymentCardIntent = new Intent(Confirmation.this, Item_Details.class);
            paymentCardIntent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
            paymentCardIntent.putExtra("price",getIntent().getExtras().getString("price"));
            paymentCardIntent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
            paymentCardIntent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
            paymentCardIntent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
            paymentCardIntent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
            startActivity(paymentCardIntent);
        });
        
        back_btn.setOnClickListener(e -> {
            Intent itemDetailsIntent = new Intent(Confirmation.this, Item_Details.class);
            itemDetailsIntent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
            itemDetailsIntent.putExtra("price",getIntent().getExtras().getString("price"));
            itemDetailsIntent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
            itemDetailsIntent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
            itemDetailsIntent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
            itemDetailsIntent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
            startActivity(itemDetailsIntent);
        });
    }
}