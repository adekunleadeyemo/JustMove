package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PaymentCard extends AppCompatActivity {

    EditText cardNumber;
    EditText cardMonth;
    EditText cardYear;
    Button addCard;
    ImageView back_btn;
    TextView save_btn;
    EditText cvc_txt;
    EditText cardName;

    EditText cardZip;
    ArrayList<String> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_card);

        Intent payIntent = new Intent(PaymentCard.this, Payment.class);
        cardNumber = findViewById(R.id.card_number);
        cardMonth = findViewById(R.id.card_exp_month);
        cardYear = findViewById(R.id.card_exp_year);
        addCard = findViewById(R.id.payment_card_add);
        back_btn = findViewById(R.id.payment_card_back);
        save_btn = findViewById(R.id.payment_card_save);
        cvc_txt = findViewById(R.id.card_exp_cvc);
        cardName = findViewById(R.id.card_name);
        cards = new ArrayList<>();
        cardZip = findViewById(R.id.card_zip);

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
            payIntent.putExtras(getIntent().getExtras());
            if( getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null && getIntent().getExtras().getStringArrayList("cards").size()>0){
               cards.addAll(getIntent().getExtras().getStringArrayList("cards"));
               cards.add("****" + cardNumber.getText().toString().substring(12));
                payIntent.putStringArrayListExtra("cards",cards);

                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()) {
                    payIntent.putExtra("selectedCard",getIntent().getExtras().getString("selectedCard"));
                    payIntent.putExtra("pos",getIntent().getExtras().getInt("pos"));
                }

            }
            else{
                ArrayList<String> newCard = new ArrayList<>();
                newCard.add("****" + cardNumber.getText().toString().substring(12));
                payIntent.putStringArrayListExtra("cards",newCard);
                payIntent.putExtra("selectedCard","****" + cardNumber.getText().toString().substring(12));
                payIntent.putExtra("pos",0);
            }
            startActivity(payIntent);

        });

        save_btn.setOnClickListener(e -> {
            payIntent.putExtras(getIntent().getExtras());
            if( getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null && getIntent().getExtras().getStringArrayList("cards").size()>0){
                cards.addAll(getIntent().getExtras().getStringArrayList("cards"));
                cards.add("****" + cardNumber.getText().toString().substring(12));
                payIntent.putStringArrayListExtra("cards",cards);

                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()) {
                    payIntent.putExtra("selectedCard",getIntent().getExtras().getString("selectedCard"));
                    payIntent.putExtra("pos",getIntent().getExtras().getInt("pos"));
                }

            }
            else{
                ArrayList<String> newCard = new ArrayList<>();
                newCard.add("****" + cardNumber.getText().toString().substring(12));
                payIntent.putStringArrayListExtra("cards",newCard);
                payIntent.putExtra("selectedCard","****" + cardNumber.getText().toString().substring(12));
                payIntent.putExtra("pos",0);
            }
            startActivity(payIntent);
        });

        setInputStyle(cardYear);
        setInputStyle(cardMonth);
        setInputStyle(cardNumber);
        setInputStyle(cvc_txt);
        setInputStyle(cardName);
        setInputStyle(cardZip);
    }

    private  void setInputStyle(EditText ed){

        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed.getId() == R.id.card_name){
                    if(s.length() > 0){
                        ed.setLetterSpacing(0.04F);
                        ed.setTextSize(18);
                    }else {
                        ed.setLetterSpacing(0);
                        ed.setTextSize(14);
                    }
                }
                else{
                    if(s.length() > 0){
                        ed.setLetterSpacing(0.15F);
                        ed.setTextSize(18);
                    }else {
                        ed.setLetterSpacing(0);
                        ed.setTextSize(14);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}