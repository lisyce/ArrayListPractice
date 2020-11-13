package com.donuts;

public class Product {
    private final String name;
    private double price;
    private final String imageSrc;

    public Product(String name, double price, String imageSrc) {
        this.name = name;
        this.price = price;
        this.imageSrc = imageSrc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImageSrc() {
        return imageSrc;
    }

}
