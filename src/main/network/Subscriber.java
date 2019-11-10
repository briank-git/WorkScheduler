package network;

import java.util.ArrayList;

public class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(ArrayList<Double> minMaxTemp) {
        String stateTemp =  " in Vancouver. Low of " + minMaxTemp.get(0).intValue()
                + ". High of " + minMaxTemp.get(1).intValue();

        if (minMaxTemp.get(1) < 15 & minMaxTemp.get(1) > 0) {
            System.out.println(name + " says it's chilly today" + stateTemp);
        } else if (minMaxTemp.get(1) > 15) {
            System.out.println(name + " says it's a nice day" + stateTemp);
        } else if (minMaxTemp.get(1) <= 0) {
            System.out.println(name + " says watch out for ice" + stateTemp);
        }
    }
}
