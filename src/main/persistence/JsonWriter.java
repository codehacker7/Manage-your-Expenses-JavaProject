package persistence;

import model.Customer;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/*
This class is used to write the data to a json file
 */
public class JsonWriter {

    private static final int TAB = 4;
    private String destination;
    PrintWriter writer;

    //EFFECTS : SETS THE PRINTWRITER TO WRITE IN A FILE
    public JsonWriter(String destination) throws FileNotFoundException {
        this.destination = destination;
    }

    //EFFECTS : INTIALIZIES THE PRINTWRITER
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));

    }

    //EFFECTS : WRITES THE DATA TO JSON FILE
    public void write(JSONObject c3, Customer c, int id) throws IOException {
        JSONObject json1 = c.toJson(c3, id);
        saveToFile(json1.toString(TAB));
    }

    //EFFECTS : This is used when the file is empty
    public void emptyFileWrite(Customer c, int id) { //this is for empty file writing
        JSONObject json = c.emptyvalue(c, id);
        saveToFile(json.toString(TAB));
    }

    //EFFECTS : This is used to finally print everything to json file
    public void saveToFile(String json) {
        writer.print(json);
    }

    //EFFECTS : This method closes printwriter
    public void close() {
        writer.close();

    }


}
