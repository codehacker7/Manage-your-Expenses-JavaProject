package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExpenseDividerTest {

    ExpenseDivider c;


    @BeforeEach()
    public void setup() {

        c = new ExpenseDivider();
    }

    @Test
    public void TestgetMoneySpentonGrocEatEntEdu() {
        c.expense.setExpenseLimit(20000);
        c.expense.addExpenses("Grocery", 3, "21-09-2022", "Grocery");
        c.expense.addExpenses("Burger", 5, "23-10-2022", "Eatout");
        c.expense.addExpenses("Pizza", 9, "29-09-2022", "Eatout");
        c.expense.addExpenses("Apples", 15, "29-09-2022", "Grocery");
        c.expense.addExpenses("UBCFEE", 10000, "30-10-2022", "Education");
        c.expense.addExpenses("Movie", 30, "22-10-2022", "Entertainment");

        assertEquals(c.getMoneySpentOnGrocery(), Double.valueOf(18));
        assertTrue(c.getMoneySpentOnGrocery() == 18);
        assertEquals(c.getMoneySpentOnEatOut(), Double.valueOf(14));

        assertEquals(c.getMoneySpentOnEducation(), Double.valueOf(10000));
        assertEquals(c.getMoneySpentOnEntertainment(), Double.valueOf(30));


    }

    @Test
    public void TestEduEatEntGroceryPercentage() {
        c.expense.addExpenses("Grocery", 100, "21-09-2022", "Grocery");
        c.expense.addExpenses("Pizza", 120, "29-09-2022", "Eatout");
        c.expense.addExpenses("UBCFEE", 300, "30-10-2022", "Education");
        c.expense.addExpenses("Movie", 200, "22-10-2022", "Entertainment");

        assertEquals(c.getEducationPercentage(), ((300 / 720.0) * 100));
        assertEquals(c.getGroceryPercentage(), ((100 / 720.0) * 100));
        assertEquals(c.getEatoutPercentage(), ((120 / 720.0) * 100));
        assertEquals(c.getEntertainmentPercentage(), ((200 / 720.0) * 100));


    }

    @Test
    public void Testexpensereport() {
        c.expense.addExpenses("Grocery", 100, "21-09-2022", "Grocery");
        c.expense.addExpenses("Pizza", 120, "29-09-2022", "Eatout");
        c.expense.addExpenses("UBCFEE", 300, "30-10-2022", "Education");
        c.expense.addExpenses("Movie", 200, "22-10-2022", "Entertainment");

        c.customer.setCustomername("Pranjal");

        String c3 = "Dear Pranjal you have spent  41.66666666666667 % on education , 16.666666666666664 % on eating out "
                    +", 27.77777777777778 % on entertainment , 13.88888888888889 % on grocery. ";
        assertEquals(c3, c.expensesReport());


    }


}
