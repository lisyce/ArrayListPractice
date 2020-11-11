package com.lisyce;

import com.lisyce.fillings.Filling;
import com.lisyce.toppings.Topping;

import java.util.ArrayList;

public class FilledDonut extends Donut{
    private Filling filling;
    public FilledDonut(ArrayList<Topping> toppings, Filling filling) {
        super("Filled Donut", 2.50, "Filled with delicious cream or jam", toppings);
        this.filling = filling;
    }

    public void addFilling(Filling filling) {
        this.filling = filling;
        super.setPrice(super.getPrice() + filling.getPrice());
    }

}
