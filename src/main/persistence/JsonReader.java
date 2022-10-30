package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class JsonReader {

    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public Customer read(int id) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCustomer(jsonObject, id);
    }

    public JSONObject read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseCustomer(jsonObject);
    }


    private JSONObject parseCustomer(JSONObject jsonObject) {
        JSONArray customer = jsonObject.getJSONArray("customer");
        JSONObject newJson = new JSONObject();

        try {
            JSONArray check = newJson.getJSONArray("customer");
        } catch (JSONException c) {
            newJson.put("customer",customer);
        }
        return  newJson;
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Customer parseCustomer(JSONObject jsonObject, int id) {

        JSONArray customer = jsonObject.getJSONArray("customer");
        Customer addedcustomer = null;

        for (Object c : customer) {

            JSONObject jsonobject = (JSONObject) c;
            String name = jsonobject.getString("name");
            int customerid = jsonobject.getInt("id");

            if ((int) jsonobject.get("id") == id) {
                addedcustomer = new Customer(name, id);
                JSONArray expensesarray = jsonobject.getJSONArray("expenses");
                addExpenses(expensesarray, addedcustomer);
            }
        }



        if (addedcustomer == null) {
            return addedcustomer = new Customer("No details found", -1);
        }

        return addedcustomer;
    }

    private void addExpenses(JSONArray expensesarray, Customer addedcustomer) {

        Expenses expenses = new Expenses();

        for (Object c : expensesarray) {
            JSONObject jsonobject = (JSONObject) c;
            String expensename = jsonobject.getString("expensename");
            String date = jsonobject.getString("date");
            String category = jsonobject.getString("category");
            double price = jsonobject.getInt("price");

            expenses.addExpenses(expensename, price, date, category);

        }
        addedcustomer.addreadCustomerExpense(expenses);
    }


}





