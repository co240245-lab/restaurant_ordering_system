package com.popsilog.popsilogorderingsystem.model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 1;
    private static final DecimalFormat orderIdFormat = new DecimalFormat("0000");

    private String orderId;
    private String tableNumber;
    private String orderType;
    private List<OrderItem> items;
    private LocalDateTime orderTime;
    private boolean isSeniorDiscount;

    public Order(String tableNumber, String orderType) {
        this.orderId = "Order #" + orderIdFormat.format(orderCounter++);
        this.tableNumber = tableNumber;
        this.orderType = orderType;
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
        this.isSeniorDiscount = false;
    }

    public void addItem(OrderItem item) { items.add(item); }
    public void clearOrder() { items.clear(); }

    public double calculateRawSubtotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double getVatableSales() {
        return calculateRawSubtotal() / 1.12;
    }

    public double getVatAmount() {
        if (isSeniorDiscount) return 0.00;
        return calculateRawSubtotal() - getVatableSales();
    }

    public double getSeniorDiscountAmount() {
        if (isSeniorDiscount) return getVatableSales() * 0.20;
        return 0.00;
    }

    public double calculateFinalTotal() {
        if (isSeniorDiscount) return getVatableSales() - getSeniorDiscountAmount();
        return calculateRawSubtotal();
    }

    public List<OrderItem> getItems() { return items; }
    public String getOrderId() { return orderId; }
    public String getTableNumber() { return tableNumber; }
    public void setTableNumber(String tableNumber) { this.tableNumber = tableNumber; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public boolean isSeniorDiscount() { return isSeniorDiscount; }
    public void setSeniorDiscount(boolean seniorDiscount) { isSeniorDiscount = seniorDiscount; }
}