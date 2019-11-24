package ui;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Saveable {

    //EFFECTS: saves employee data from a list to outputfile.txt
    void save() throws FileNotFoundException, UnsupportedEncodingException;
}
