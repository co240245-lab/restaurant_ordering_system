package com.popsilog.popsilogorderingsystem.model;

public class RestaurantTable {
    private String code;
    private String name;
    private String status;

    public RestaurantTable(String code, String name, String status) {
        this.code = code;
        this.name = name;
        this.status = status;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}