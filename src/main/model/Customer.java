package model;

import java.util.ArrayList;

public class Customer {

    int totalAmountSpent = 0;

    private ArrayList<String> expenselist = new ArrayList();
    private ArrayList<Double> itemprice = new ArrayList();
    private ArrayList<String> expensedate = new ArrayList();
    private double expenselimit = 2000; // by default user has expense limit of $2000
    private int size;
    private int id;

    private String customername;


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

    //REQUIRES : price>0
    //EFFECTS : This method is used to set the name of the customer and set up an id which they can use later for
    // retrieving the data
    public Customer(String customername, int id) {
        setCustomername(customername);
        setId(id);
    }


    //REQUIRES : price>0
    //MODIFIES: this
    //EFFECTS: It is bascially used to store the name of the expense of the user along with the price and dateof expense
    //- This method only works if the expense limit which is set up by the user is currently more than total_amount
    //  of expenses made by the user. If the user is willing to add an expense which will exceed the expense limit
    //  then the method will ask the user to update their expense limit so they can add the expense along with the date
    // This method also increments the size of the arraylist
    public String addExpenses(String expensename, double price, String date) {


        if (expenselimit < (totalAmountSpent + price)) {
            return "You will cross your expense limit of $ " + getExpenseLimit() + " :" + " "
                    + "If you will still like to store this expense please update your expense limit";
        } else {

            expenselist.add(expensename);
            expensedate.add(date);
            itemprice.add(price);
            totalAmountSpent += price;
            size++;

            return "Expense:  " + expenselist.get(expenselist.size() - 1) + " with the price being : "
                    + itemprice.get(itemprice.size() - 1) + " has been added successfully on "
                    + expensedate.get(expensedate.size() - 1);

        }
    }

    //    by default user is assigned an expense limit of $2000
    public void setExpenseLimit(double expenselimit) {

        this.expenselimit = expenselimit;
    }

    public double getExpenseLimit() {

        return this.expenselimit;
    }

    public int getTotalAmountSpent() {
        return this.totalAmountSpent;
    }


    // This method is used to display all the  expenses of the user
    public String displayExpenses() {
        String s = "";
        for (int i = 0; i < expenselist.size(); i++) {
            s = s + " Expense:  " + expenselist.get(i) + " with the price being : "
                    + itemprice.get(i) + " was purchased on "
                    + expensedate.get(i) + "\n";
        }
        return s;


    }
//    REQUIRES: index>0
//    MODIFIES: this
//    EFFECTS: This method is used to completely remove a particular expense of the user

    public void removeExpense(int index) {

        expenselist.remove(index);
        itemprice.remove(index);
        expensedate.remove(index);
        size--;


    }

    public ArrayList getExpenselist() {

        return expenselist;
    }

    public ArrayList getitemprice() {

        return itemprice;
    }

    public ArrayList getExpensedate() {
        return expensedate;
    }

    public int getSize() {
        return size;
    }

}
