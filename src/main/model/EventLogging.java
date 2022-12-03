package model;

import java.util.List;

/*
This class is used to handle all the expenses which are to be printed on the console
 */

public class EventLogging {

    private static int count = 0;

    //EFFECTS: This method is used to print all the expenses added to the system
    public static void addExpensesLogger(Customer c) {

        String s = "";

        for (int i = 0; i < c.expenses.size(); i++) {
            s = s + c.expenses.get(0).displayAllExpenses();
        }
        eventLogger("New " + s + " has been successfully added");
    }

    //EFFECTS: This method is used to print the deatils of new customer
    public static void saveaddCustomer(int id, String customername) {
        if (id != -1 && !(customername.equals("No details found"))) {
            eventLogger("Thank you " + customername + " for saving details with id number " + id);
        }

    }

    //EFFECTS: This method is used to print the deatils of old customer
    public static void loadCustomer(int id, String customername) {
        if (id != -1 && !(customername.equals("No details found"))) {
            eventLogger("Thank you " + customername + " for viewing your expenses " + id);
        }

    }

    //EFFECTS: This method is used to print the all the expenses saved in the system
    public static void loadexpenses(List<Expenses> ex, Customer customer) {
        String s = "";
        for (Expenses exp : customer.getreadCustomerExpense()) {
            for (int j = 0; j < exp.getExpenseList().size(); j += 3) {
                s += "You purchased  " + exp.getExpenseList().get(j)
                        + " with price being  $ " + exp.getExpenseList().get(j + 1)
                        + " and the purchase was made on : " + exp.getExpenseList().get(j + 2)
                        + " which belongs to : " + exp.getCategory().get(j / 3) + "\n ";
            }
        }
        eventLogger(s);

    }

    //EFFECTS: This method is used to print that all the expenses has been updated
    public static void expensesLimitupdated(double updatedlimit) {
        eventLogger("Your expense limit has been updated to " + updatedlimit);
    }

    //EFFECTS: This method is used to create an event log
    public static void eventLogger(String details) {
        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(new Event(details));

    }

    //EFFECTS: This method is used to print all the events in the eventlog at the end of the system
    public static void printEventsInLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString() + "\n");
        }
    }


}
