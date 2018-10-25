package com.netcracker.alexa.controlpanel.vaadin.model;

public class StockItem {
    private String name;

    private String price;

    public StockItem(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public StockItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
