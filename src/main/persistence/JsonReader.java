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

/*
This class is used to read data from json file
 */
public class JsonReader {

    private String source;

  //EFFECTS : This constructor is used to set the source of the file which you are reading from
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS : This method is used to read an users data with a particular id
    public Customer read(int id) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCustomer2(jsonObject, id);

    }

    //EFFECTS : This method is used to read the whole jsonfile and parse it to a json object and return it
    public JSONObject read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseCustomer(jsonObject);
    }

    //EFFECTS : This method assings a customer jsonarray to a new array
    private JSONObject parseCustomer(JSONObject jsonObject) {
        JSONArray customer = jsonObject.getJSONArray("customer");
        JSONObject newJson = new JSONObject();

        try {
            JSONArray check = newJson.getJSONArray("customer");
        } catch (JSONException c) {
            newJson.put("customer", customer);
        }
        return newJson;
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses Customer from JSON object and returns it
    private Customer parseCustomer2(JSONObject jsonObject, int id) {

        JSONArray customer = jsonObject.getJSONArray("customer");
        Customer addedcustomer = null;

        for (Object c : customer) {

            JSONObject jsonobject = (JSONObject) c;
            String name = jsonobject.getString("name");
            int customerid = jsonobject.getInt("id");

            if ((int) jsonobject.get("id") == id) {
                addedcustomer = new Customer(name, id);
                double expenselimit = jsonobject.getDouble("expenselimit"); //new edition

                JSONArray expensesarray = jsonobject.getJSONArray("expenses");
                addExpenses(expensesarray, addedcustomer,expenselimit);
            }
        }


        if (addedcustomer == null) {
            return addedcustomer = new Customer("No details found", -1);
        }

        return addedcustomer;
    }

    //EFFECTS : This method is used to add expensename , date ,category and price to the expenses array in json file
    private void addExpenses(JSONArray expensesarray, Customer addedcustomer,double expenselimit) {

        Expenses expenses = new Expenses();

        for (Object c : expensesarray) {
            JSONObject jsonobject = (JSONObject) c;
            String expensename = jsonobject.getString("expensename");
            String date = jsonobject.getString("date");
            String category = jsonobject.getString("category");
            double price = jsonobject.getInt("price");


            expenses.addExpenses(expensename, price, date, category);
        }
        expenses.setExpenseLimit(expenselimit);
        addedcustomer.addreadCustomerExpense(expenses);
    }


}





