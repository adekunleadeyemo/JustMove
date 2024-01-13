package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Confirmation extends AppCompatActivity {

    LinearLayout goToPayments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        goToPayments = findViewById(R.id.go_to_payments);

        goToPayments.setOnClickListener(e -> startActivity(new Intent(Confirmation.this, Payment.class)));
    }
}