package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Item_Details extends AppCompatActivity {

    Button continue_btn;
    TextView next_btn;

    ImageView back_btn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        continue_btn = findViewById(R.id.item_details_continue);
        next_btn = findViewById(R.id.item_details_next);
        back_btn = findViewById(R.id.item_details_back_arrow);

        intent = new Intent(Item_Details.this, Confirmation.class);

        intent.putExtra("date",getIntent().getExtras().getString("date"));
        intent.putExtra("time",getIntent().getExtras().getString("time"));
        intent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
        intent.putExtra("price",getIntent().getExtras().getString("price"));
        intent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
        intent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
        intent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
        intent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));

        continue_btn.setOnClickListener(e -> startActivity(intent));
        next_btn.setOnClickListener(e -> startActivity(intent));
        back_btn.setOnClickListener(e -> {
            Intent timeIntent = new Intent(Item_Details.this, SetTime.class);
            timeIntent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
            timeIntent.putExtra("price",getIntent().getExtras().getString("price"));
            timeIntent.putExtra("date",getIntent().getExtras().getString("date"));
            timeIntent.putExtra("time",getIntent().getExtras().getString("time"));
            timeIntent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
            timeIntent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
            timeIntent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
            timeIntent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
            startActivity(timeIntent);
        });


    }
}