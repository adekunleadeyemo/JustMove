package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.logistics.justMove.Models.PaymentCardModel;
import com.logistics.justMove.Utils.RecyclerViewInterface;


import java.util.ArrayList;
import java.util.List;

public class Payment extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView3;
    List<PaymentCardModel> cards;

    ArrayList<String> cardNumbers;
    MyAdapter myAdapter;

    TextView add_card;
    Intent confirmationIntent;
    Intent paymentCardIntent;
    
    Button payment_continue;

    String selectedCard;

    ImageView back_btn;

    int prevPos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        dataInitialize();
        add_card = findViewById(R.id.add_payment_card);
        recyclerView3 = findViewById(R.id.recylerView3);
        payment_continue = findViewById(R.id.payment_continue);
        back_btn = findViewById(R.id.payment_back_arrow);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView3.setHasFixedSize(true);
        myAdapter = new MyAdapter(getApplicationContext(), this);
        myAdapter.setCards(cards);
        myAdapter.setType("payment");
        recyclerView3.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        paymentCardIntent = new Intent(Payment.this, PaymentCard.class);
        confirmationIntent = new Intent(Payment.this, Confirmation.class);
        
        payment_continue.setOnClickListener(e -> {
            confirmationIntent.putExtras(getIntent().getExtras());
            if(getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null){
                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()){
                    confirmationIntent.putExtra("selectedCard",selectedCard);
                    confirmationIntent.putExtra("pos",prevPos);
                }
                confirmationIntent.putStringArrayListExtra("cards",cardNumbers);
            }
            startActivity(confirmationIntent);
        });

        add_card.setOnClickListener(e -> {
            paymentCardIntent.putExtras(getIntent().getExtras());
            if(getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null){
                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()){
                    paymentCardIntent.putExtra("selectedCard",selectedCard);
                    paymentCardIntent.putExtra("pos",prevPos);
                }
                paymentCardIntent.putStringArrayListExtra("cards",cardNumbers);
            }
            startActivity(paymentCardIntent);
            
        });

        back_btn.setOnClickListener(e -> {
            confirmationIntent.putExtras(getIntent().getExtras());
            if(getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null){
                if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()){
                    confirmationIntent.putExtra("selectedCard",selectedCard);
                    confirmationIntent.putExtra("pos",prevPos);
                }
                confirmationIntent.putStringArrayListExtra("cards",cardNumbers);
            }
            startActivity(confirmationIntent);
        });


    }

    private void dataInitialize(){
        cards = new ArrayList<>();
        cardNumbers = new ArrayList<>();
        
        if(getIntent().getExtras() != null && getIntent().getExtras().getStringArrayList("cards") != null){
            cardNumbers.addAll(getIntent().getExtras().getStringArrayList("cards"));
            if(getIntent().getExtras().getString("selectedCard") != null && !getIntent().getExtras().getString("selectedCard").isEmpty()) {
                selectedCard = getIntent().getExtras().getString("selectedCard");
                prevPos = getIntent().getExtras().getInt("pos");
            }
           for (String i : cardNumbers){
                if(i.equals(selectedCard)){
                    cards.add(new PaymentCardModel(selectedCard, true, false, R.drawable.filled_cirlcle));
                }
                else{
                    cards.add(new PaymentCardModel(i, false,false,R.drawable.outline_circle));
                }

           }
        }
    }

    @Override
    public void onItemClick(int position, String event) {

        if(prevPos != position && event.equals("click")){
            cards.get(position).setBackground(R.drawable.filled_cirlcle);
            cards.get(position).setSelected(true);
            if(prevPos != -1) {
                cards.get(prevPos).setBackground(R.drawable.outline_circle);
                cards.get(prevPos).setSelected(false);
            }
            prevPos = position;
            selectedCard = cards.get(position).getCardNumber();
            myAdapter.notifyDataSetChanged();

        }
        else if (event.equals("delete")){
            cards.remove(position);
            cardNumbers.remove(position);
            if(prevPos -1  == position){
                prevPos = position;
            }
            myAdapter.notifyDataSetChanged();
        }


    }
}