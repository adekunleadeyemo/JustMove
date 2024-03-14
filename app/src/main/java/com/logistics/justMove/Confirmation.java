package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
    ImageView back_btn;
    Button continue_btn;
    TextView pk_addr_1;
    TextView pk_addr_2;
    TextView dl_addr_1;
    TextView dl_addr_2;
    TextView confirm_next;
    TextView paymentCard;

    ConstraintLayout confirm_pickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        goToPayments = findViewById(R.id.go_to_payments);
        vehicle_img = findViewById(R.id.confirm_vehicle_img);
        confirm_date = findViewById(R.id.confirm_date);
        confirm_price = findViewById(R.id.confirm_price);
        confirm_time = findViewById(R.id.confirm_time);
        pk_addr_1 = findViewById(R.id.confirm_pickup_addr_1);
        pk_addr_2 = findViewById(R.id.confirm_pickup_addr_2);
        dl_addr_1 = findViewById(R.id.confirm_delivery_addr_1);
        dl_addr_2 = findViewById(R.id.confirm_delivery_addr_2);
        continue_btn = findViewById(R.id.confirm_continue);
        back_btn = findViewById(R.id.confirm_back_arrow);
        paymentCard = findViewById(R.id.payment_card);
        confirm_next = findViewById(R.id.confirm_nav_next);
        confirm_pickup = findViewById(R.id.confirm_pickup_addr_container);


        if(getIntent().getExtras() != null && getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()){
                paymentCard.setText(getIntent().getExtras().getString("selectedCard"));
        }

        vehicle_img.setImageResource(getIntent().getExtras().getInt("vehicle"));
        confirm_price.setText(getIntent().getExtras().getString("price"));
        confirm_date.setText(getIntent().getExtras().getString("date"));
        confirm_time.setText(getIntent().getExtras().getString("time"));

        pk_addr_1.setText(getIntent().getExtras().getString("pk_addr_1"));
        pk_addr_2.setText(getIntent().getExtras().getString("pk_addr_2"));
        dl_addr_1.setText(getIntent().getExtras().getString("dl_addr_1"));
        dl_addr_2.setText(getIntent().getExtras().getString("dl_addr_2"));

        goToPayments.setOnClickListener(e -> {
            Intent paymentCardIntent = new Intent(Confirmation.this, Payment.class);
            paymentCardIntent.putExtras(getIntent().getExtras());
            startActivity(paymentCardIntent);
        });
        
        back_btn.setOnClickListener(e -> {
            Intent itemDetailsIntent = new Intent(Confirmation.this, Item_Details.class);
            itemDetailsIntent.putExtras(getIntent().getExtras());
            startActivity(itemDetailsIntent);
        });

        continue_btn.setOnClickListener(e -> {
            if(getIntent().getExtras().getString("selectedCard") !=null && !getIntent().getExtras().getString("selectedCard").isEmpty()){
                Intent completedIntent = new Intent(Confirmation.this, Completed.class);
                completedIntent.putExtras(getIntent().getExtras());
                startActivity(completedIntent);
            }

        });

        confirm_next.setOnClickListener(e -> {
            if(getIntent().getExtras().getString("selectedCard") !=null && !getIntent().getExtras().getString("selectedCard").isEmpty()){
                Intent completedIntent = new Intent(Confirmation.this, Completed.class);
                completedIntent.putExtras(getIntent().getExtras());
                startActivity(completedIntent);
            }
        });
    }
}