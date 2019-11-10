package network;

import java.util.ArrayList;

public interface Observer {
    void update(ArrayList<Double> minMaxTemp);
}
