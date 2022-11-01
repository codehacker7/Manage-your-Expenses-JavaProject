package model;

/*
This class is made so that user can divide their expenses into grocery,entertainment,education and eatout
and is also used to calculate the percentage of total money spent on each of them
 */

public class ExpenseDivider {

    Expenses expense = new Expenses();
    Customer customer = new Customer("RandomTest", 1);


    //EFFECTS : THIS METHOD RETURNS TOTAL MONEY SPENT ON GROCERY
    public double getMoneySpentOnGrocery() {
        double totalGroceryAmount = 0;
        for (int i = 1; i < expense.getGrocery().size(); i += 3) {
            totalGroceryAmount += (Double) (expense.getGrocery().get(i));
        }
        return totalGroceryAmount;
    }

    //EFFECTS : THIS METHOD RETURNS TOTAL MONEY SPENT ON ENTERTAINMENT
    public double getMoneySpentOnEntertainment() {
        double totalEntertainmentAmount = 0;
        for (int i = 1; i < expense.getEntertainment().size(); i += 3) {
            totalEntertainmentAmount += (Double) expense.getEntertainment().get(i);
        }

        return totalEntertainmentAmount;
    }

    //EFFECTS : THIS METHOD RETURNS TOTAL MONEY SPENT ON EDUCATION
    public double getMoneySpentOnEducation() {
        double totalEducationAmount = 0;
        for (int i = 1; i < expense.getEducation().size(); i += 3) {
            totalEducationAmount += (Double) expense.getEducation().get(i);
        }

        return totalEducationAmount;
    }

    //EFFECTS : THIS METHOD RETURNS TOTAL MONEY SPENT ON EATOUT
    public double getMoneySpentOnEatOut() {
        double totalEatOutAmount = 0;
        for (int i = 1; i < expense.getEatOut().size(); i += 3) {
            totalEatOutAmount += (Double) expense.getEatOut().get(i);
        }

        return totalEatOutAmount;
    }
    //EFFECTS : THIS METHOD RETURNS THE PERCENTAGE OF TOTAL MONEY SPENT ON EDUCATION

    public double getEducationPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double educationpercent = ((getMoneySpentOnEducation()) / (totalAmountSpent)) * 100;

        return educationpercent;
    }

    //EFFECTS : THIS METHOD RETURNS THE PERCENTAGE OF TOTAL MONEY SPENT ON EATOUT
    public double getEatoutPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double eatoutpercent = ((getMoneySpentOnEatOut()) / (totalAmountSpent)) * 100;

        return eatoutpercent;
    }

    //EFFECTS : THIS METHOD RETURNS THE PERCENTAGE OF TOTAL MONEY SPENT ON ENTERTAINMENT
    public double getEntertainmentPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double entertainmentpercent = ((getMoneySpentOnEntertainment()) / (totalAmountSpent)) * 100;

        return entertainmentpercent;

    }

    //EFFECTS : THIS METHOD RETURNS THE PERCENTAGE OF TOTAL MONEY SPENT ON GROCERY
    public double getGroceryPercentage() {

        double totalAmountSpent = expense.getTotalAmountSpent();

        double grocerypercentage = ((getMoneySpentOnGrocery()) / (totalAmountSpent)) * 100;

        return grocerypercentage;

    }

    //EFFECTS : THIS METHOD is used to give an complete analysis of money spent on education,grocery,
    //entertainment and eatout
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
