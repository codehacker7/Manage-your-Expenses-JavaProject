package ui;

import model.Customer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String customername = r.nextLine();


        System.out.println("Enter your id which will be used by you for accessing your data later: ");
        int id = r.nextInt();

        Customer user = new Customer(customername, id);

        menuformain(user);

    }

    @SuppressWarnings("methodlength")
    public static void updateexpenselimit(Customer c, double price) {
        double expenselimit = 0;

        Scanner r = new Scanner(System.in);
        if (c.getExpenseLimit() > (c.getTotalAmountSpent() + price)) {
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

            c.setExpenseLimit(expenselimit);

            r.nextLine();
        }

        while (c.getExpenseLimit() < (c.getTotalAmountSpent() + price)) {
            System.out.print("Your expense limit is " + c.getExpenseLimit());
            System.out.print("This limit is still not enough to purchase the goods : ");
            System.out.println();
            System.out.println("Please update your limit. Enter yes to update it again or No to end the program : ");

            char userresponse = r.nextLine().charAt(0);

            if (userresponse == 'Y' || userresponse == 'y') {
                System.out.println("Enter the new limit: ");
                expenselimit = r.nextInt();
                c.setExpenseLimit(expenselimit);
                r.nextLine();
            } else {
                System.exit(0);
            }
        }
        System.out.println("Thanks for updating your expense limit to " + c.getExpenseLimit());
    }


    @SuppressWarnings("methodlength")
    public static void menuformain(Customer c) {

        Scanner r = new Scanner(System.in);


        System.out.println("Welcome  " + c.getCustomername() + " you have choosen ID number:  " + c.getId());

        boolean input = false;
        while (!input) {
            System.out.println("Please enter the name, price and date of your expense: ");
            String name = r.nextLine();
            double price = r.nextDouble();
            r.nextLine();
            String date = r.nextLine();
            updateexpenselimit(c, price);

            System.out.println(c.addExpenses(name, price, date));

            System.out.println("Do you want to enter any other expense Type yes or no :  ");
            char userresponse = r.nextLine().charAt(0);

            if (userresponse == 'Y' || userresponse == 'y') {
                input = false;
            } else {
                input = true;
            }


        }
        System.out.println("To view the history of expenses please type 1 else type something to end the program : ");
        char history = r.nextLine().charAt(0);
        if (history == '1') {
            System.out.println(c.displayExpenses());
        }
    }
}