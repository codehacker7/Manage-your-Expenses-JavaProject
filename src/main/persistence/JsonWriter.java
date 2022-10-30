package persistence;

import model.Customer;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class JsonWriter {

    private static final int TAB = 4;
    private String destination;
    PrintWriter writer;


    public JsonWriter(String destination) throws FileNotFoundException {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));

    }

    public void write(JSONObject c3, Customer c, int id) throws IOException {
        JSONObject json1 = c.toJson(c3, id);
        saveToFile(json1.toString(TAB));
    }

    public void emptyFileWrite(Customer c, int id) { //this is for empty file writing
        JSONObject json = c.emptyvalue(c,id);
        saveToFile(json.toString(TAB));
    }

    public void saveToFile(String json) {
        writer.print(json);
    }

    public void close() {
        writer.close();
        System.out.println("Done saving the data");
    }


}
