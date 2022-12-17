package model;

import org.json.JSONArray;
import org.json.JSONObject;
import ui.ExpensesApp;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/*

This class is used to access the name and id of the customer

 */
public class Customer {

    private int id;
    //private int cus
    private String customername;
    List<Expenses> expenses = new ArrayList<>();
    List<Expenses> readexpenses = new ArrayList<>();


    //  REQUIRES : id>=0
//  EFFECTS : It is used to set the name and the id of the customer
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

    //EFFECTS : It is used to add the expenses which are made by the user to an expenses arraylist
    public void addExpenses(Expenses c) {
        expenses.add(c);
    }

    public List<Expenses> getExpenses() {
        return expenses;
    }


    //EFFECTS : It is used to add either a new customer to the system or add existing expenses of an existing user

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public JSONObject toJson(JSONObject c3, int id) {

        JSONArray jsonArray = c3.getJSONArray("customer");
        JSONObject json = new JSONObject();
        boolean value1 = false;
        double actualexpenselimit = 0;


        for (Object jsonattributes : jsonArray) {
            JSONObject jsonobject = (JSONObject) jsonattributes;
            if ((int) jsonobject.get("id") == id) {
                value1 = true;
                double jsonexpenselimit = jsonobject.getDouble("expenselimit");

                for (Expenses c : expenses) {
                    actualexpenselimit = c.getExpenseLimit();
                    //1000 limit actual

                }

                if (jsonexpenselimit < 2000.0) {
                    if (actualexpenselimit != 2000.0) {
                        jsonobject.remove("expenselimit");
                        jsonobject.put("expenselimit", actualexpenselimit);
                    } else {
                        jsonobject.remove("expenselimit");
                        jsonobject.put("expenselimit", jsonexpenselimit);
                    }
                } else {
                    if (actualexpenselimit != 2000.0) {
                        jsonobject.remove("expenselimit");
                        jsonobject.put("expenselimit", actualexpenselimit);
                    } else {
                        jsonobject.remove("expenselimit");
                        jsonobject.put("expenselimit", jsonexpenselimit);
                    }
                }


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

            for (Expenses c : expenses) {
                newjson.put("expenselimit", c.getExpenseLimit());
            }

            newjson.put("expenses", forExpenses());
            jsonArray.put(newjson);
            json.put("customer", jsonArray);
            return json;


        }
    }

    //EFFECTS : If the customer is a new customer this method is used to put all the attributes such as
    // expensename, price, date and category to the expenses array
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


        return jsonArray;
    }

    //EFFECTS : This method is made so that when there is no customer array in the system it can write that
    public JSONObject emptyvalue(Customer c, int id) {


        JSONObject jsonObject = new JSONObject();

        jsonObject.put("customer", expenses2());

        return jsonObject;


    }

    //EFFECTS : This method is made so that when there is no customer array in the system it can
    //write the name id and expenses and make an customer array
    public JSONArray expenses2() {

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", customername);
        jsonObject.put("id", id);
        for (Expenses c : expenses) {
            jsonObject.put("expenselimit", c.getExpenseLimit());
        }
        jsonObject.put("expenses", expense3());

        jsonArray.put(jsonObject);
        return jsonArray;
    }

    //EFFECTS : This method is used to add the attributes of expenses to the expenses array
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

    //EFFECTS : This method is used to add expenses to the readexpenses arraylist
    public void addreadCustomerExpense(Expenses c) {
        readexpenses.add(c);
    }

    public List<Expenses> getreadCustomerExpense() {
        return readexpenses;
    }

}









