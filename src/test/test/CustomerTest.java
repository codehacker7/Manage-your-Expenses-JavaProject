package test;

import model.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    Customer c;
    Customer d;

        @Before
        public void setup() {
            c = new Customer("Alex", 1);
            d = new Customer("John",2);
        }

        @Test
        public void TestConstructor() {
            assertTrue(c.getCustomername()== "Alex");
            assertTrue(c.getId() == 1);
            assertTrue(d.getCustomername()=="John");
            assertTrue(d.getId()==2);
        }

        @Test
        public void addexpensesTest() {
            c.addExpenses("HamBurger", 20, "09-09-2022");
            assertEquals(c.getTotalAmountSpent(), 20);
            c.addExpenses("Grocery", 180, "10-09-2022");
            assertTrue(c.getTotalAmountSpent() == 200);
            c.addExpenses("Rent",1810,"11-09-2022");
            assertFalse(c.getTotalAmountSpent()==2010);
            c.setExpenseLimit(3000);
            c.addExpenses("Rent",1810,"11-09-2022");
            assertTrue(c.getTotalAmountSpent()==2010);
        }

        @Test
        public void TestremoveExpenses(){
            c.addExpenses("Burger",15,"9-12-2022");
            c.addExpenses("Pizza",30,"11-12-2022");
            c.addExpenses("Haircut",25,"18-12-2022");
            c.addExpenses("Eat-out",20,"31-12-2022");

            assertTrue(c.getSize()==4);
            c.removeExpense(3);
            assertTrue(c.getSize()==3);
            assertEquals("Haircut",c.getExpenselist().get(2));
            c.removeExpense(1);
            assertEquals("Haircut",c.getExpenselist().get(1));
            assertTrue(c.getSize()==2);

        }



    }



