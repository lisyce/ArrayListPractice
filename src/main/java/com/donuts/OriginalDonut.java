package com.donuts;

import com.donuts.fillings.Filling;

public class OriginalDonut extends Donut{

    public OriginalDonut() {
        super("Original Donut", 2.00, "src/main/images/plainDonut.png");
    }


    @Override
    public void addFilling(Filling filling) {
        System.out.println("cannot add filling to original donut");
    }
}
