package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ExpensesTest {

    Expenses expenses;

    @BeforeEach
    public void setup() {
        expenses = new Expenses();

    }
    @Test
    public void TestConstructor(){
        expenses = new Expenses("Pizza", 20.2, "20-09-2022", "Eatout");

        assertTrue(expenses.getSize() == 3);
        assertTrue(expenses.getTotalAmountSpent() == 20.2 );

    }

    @Test
    public void TestaddExpenses() {
        expenses.addExpenses("Pizza", 20.2, "20-09-2022", "Eatout");
        expenses.addExpenses("ChocoLavaCake", 2.99, "22-10-2022", "Entertainment");
        assertTrue(expenses.getSize() == 6);
        assertTrue(expenses.getTotalAmountSpent() == (20.2 + 2.99));
        expenses.addExpenses("HouseRent", 4000, "22-10-2022", "Grocery");
        assertFalse(expenses.getSize() == 9);
        expenses.setExpenseLimit(6000);
        expenses.addExpenses("HouseRent", 4000, "22-10-2022", "Grocery");
        assertTrue(expenses.getSize() == 9);
        assertTrue(expenses.getExpenseList().get(0) == "Pizza");
        assertTrue(expenses.getExpenseList().get(3) == "ChocoLavaCake");
        assertTrue(expenses.getExpenseList().get(1).equals(Double.valueOf(20.2)));
        assertTrue(expenses.getExpenseList().get(4).equals(Double.valueOf(2.99)));
        assertTrue(expenses.getExpenseList().get(2).equals(String.valueOf("20-09-2022")));

    }

    @Test
    public void TestRemoveExpenses() {
        expenses.setExpenseLimit(9000);
        expenses.addExpenses("Pizza", 20.2, "20-09-2022", "Eatout");
        expenses.addExpenses("ChocoLavaCake", 2.99, "22-10-2022", "Entertainment");
        expenses.addExpenses("ChocoLavaCake1", 7, "27-10-2022", "Entertainment");
        expenses.addExpenses("ChocoLavaCake2", 9.9, "29-10-2022", "Entertainment");
        expenses.removeExpense(2);
        expenses.removeExpense(3);
        assertTrue(expenses.getExpenseList().size() == 6);
        assertEquals(expenses.getExpenseList().get(3), "ChocoLavaCake1");
        expenses.removeExpense(1);

    }

    @Test
    public void TestremoveRelatingExpenses() {
        expenses.addExpenses("Pizza", 20.2, "20-09-2022", "Eatout");
        expenses.addExpenses("ChocoLavaCake", 2.99, "22-10-2022", "Entertainment");
        expenses.addExpenses("ChocoLavaCake1", 7, "27-10-2022", "Entertainment");
        expenses.addExpenses("ChocoLavaCake2", 9.9, "29-10-2022", "Entertainment");
        assertTrue(expenses.getEntertainment().size() == 9);
        expenses.removeRelatingExpense(3, "ChocoLavaCake1", 7.0, "27-10-2022");
        assertTrue(expenses.getEntertainment().size() == 6);
        assertTrue(expenses.getEntertainment().get(3) == "ChocoLavaCake2");
        assertEquals(expenses.getEntertainment().get(4), Double.valueOf(9.9));
        assertEquals(expenses.getEntertainment().get(5), "29-10-2022");
        expenses.addExpenses("UBC FEE", 9.9, "29-10-2022", "Education");
        expenses.removeRelatingExpense(3, "UBC FEE", 9.9, "29-10-2022");
        expenses.addExpenses("Grocery", 9.9, "29-10-2022", "Grocery");
        expenses.removeRelatingExpense(3, "UBC FEE", 9.9, "29-10-2022");

    }


    @Test
    public void TestAddCategory() {
        expenses.addCategory("Education", "UBC FEE", 20, "20-10-2022");
        expenses.addCategory("GROCERY", "Grocery", 50, "10-10-2022");
        expenses.addCategory("Entertainment", "Movie", 80, "19-10-2022");
        expenses.addCategory("Eatout", "Pizza", 100, "22-10-2022");

        assertTrue(expenses.getEducation().get(0) == "UBC FEE");
        assertTrue(expenses.getGrocery().get(0) == "Grocery");
        assertTrue(expenses.getEntertainment().get(0) == "Movie");
        assertTrue(expenses.getEatOut().get(0) == "Pizza");

    }

    @Test
    public void Testdisplayallexpenses() {
        expenses.addExpenses("Burger", 15, "9-12-2022", "Eatout");
        String v = "Expense:  Burger with the price being : 15.0 was purchased on 9-12-2022\n";
        assertEquals(v, expenses.displayAllExpenses());
        assertEquals(v, expenses.displayEatOutExpenses());
    }

    @Test
    public void TestdisplayEntertainmentExpenses() {
        expenses.addExpenses("Burger", 15, "9-12-2022", "Eatout");
        expenses.addExpenses("Movie", 15, "9-12-2022", "Entertainment");
        String v = "Expense:  Movie with the price being : 15.0 was purchased on 9-12-2022\n";
        assertEquals(v, expenses.displayEntertainmentExpenses());
    }

    @Test
    public void TestdisplayEducationExpenses() {
        expenses.addExpenses("Burger", 15, "9-12-2022", "Eatout");
        expenses.addExpenses("UBCFEE", 15, "9-12-2022", "Education");
        String v = "Expense:  UBCFEE with the price being : 15.0 was purchased on 9-12-2022\n";
        assertEquals(v, expenses.displayEducationExpenses());
    }

    @Test
    public void TestdisplayGroceryExpenses() {
        expenses.addExpenses("Bread", 15, "9-12-2022", "Grocery");

        String v = "Expense:  Bread with the price being : 15.0 was purchased on 9-12-2022\n";
        assertEquals(v, expenses.displayGroceryExpenses());
    }


}
