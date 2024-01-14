package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PaymentCard extends AppCompatActivity {

    EditText cardNumber;
    Button addCard;
    ImageView back_btn;
    TextView save_btn;

    ArrayList<String> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_card);

        Intent payIntent = new Intent(PaymentCard.this, Payment.class);
        cardNumber = findViewById(R.id.card_number);
        addCard = findViewById(R.id.payment_card_add);
        back_btn = findViewById(R.id.payment_card_back);
        save_btn = findViewById(R.id.payment_card_save);
        cards = new ArrayList<>();

        back_btn.setOnClickListener(e -> {
            if( getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null && getIntent().getExtras().getStringArrayList("cards").size()>0){
                cards.addAll(getIntent().getExtras().getStringArrayList("cards"));
                payIntent.putStringArrayListExtra("cards",cards);

                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()) {
                    payIntent.putExtra("selectedCard",getIntent().getExtras().getString("selectedCard"));
                    payIntent.putExtra("pos",getIntent().getExtras().getInt("pos"));
                }

            }
            payIntent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
            payIntent.putExtra("price",getIntent().getExtras().getString("price"));
            payIntent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
            payIntent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
            payIntent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
            payIntent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
            startActivity(payIntent);

        });

        addCard.setOnClickListener( e -> {
            if( getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null && getIntent().getExtras().getStringArrayList("cards").size()>0){
               cards.addAll(getIntent().getExtras().getStringArrayList("cards"));
               cards.add(cardNumber.getText().toString());
                payIntent.putStringArrayListExtra("cards",cards);

                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()) {
                    payIntent.putExtra("selectedCard",getIntent().getExtras().getString("selectedCard"));
                    payIntent.putExtra("pos",getIntent().getExtras().getInt("pos"));
                }

            }
            else{
                ArrayList<String> newCard = new ArrayList<>();
                newCard.add(cardNumber.getText().toString());
                payIntent.putStringArrayListExtra("cards",newCard);
                payIntent.putExtra("selectedCard",cardNumber.getText().toString());
                payIntent.putExtra("pos",0);
            }
            payIntent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
            payIntent.putExtra("price",getIntent().getExtras().getString("price"));
            payIntent.putExtra("date",getIntent().getExtras().getString("date"));
            payIntent.putExtra("time",getIntent().getExtras().getString("time"));
            payIntent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
            payIntent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
            payIntent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
            payIntent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
            startActivity(payIntent);

        });

        save_btn.setOnClickListener(e -> {
            if( getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null && getIntent().getExtras().getStringArrayList("cards").size()>0){
                cards.addAll(getIntent().getExtras().getStringArrayList("cards"));
                cards.add(cardNumber.getText().toString());
                payIntent.putStringArrayListExtra("cards",cards);

                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()) {
                    payIntent.putExtra("selectedCard",getIntent().getExtras().getString("selectedCard"));
                    payIntent.putExtra("pos",getIntent().getExtras().getInt("pos"));
                }

            }
            else{
                ArrayList<String> newCard = new ArrayList<>();
                newCard.add(cardNumber.getText().toString());
                payIntent.putStringArrayListExtra("cards",newCard);
                payIntent.putExtra("selectedCard",cardNumber.getText().toString());
                payIntent.putExtra("pos",0);
            }
            payIntent.putExtra("vehicle",getIntent().getExtras().getInt("vehicle"));
            payIntent.putExtra("price",getIntent().getExtras().getString("price"));
            payIntent.putExtra("date",getIntent().getExtras().getString("date"));
            payIntent.putExtra("time",getIntent().getExtras().getString("time"));
            payIntent.putExtra("pk_addr_1", getIntent().getExtras().getString("pk_addr_1"));
            payIntent.putExtra("pk_addr_2", getIntent().getExtras().getString("pk_addr_2"));
            payIntent.putExtra("dl_addr_1", getIntent().getExtras().getString("dl_addr_1"));
            payIntent.putExtra("dl_addr_2", getIntent().getExtras().getString("dl_addr_2"));
            startActivity(payIntent);
        });
    }
}