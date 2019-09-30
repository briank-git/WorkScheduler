package ui;

import model.Employee;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface Saveable {

    //EFFECTS: saves employee data from a list to outputfile.txt
    void save(ArrayList<Employee> input) throws FileNotFoundException, UnsupportedEncodingException;
}
