package com.lisyce;
import com.lisyce.toppings.Topping;

import java.util.ArrayList;

public class Donut {
    private String name;
    private double price;
    private String description;
    private ArrayList<Topping> toppings;

    public Donut(String name, double price, String description, ArrayList<Topping> toppings) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.toppings = toppings;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
        this.price += topping.getPrice();
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
        this.price -= topping.getPrice();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
