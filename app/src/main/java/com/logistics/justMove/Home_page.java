package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Home_page extends AppCompatActivity {

    Button home_pickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        home_pickup = findViewById(R.id.home_pickup);

        Intent intent = new Intent(Home_page.this, locationSearch.class);
        intent.putExtra("change","pickup");

        home_pickup.setOnClickListener( e -> startActivity(intent));
    }
}