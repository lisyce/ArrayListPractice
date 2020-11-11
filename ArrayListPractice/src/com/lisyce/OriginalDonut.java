package com.lisyce;

import com.lisyce.toppings.Topping;

import java.util.ArrayList;

public class OriginalDonut extends Donut{
    public OriginalDonut(ArrayList<Topping> toppings) {
        super("Original Donut", 2.00, "The classic donut", toppings);
    }
}
