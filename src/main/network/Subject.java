package network;

import java.util.ArrayList;

public abstract class Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    void notifyObservers(ArrayList<Double> minMaxTemp) {
        for (Observer o : observers) {
            o.update(minMaxTemp);
        }
    }
}
