package com.lisyce.fillings;

public class Filling {
    private String name;
    private double price;

    public Filling(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
