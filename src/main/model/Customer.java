package model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;


public class Customer {

    private int id;
    private String customername;
    List<Expenses> expenses = new ArrayList<>();
    List<Expenses> readexpenses = new ArrayList<>();


    public Customer(String name, int id) {
        setCustomername(name);
        setId(id);


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public void addExpenses(Expenses c) {
        expenses.add(c);
    }

    public List<Expenses> getExpenses() {
        return expenses;
    }


    @SuppressWarnings("checkstyle:MethodLength")
    public JSONObject toJson(JSONObject c3, int id) {

        JSONArray jsonArray = c3.getJSONArray("customer");
        JSONObject json = new JSONObject();
        boolean value1 = false;


        for (Object jsonattributes : jsonArray) {
            JSONObject jsonobject = (JSONObject) jsonattributes;
            if ((int) jsonobject.get("id") == id) {
                value1 = true;
                JSONArray expensesarray = jsonobject.getJSONArray("expenses");
                for (Expenses c : expenses) {

                    for (int j = 0; j < c.getExpenseList().size(); j += 3) {
                        JSONObject json2 = new JSONObject();

                        json2.put("expensename", c.getExpenseList().get(j));
                        json2.put("price", c.getExpenseList().get(j + 1));
                        json2.put("date", c.getExpenseList().get(j + 2));
                        json2.put("category", c.getCategory().get(j / 3));

                        expensesarray.put(json2);

                    }
                }


            }

        }

        if (value1 == true) {
            json.put("customer", jsonArray);
            return json;
        } else {

            JSONObject newjson = new JSONObject();
            newjson.put("name", customername);
            newjson.put("id", id);
            newjson.put("expenses", forExpenses());
            jsonArray.put(newjson);
            json.put("customer", jsonArray);
            return json;


        }
    }


    public JSONArray forExpenses() {

        JSONArray jsonArray = new JSONArray();


        for (Expenses c : expenses) {
            for (int j = 0; j < c.getExpenseList().size(); j += 3) {
                JSONObject json = new JSONObject();


                json.put("expensename", c.getExpenseList().get(j));
                json.put("price", c.getExpenseList().get(j + 1));
                json.put("date", c.getExpenseList().get(j + 2));
                json.put("category", c.getCategory().get(j / 3));

                jsonArray.put(json);

            }
        }

        System.out.println("Out of the loop");


        return jsonArray;
    }


    public JSONObject emptyvalue(Customer c, int id) {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put("customer", expenses2());

        return jsonObject;



    }

    public JSONArray expenses2() {

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", customername);
        jsonObject.put("id", id);
        jsonObject.put("expenses", expense3());

        jsonArray.put(jsonObject);
        return jsonArray;


    }

    public JSONArray expense3() {
        JSONArray jsonArray = new JSONArray();


        for (Expenses c : expenses) {
            for (int j = 0; j < c.getExpenseList().size(); j += 3) {
                JSONObject json = new JSONObject();


                json.put("expensename", c.getExpenseList().get(j));
                json.put("price", c.getExpenseList().get(j + 1));
                json.put("date", c.getExpenseList().get(j + 2));
                json.put("category", c.getCategory().get(j / 3));

                jsonArray.put(json);

            }
        }

        return jsonArray;
    }

    public void addreadCustomerExpense(Expenses c) {
        readexpenses.add(c);
    }

    public List<Expenses> getreadCustomerExpense() {
        return readexpenses;
    }

}









