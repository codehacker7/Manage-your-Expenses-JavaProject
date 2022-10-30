package model;

public class ExpenseDivider {

    Expenses expense = new Expenses();
    Customer customer = new Customer("RandomTest",1);


    public double getMoneySpentOnGrocery() {
        double totalGroceryAmount = 0;
        for (int i = 1; i < expense.getGrocery().size(); i += 3) {
            totalGroceryAmount += (Double) (expense.getGrocery().get(i));
        }
        return totalGroceryAmount;
    }

    public double getMoneySpentOnEntertainment() {
        double totalEntertainmentAmount = 0;
        for (int i = 1; i < expense.getEntertainment().size(); i += 3) {
            totalEntertainmentAmount += (Double) expense.getEntertainment().get(i);
        }

        return totalEntertainmentAmount;
    }

    public double getMoneySpentOnEducation() {
        double totalEducationAmount = 0;
        for (int i = 1; i < expense.getEducation().size(); i += 3) {
            totalEducationAmount += (Double) expense.getEducation().get(i);
        }

        return totalEducationAmount;
    }

    public double getMoneySpentOnEatOut() {
        double totalEatOutAmount = 0;
        for (int i = 1; i < expense.getEatOut().size(); i += 3) {
            totalEatOutAmount += (Double) expense.getEatOut().get(i);
        }

        return totalEatOutAmount;
    }

    public double getEducationPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double educationpercent = ((getMoneySpentOnEducation()) / (totalAmountSpent)) * 100;

        return educationpercent;
    }

    public double getEatoutPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double eatoutpercent = ((getMoneySpentOnEatOut()) / (totalAmountSpent)) * 100;

        return eatoutpercent;
    }

    public double getEntertainmentPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double entertainmentpercent = ((getMoneySpentOnEntertainment()) / (totalAmountSpent)) * 100;

        return entertainmentpercent;

    }

    public double getGroceryPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double grocerypercentage = ((getMoneySpentOnGrocery()) / (totalAmountSpent)) * 100;

        return grocerypercentage;

    }

    public String expensesReport() {
        double education = getEducationPercentage();
        double eatout = getEatoutPercentage();
        double entertainment = getEntertainmentPercentage();
        double grocery = getGroceryPercentage();

        String a = "Dear " + customer.getCustomername() + " you have spent  " + education + " % on education";
        String b = " , " + eatout + " % on eating out";
        String c = " , " + entertainment + " % on entertainment";
        String d = " , " + grocery + " % on grocery. ";

        return a + b + c + d;
    }


}
