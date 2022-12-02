package model;

import java.util.List;

public class EventLogging {

    private static int count = 0;

    public static void addExpensesLogger(Customer c) {

        String s = "";

        for (int i = 0; i < c.expenses.size(); i++) {
            s = s + c.expenses.get(0).displayAllExpenses();
        }
        eventLogger("New " + s + " has been successfully added");
    }

    public static void saveaddCustomer(int id, String customername) {
        if (id != -1 && !(customername.equals("No details found"))) {
            eventLogger("Thank you " + customername + " for saving details with id number " + id);
        }

    }

    public static void loadCustomer(int id, String customername) {
        if (id != -1 && !(customername.equals("No details found"))) {
            eventLogger("Thank you " + customername + " for viewing your expenses " + id);
        }

    }

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

    public static void expensesLimitupdated(double updatedlimit) {
        eventLogger("Your expense limit has been updated to " + updatedlimit);
    }


    public static void eventLogger(String details) {
        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(new Event(details));

    }

    public static void printEventsInLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString() + "\n");
        }
    }


}
