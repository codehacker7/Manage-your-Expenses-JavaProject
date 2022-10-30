package persistance;

import model.Customer;
import model.Expenses;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Customer wr = new Customer("Alex",2);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExpenses() throws FileNotFoundException {

        try {
            Customer c = new Customer("Kavyansh",1);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenses.json");
            writer.open();
            writer.emptyFileWrite(c,c.getId());
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenses.json");
            c = reader.read(1);
            assertEquals("Kavyansh",c.getCustomername());
            assertEquals(1, c.getId());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralWorkroom() {
        try {
            Customer wr = new Customer("Kavyansh",1);
            Customer wr1 = new Customer("Pranjal",2);
            Expenses c = new Expenses();

            Expenses c2 = new Expenses();

            c2.addExpenses("Pizza",20,"20-09-2003","Eatout");
            c2.addExpenses("Pasta",10,"11-01-2022","Eatout");
            wr1.addExpenses(c2);

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenses.json");
            JSONObject jsonObject = reader.read();

            c.addExpenses("Pizza",20,"20-09-2003","Eatout");
            c.addExpenses("Pasta",10,"11-01-2022","Eatout");
            wr.addExpenses(c);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenses.json");
            writer.open();
            writer.write(jsonObject,wr1,wr1.getId());
            writer.close();

            JsonReader reader1 = new JsonReader("./data/testWriterGeneralExpenses.json");
            wr = reader1.read(1);
            assertEquals("Kavyansh",wr.getCustomername());
            assertEquals("Pizza",wr.getreadCustomerExpense().get(0).getExpenseList().get(0));
            assertEquals(20.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(1));
            assertEquals("20-09-2003", wr.getreadCustomerExpense().get(0).getExpenseList().get(2));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(0));


            assertEquals("Pasta", wr.getreadCustomerExpense().get(0).getExpenseList().get(3));
            assertEquals(10.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(4));
            assertEquals("11-01-2022", wr.getreadCustomerExpense().get(0).getExpenseList().get(5));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(1));


            wr1 = reader1.read(2);

            assertEquals("Pranjal",wr1.getCustomername());

            assertEquals("Pizza",wr1.getreadCustomerExpense().get(0).getExpenseList().get(0));
            assertEquals(20.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(1));
            assertEquals("20-09-2003", wr.getreadCustomerExpense().get(0).getExpenseList().get(2));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(0));


            assertEquals("Pasta",wr.getreadCustomerExpense().get(0).getExpenseList().get(3));
            assertEquals(10.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(4));
            assertEquals("11-01-2022", wr.getreadCustomerExpense().get(0).getExpenseList().get(5));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(1));






        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }




















}
