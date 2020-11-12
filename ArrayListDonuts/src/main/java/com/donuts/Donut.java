package com.donuts;

import com.donuts.toppings.Topping;

import java.util.ArrayList;

public class Donut extends Product {
    private ArrayList<Topping> toppings;

    public Donut(String name, double price, String imageSrc) {
        super(name, price, imageSrc);
    }

    public Donut(String name, double price, String imageSrc, ArrayList<Topping> toppings) {
        this(name, price, imageSrc);
        this.toppings = toppings;
    }



    public void addTopping(Topping topping) {
        toppings.add(topping);
        super.setPrice(super.getPrice() + topping.getPrice());
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
        super.setPrice(super.getPrice() - topping.getPrice());
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }
}
