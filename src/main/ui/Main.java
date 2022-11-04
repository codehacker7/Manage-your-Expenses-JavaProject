package ui;


import model.Customer;
import model.Expenses;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final String destination = "./data/expenses.json";


    public static void main(String[] args) throws IOException {
        Scanner r = new Scanner(System.in);


        Expenses ex = new Expenses();

        System.out.println("Enter your name: ");
        String customername = r.nextLine();


        System.out.println("Enter your id which will be used by you for accessing your data later: ");
        int id = r.nextInt();

        Customer user = new Customer(customername, id);

        menuformain(user, ex);

    }


    // EFFECTS : This method is used to update the expense limit of the user so they can store more expenses
    @SuppressWarnings("methodlength")
    public static void updateexpenselimit(Customer c, double price, Expenses ex) {
        double expenselimit = 0;

        Scanner r = new Scanner(System.in);
        if (ex.getExpenseLimit() > (ex.getTotalAmountSpent() + price)) {
            return;
        }
        System.out.print("Your expense limit is not enough to add this expense please update it   ");
        System.out.println();
        System.out.print("If you do not want to update your limit and end the program type No else type ");
        System.out.println("yes:  ");
        char response = r.nextLine().charAt(0);

        if (response == 'N' || response == 'n') {
            System.exit(0);
        } else {
            System.out.println(" What is the expense limit you want your limit to be updated to ??:  ");
            expenselimit = r.nextDouble();

            ex.setExpenseLimit(expenselimit);

            r.nextLine();
        }

        while (ex.getExpenseLimit() < (ex.getTotalAmountSpent() + price)) {
            System.out.print("Your expense limit is " + ex.getExpenseLimit());
            System.out.print("This limit is still not enough to purchase the goods : ");
            System.out.println();
            System.out.println("Please update your limit. Enter yes to update it again or No to end the program : ");

            char userresponse = r.nextLine().charAt(0);

            if (userresponse == 'Y' || userresponse == 'y') {
                System.out.println("Enter the new limit: ");
                expenselimit = r.nextInt();
                ex.setExpenseLimit(expenselimit);
                r.nextLine();
            } else {
                System.exit(0);
            }
        }
        System.out.println("Thanks for updating your expense limit to " + ex.getExpenseLimit());
    }

    // EFFECTS: This method has a variety of options from which a user can choose and store or see the expenses
    @SuppressWarnings("methodlength")
    public static void menuformain(Customer c, Expenses ex) throws IOException {

        Scanner r = new Scanner(System.in);

        System.out.println("Do you want to look at your saved expenses? (Yes or No): ");
        char expenseslook = r.nextLine().charAt(0);


        if (expenseslook == 'y' || expenseslook == 'Y') {
            readExpenses(c.getId());
        }

        System.out.println();
        System.out.println("Welcome  " + c.getCustomername() + " you have ID number:  " + c.getId());
        System.out.println("In this expense manager we have decided to have 4 categories Household, Education");
        System.out.println("Entertainment, Eatout. Be sure to choose your expense from one of them ");
        System.out.println("********Dont make typos make sure to have spelling like us");

        boolean input = false;
        while (!input) {
            System.out.println();
            System.out.println("Please enter the name, price and date and category of your expense: ");
            String name = r.nextLine();
            double price = r.nextDouble();
            r.nextLine();
            String date = r.nextLine();
            updateexpenselimit(c, price, ex);
            String category = r.nextLine();

            System.out.println(ex.addExpenses(name, price, date, category));

            System.out.println();
            System.out.println("Do you want to enter any other expense Type yes or no :  ");
            char userresponse = r.nextLine().charAt(0);

            if (userresponse == 'Y' || userresponse == 'y') {
                input = false;
            } else {
                input = true;
            }


        }
        c.addExpenses(ex);
        System.out.println("To view the history of expenses please type 1 else type something to end the program : ");
        char history = r.nextLine().charAt(0);

        if (history == '1') {
            System.out.println();
            System.out.println(ex.displayAllExpenses());
        }

        System.out.println("To save all the expenses please press 2: ");
        char save = r.nextLine().charAt(0);


        if (save == '2') {
            saveExpenses(c, c.getId());

        }

    }

    //EFFECTS : This method is used to save expenses of the user within a given id
    public static void saveExpenses(Customer c, int id) throws IOException {
        JsonReader reader = new JsonReader(destination);
        JSONObject c3 = reader.read(); // FULL JSON OBJECT
        JsonWriter writer1 = new JsonWriter(destination);
        writer1.open();
        writer1.write(c3,c, id);
        writer1.close();
    }

    //EFFECTS : This method takes in an id as an input from the user and reads the expenses from the user
    //with the given id and if the id is not found in the database it just returns no account has been found
    //and you can create a new one
    public static void readExpenses(int id) throws IOException {
        JsonReader reader = new JsonReader(destination);
        Customer c = reader.read(id);

        System.out.println();
        for (Expenses exp : c.getreadCustomerExpense()) {
            for (int j = 0; j < exp.getExpenseList().size(); j += 3) {

                System.out.println("Expense name : " + exp.getExpenseList().get(j));
                System.out.println("Price : " + exp.getExpenseList().get(j + 1));
                System.out.println("Date : " + exp.getExpenseList().get(j + 2));
                System.out.println("Category : " + exp.getCategory().get(j / 3));
                System.out.println();
            }


        }
        if (c.getreadCustomerExpense().size() == 0) {
            System.out.println("No account has been found");
            System.out.println("You can make a new one");
        }

    }

}


