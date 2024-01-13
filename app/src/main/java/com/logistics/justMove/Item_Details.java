package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Item_Details extends AppCompatActivity {

    Button continue_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        continue_btn = findViewById(R.id.item_details_continue);

        continue_btn.setOnClickListener(e -> startActivity(new Intent(Item_Details.this, Confirmation.class)));


    }
}