package com.logistics.justMove.Models;

public class PaymentCardModel {


    public PaymentCardModel(String cardNumber, boolean selected, boolean deleted, int background) {
        this.cardNumber = cardNumber;
        this.selected = selected;
        this.deleted = deleted;
        this.background = background;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    private String cardNumber;
    private boolean selected;
    private boolean deleted;

    private int background;


}
