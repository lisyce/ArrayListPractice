package com.donuts;

import com.donuts.fillings.Filling;
import com.donuts.toppings.Topping;

import java.util.ArrayList;

public  class Donut extends Product {
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

    public void addFilling(Filling filling) {

    }

}
