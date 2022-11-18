package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {


    Customer c;
    Expenses s;

    @BeforeEach
    public void setup(){
        c = new Customer("Kavyansh",1);

    }

    @Test
    public void TestConstructor() {
        assertTrue(c.getCustomername().equals("Kavyansh"));
        assertTrue(c.getId() == 1);



    }

    @Test
    public void TestExpenses(){
        s = new Expenses("pizza",10,"20-09-2022","Eatout");
        c.addExpenses(s);
         c.addreadCustomerExpense(s);
        assertTrue(s.getSize() == 3);
        assertTrue(s.getTotalAmountSpent() == 10 );
        assertTrue(c.getExpenses().size()==1);
        assertTrue(c.getreadCustomerExpense().size()==1);
    }













}




