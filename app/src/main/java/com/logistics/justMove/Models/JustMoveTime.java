package com.logistics.justMove.Models;

public class JustMoveTime {

    public JustMoveTime(String label, String value, int background) {
        this.label = label;
        this.value = value;
        this.background = background;
    }

    private String label;
    private  String value;
    private int background;

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public int getBackground() {
        return background;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
