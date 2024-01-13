package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.logistics.justMove.Models.PaymentCardModel;
import com.logistics.justMove.Utils.RecyclerViewInterface;


import java.util.ArrayList;
import java.util.List;

public class Payment extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView3;
    List<PaymentCardModel> cards;
    MyAdapter myAdapter;

    TextView add_card;

    int prevPos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        dataInitialize();
        add_card = findViewById(R.id.add_payment_card);
        recyclerView3 = findViewById(R.id.recylerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView3.setHasFixedSize(true);
        myAdapter = new MyAdapter(getApplicationContext(), this);
        myAdapter.setCards(cards);
        myAdapter.setType("payment");
        recyclerView3.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        add_card.setOnClickListener(e -> startActivity(new Intent(Payment.this, PaymentCard.class)));
    }

    private void dataInitialize(){
        cards = new ArrayList<>();
        cards.add(new PaymentCardModel("****3587",false,false,R.drawable.outline_circle));
        cards.add(new PaymentCardModel("****02020",false,false,R.drawable.outline_circle));
        cards.add(new PaymentCardModel("****1111",false,false,R.drawable.outline_circle));

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
            myAdapter.notifyDataSetChanged();

        }
        else if (event.equals("delete")){
            cards.remove(position);
            if(prevPos -1  == position){
                prevPos = position;
            }
            myAdapter.notifyDataSetChanged();
        }


    }
}