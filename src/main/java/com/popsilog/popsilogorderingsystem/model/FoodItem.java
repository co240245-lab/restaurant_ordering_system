package com.popsilog.popsilogorderingsystem.model;

public class FoodItem {
    private String id;
    private String name;
    private String description;
    private double basePrice;
    private String category;
    private String imageFileName;
    private String badge;
    private boolean isAvailable;

    public FoodItem(String id, String name, String description, double basePrice, 
                    String category, String imageFileName, String badge, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.category = category;
        this.imageFileName = imageFileName;
        this.badge = badge;
        this.isAvailable = isAvailable;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getBasePrice() { return basePrice; }
    public String getCategory() { return category; }
    public String getImageFileName() { return imageFileName; }
    public String getBadge() { return badge; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}