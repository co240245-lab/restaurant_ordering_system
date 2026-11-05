package com.popsilog.popsilogorderingsystem.model;

public class OrderItem {
    private FoodItem item;
    private int quantity;
    private String eggStyle;
    private String riceChoice;

    public OrderItem(FoodItem item, int quantity, String eggStyle, String riceChoice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.item = item;
        this.quantity = quantity;
        this.eggStyle = eggStyle;
        this.riceChoice = riceChoice;
    }

    public FoodItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getEggStyle() { return eggStyle; }
    public String getRiceChoice() { return riceChoice; }

    public double getSubtotal() {
        double extraRiceCost = 0.0;
        if ("Extra Rice".equals(riceChoice)) extraRiceCost = 20.0;
        return (item.getBasePrice() + extraRiceCost) * quantity;
    }
}