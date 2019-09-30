package ui;

import model.Employee;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Loadable {

    //EFFECTS: loads information from a outputfile.txt and returns a list of employees created from that information
    ArrayList<Employee> load() throws IOException;
}
