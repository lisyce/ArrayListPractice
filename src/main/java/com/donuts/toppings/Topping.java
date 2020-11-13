package com.donuts.toppings;
import com.donuts.Product;

public class Topping extends Product {
    private String name;
    private double price;

    public Topping(String name, double price, String imageSrc) {
        super(name, price, imageSrc);
    }

}
