package com.popsilog.popsilogorderingsystem.model;

public class InventoryItem {
    private String name;
    private String category;
    private double inStock;
    private double minThreshold;
    private String unit;
    private String status;

    public InventoryItem(String name, String category, double inStock, double minThreshold, String unit, String status) {
        this.name = name;
        this.category = category;
        this.inStock = inStock;
        this.minThreshold = minThreshold;
        this.unit = unit;
        this.status = status;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getInStock() { return inStock; }
    public void setInStock(double inStock) { this.inStock = inStock; }
    public double getMinThreshold() { return minThreshold; }
    public String getUnit() { return unit; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}