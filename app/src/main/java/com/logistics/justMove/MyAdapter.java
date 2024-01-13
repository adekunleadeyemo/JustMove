package com.logistics.justMove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.logistics.justMove.Models.JustMoveTime;
import com.logistics.justMove.Models.PaymentCardModel;
import com.logistics.justMove.Utils.RecyclerViewInterface;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {

    Context context;
    List<JustMoveTime> myDates;
    List<PaymentCardModel> cards;
    String type;

    public void setMyDates(List<JustMoveTime> myDates) {
        this.myDates = myDates;
    }

    public void setCards(List<PaymentCardModel> cards) {
        this.cards = cards;
    }

    public void setType(String type) {
        this.type = type;
    }

    private final RecyclerViewInterface recyclerViewInterface;
    public MyAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (type){
            case "payment":
                  v = LayoutInflater.from(context).inflate(R.layout.payment_card_view,parent,false);
                return new MyviewHolder(v,recyclerViewInterface, type);
            default:
                  v = LayoutInflater.from(context).inflate(R.layout.date_view,parent,false);
                return new MyviewHolder(v, recyclerViewInterface,type);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        switch (type){
            case "payment":
                holder.card_number.setText(cards.get(position).getCardNumber());
                holder.card_select.setImageResource(cards.get(position).getBackground());
                if (cards.get(position).isSelected()) {
                    holder.delete_card.setVisibility(View.INVISIBLE);
                } else {
                    holder.delete_card.setVisibility(View.VISIBLE);
                }
                break;
            default:
                holder.dateText.setText(myDates.get(position).getLabel());
                holder.dateText.setBackgroundResource(myDates.get(position).getBackground());
                break;
        }

    }
    @Override
    public int getItemCount() {
        switch (type) {
            case "payment":
                return cards.size();
            default:
                return myDates.size();
        }
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder {
    TextView dateText;
        ConstraintLayout card_layout;
        TextView card_number;
        ImageView card_select;
        ImageView delete_card;
        public MyviewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface, String type) {
            super(itemView);

            switch (type){
                case "payment":
                    card_layout = itemView.findViewById(R.id.payment_card_layout);
                    card_number = itemView.findViewById(R.id.payment_card);
                    card_select = itemView.findViewById(R.id.card_select);
                    delete_card = itemView.findViewById(R.id.delete_card);
                    card_layout.setOnClickListener(e -> {
                        if(recyclerViewInterface != null){
                            int pos = getAdapterPosition();
                            if(pos != RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(pos,"click");
                            }
                        }
                    });
                    delete_card.setOnClickListener(e -> {
                        if(recyclerViewInterface != null){
                            int pos = getAdapterPosition();
                            if(pos != RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(pos,"delete");
                            }
                        }
                    });
                    break;
                default:
                    dateText = itemView.findViewById(R.id.set_date);
                    itemView.setOnClickListener(e -> {
                        if(recyclerViewInterface != null){
                            int pos = getAdapterPosition();
                            if(pos != RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(pos,"click");
                            }
                        }
                    });
                    break;
            }

        }
    }
}
