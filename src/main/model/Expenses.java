package model;

import java.util.ArrayList;

public class Expenses {

    private ArrayList expenseList = new ArrayList();
    private ArrayList education = new ArrayList();
    private ArrayList entertainment = new ArrayList();
    private ArrayList eatOut = new ArrayList();
    private ArrayList grocery = new ArrayList();
    private ArrayList<String> category = new ArrayList();
    //    private static int var = 0;
//    private static int categoryvar = 0;
    private double expenselimit = 2000; // by default user has expense limit of $2000
    private int size;
    private int count;
    //    private String expenseCategory;
    private double totalAmountSpent = 0;
//    Customer cus;


    //REQUIRES : price>=0
    //EFFECTS:This constructor is used to add the name of the expenses along with price and date and category
    public Expenses(String expensename, double price, String date, String category) {
        addExpenses(expensename, price, date, category);
    }

    //EFFECTS : This is an empty constructor created so that if one just wants to access the attributes
    //of the Expenses class they can do so without actually being forced to put the full details of expenses
    public Expenses() {
    }


    //REQUIRES : price>0
    //MODIFIES: this
    //EFFECTS: It is bascially used to store the name of the expense of the user along with the price and dateof expense
    //- This method only works if the expense limit which is set up by the user is currently more than total_amount
    //  of expenses made by the user. If the user is willing to add an expense which will exceed the expense limit
    //  then the method will ask the user to update their expense limit so they can add the expense along with the date
    // This method also increments the size of the arraylist
    public String addExpenses(String expensename, double price, String date, String category) {

        if (expenselimit < (totalAmountSpent + price)) {
            return "You will cross your expense limit of $ " + getExpenseLimit() + " :" + " "
                    + "If you will still like to store this expense please update your expense limit";
        } else {

            expenseList.add(expensename);
            expenseList.add(price);
            expenseList.add(date);
            setExpenseCategory(category);
            addCategory(category, expensename, price, date);


            totalAmountSpent += price;
            size += 3;


            return "Expense:  " + expenseList.get(size - 3) + " with the price being : "
                    + expenseList.get(size - 2) + " has been added successfully on "
                    + expenseList.get(size - 1);

        }

    }

    //    by default user is assigned an expense limit of $2000
    public void setExpenseLimit(double expenselimit) {

        this.expenselimit = expenselimit;
    }

    public double getExpenseLimit() {

        return this.expenselimit;
    }

    public double getTotalAmountSpent() {
        return this.totalAmountSpent;
    }


    // EFFECTS : This method is used to display all the  expenses of the user
    public String displayAllExpenses() {
        String s = "";
        for (int i = 0; i < expenseList.size(); i += 3) {
            s = s + "Expense:  " + expenseList.get(i) + " with the price being : "
                    + expenseList.get(i + 1) + " was purchased on "
                    + expenseList.get(i + 2) + "\n";
        }
        return s;


    }

    // EFFECTS : This method is used to display all the entertainment expenses of the user
    public String displayEntertainmentExpenses() {
        String s = "";
        for (int i = 0; i < entertainment.size(); i += 3) {
            s = s + "Expense:  " + entertainment.get(i) + " with the price being : "
                    + entertainment.get(i + 1) + " was purchased on "
                    + entertainment.get(i + 2) + "\n";
        }
        return s;
    }

    // EFFECTS : This method is used to display all the educational  expenses of the user
    public String displayEducationExpenses() {
        String s = "";
        for (int i = 0; i < education.size(); i += 3) {
            s = s + "Expense:  " + education.get(i) + " with the price being : "
                    + education.get(i + 1) + " was purchased on "
                    + education.get(i + 2) + "\n";
        }
        return s;
    }

    // EFFECTS : This method is used to display all the grocery expenses of the user
    public String displayGroceryExpenses() {
        String s = "";
        for (int i = 0; i < grocery.size(); i += 3) {
            s = s + "Expense:  " + grocery.get(i) + " with the price being : "
                    + grocery.get(i + 1) + " was purchased on "
                    + grocery.get(i + 2) + "\n";
        }
        return s;
    }

    // EFFECTS : This method is used to display all the eatout expenses of the user
    public String displayEatOutExpenses() {
        String s = "";
        for (int i = 0; i < eatOut.size(); i += 3) {
            s = s + "Expense:  " + eatOut.get(i) + " with the price being : "
                    + eatOut.get(i + 1) + " was purchased on "
                    + eatOut.get(i + 2) + "\n";
        }
        return s;
    }


//    REQUIRES: index>0
//    MODIFIES: this
//    EFFECTS: This method is used to completely remove a particular expense of the user

    public void removeExpense(int expensenumber) {

        // let us suppose that user says that they want to remove 2nd expense and its category is
        //say education
        //0  0-2
        //1 3-5
        //2 6-8
        //3 9-11
        //4 12-14
        //5 15-17


        if (expensenumber == 1) {


            for (int i = 0; i < 3; i++) {
                expenseList.remove(i);
            }
            size -= 3;

        } else {

            for (int i = 1; i <= expensenumber - 1; i++) {
                count += 2;            // count = 2
            }
            int imm = (expensenumber - 1) + count; //  3
            String nameOfExpense = (String) expenseList.get(imm);  // 3


            double price = (Double) (expenseList.get(imm + 1));  //4

            String date = (String) expenseList.get(imm + 2); // 5

            int counter = 0;

            while (counter <= 2) {
                expenseList.remove(imm);
                counter++;
            }


            size -= 3;
            count = 0;


            removeRelatingExpense(expensenumber, nameOfExpense, price, date);


        }


    }

    // EFFECTS : This method checks if the arraylist is of type expense,education,entertainment or grocery
    public void removeRelatingExpense(int expensenumber, String nameofexpense, double price, String date) {

        if (category.get(expensenumber - 1).equalsIgnoreCase("Education")) {
            removeRelatingExpensehelper(education, nameofexpense, price, date, expensenumber);
        } else if (category.get(expensenumber - 1).equalsIgnoreCase("Entertainment")) {
            removeRelatingExpensehelper(entertainment, nameofexpense, price, date, expensenumber);
        } else if (category.get(expensenumber - 1).equalsIgnoreCase("eatout")) {
            removeRelatingExpensehelper(entertainment, nameofexpense, price, date, expensenumber);
        } else if (category.get(expensenumber - 1).equalsIgnoreCase("Grocery")) {
            removeRelatingExpensehelper(entertainment, nameofexpense, price, date, expensenumber);
        }

    }

    // EFFECTS : This method is used to remove all the relating expenses from one of 4 arraylist like
    // education,entertainment,eatout and grocery
    public void removeRelatingExpensehelper(ArrayList c, String nameofexpense, double price, String date,
                                            int expensenumber) {

        int x = 0;
        int count = 0;


        for (int i = 0; i < c.size(); i += 3) {


            if (c.get(i).equals(nameofexpense) && c.get(i + 1).equals(price)
                    && c.get(i + 2).equals(date)) {
                x = i;
                count++;
            }
        }

        if (count != 0) {

            for (int i = x; i <= x + 2; i++) {
                c.remove(x);
            }
        }
        category.remove(expensenumber - 1);


    }

    // EFFECTS : This method checks if the arraylist is of type expense,education,entertainment or grocery
    public void addCategory(String category, String expensename, double price, String date) {

        if (category.equalsIgnoreCase("Education")) {
            categoryhelper(education, expensename, price, date);
        } else if (category.equalsIgnoreCase("Entertainment")) {
            categoryhelper(entertainment, expensename, price, date);

        } else if (category.equalsIgnoreCase("eatout")) {
            categoryhelper(eatOut, expensename, price, date);

        } else if (category.equalsIgnoreCase("Grocery")) {
            categoryhelper(grocery, expensename, price, date);

        }
    }

    // EFFECTS : This method adds the expense to a particular arraylist
    public void categoryhelper(ArrayList c, String expensename, double price, String date) {
        c.add(expensename);
        c.add(price);
        c.add(date);
    }

    //    this method adds all the expenses to a particular arraylist
    public void setExpenseCategory(String expenseCategory) {
        category.add(expenseCategory);

    }

    public int getSize() {
        return size;
    }

    public ArrayList getEducation() {
        return education;
    }

    public ArrayList getEntertainment() {
        return entertainment;
    }

    public ArrayList getEatOut() {
        return eatOut;
    }

    public ArrayList getGrocery() {
        return grocery;
    }

    public ArrayList getExpenseList() {
        return expenseList;
    }

    public ArrayList<String> getCategory() {
        return category;
    }


}




