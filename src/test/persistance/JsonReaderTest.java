package persistance;

import model.Customer;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        persistence.JsonReader reader = new persistence.JsonReader("./data/noSuchFile.json");
        try {
            Customer customer = reader.read(1);
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderExpenses() {
        JsonReader reader = new JsonReader("./data/expenses.json");
        try {
            Customer customer = reader.read(1);
            assertEquals("kavyansh", customer.getCustomername());
            assertEquals(20, customer.getreadCustomerExpense().get(0).getTotalAmountSpent());
            assertEquals(1, customer.getId());
            assertEquals("Pasta", customer.getreadCustomerExpense().get(0).getExpenseList().get(0));
            assertEquals(20.0, customer.getreadCustomerExpense().get(0).getExpenseList().get(1));
            assertEquals("20-09-2022", customer.getreadCustomerExpense().get(0).getExpenseList().get(2));
            assertEquals("Eatout", customer.getreadCustomerExpense().get(0).getCategory().get(0));


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyExpenses() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenses.json");
        try {
            Customer wr = reader.read(1);
            assertEquals("kavyansh", wr.getCustomername());
            assertEquals(1, wr.getId());
            assertTrue(wr.getreadCustomerExpense().get(0).getExpenseList().size()==0);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }





}
