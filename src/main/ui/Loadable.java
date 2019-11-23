package ui;

import exceptions.NegativeInputException;
import model.Employee;
import model.RegularEmployee;
import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {

    //EFFECTS: loads information from a outputfile.txt and returns a list of employees created from that information
    ArrayList<Employee> load() throws IOException, NegativeInputException;
}
