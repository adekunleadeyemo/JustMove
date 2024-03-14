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
        intent.putExtras(getIntent().getExtras());
        continue_btn.setOnClickListener(e -> startActivity(intent));
        next_btn.setOnClickListener(e -> startActivity(intent));
        back_btn.setOnClickListener(e -> {
            Intent timeIntent = new Intent(Item_Details.this, SetTime.class);
            timeIntent.putExtras(getIntent().getExtras());
            startActivity(timeIntent);
        });


    }
}