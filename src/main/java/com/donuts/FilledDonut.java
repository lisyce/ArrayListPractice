package com.donuts;

import com.donuts.fillings.Filling;

public class FilledDonut extends Donut{
    private Filling filling;

    public FilledDonut() {
        super("Filled Donut", 2.50,"src/main/images/filledDonut.png");

    }

    @Override
    public void addFilling(Filling filling) {
        this.filling = filling;
        super.setPrice(super.getPrice() + filling.getPrice());
        System.out.println("new price of donut after filling: " + super.getPrice());
    }


}
