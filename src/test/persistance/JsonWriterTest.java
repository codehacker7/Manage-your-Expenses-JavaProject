package persistance;

import model.Customer;
import model.Expenses;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Customer wr = new Customer("Alex", 2);
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
            Customer c = new Customer("Kavyansh", 1);
            Expenses exp = new Expenses();
            exp.addExpenses("Pizza", 20, "20-09-2003", "Eatout");
            c.addExpenses(exp);

            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenses.json");
            writer.open();
            writer.emptyFileWrite(c, c.getId());
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenses.json");
            c = reader.read(1);
            assertEquals("Kavyansh", c.getCustomername());
            assertEquals(1, c.getId());
            assertEquals("Pizza", c.getreadCustomerExpense().get(0).getExpenseList().get(0));
            assertEquals(20.0, c.getreadCustomerExpense().get(0).getExpenseList().get(1));
            assertEquals("20-09-2003", c.getreadCustomerExpense().get(0).getExpenseList().get(2));
            assertEquals("Eatout", c.getreadCustomerExpense().get(0).getCategory().get(0));
            assertEquals(2000.0, c.getreadCustomerExpense().get(0).getExpenseLimit());




        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExpenses() {
        try {
            Customer wr = new Customer("Kavyansh", 1);
            Customer wr1 = new Customer("Pranjal", 2);
            Expenses c = new Expenses();



//            Customer wr3 = new Customer("Kevin",3);
//
//            Expenses c3 = new Expenses();
//
//            c3.addExpenses("Pizza",20,"20-09-2003","Eatout");
//
//            JsonReader reader2 = new JsonReader("./data/testWriterGeneralExpenses.json");
//            wr3.addExpenses(c3);
//            
//            wr3 = reader2.read(3);
//
//            assertEquals("Kevin",wr3.getCustomername());
//            assertEquals("Pizza",wr3.getreadCustomerExpense().get(0).getExpenseList().get(0));
//            assertEquals(20.0, wr3.getreadCustomerExpense().get(0).getExpenseList().get(1));
//            assertEquals("20-09-2003", wr3.getreadCustomerExpense().get(0).getExpenseList().get(2));
//            assertEquals("Eatout", wr3.getreadCustomerExpense().get(0).getCategory().get(0));
//


            Expenses c2 = new Expenses();

            c2.addExpenses("Pizza", 20, "20-09-2003", "Eatout");
            c2.addExpenses("Pasta", 10, "11-01-2022", "Eatout");
            wr1.addExpenses(c2);

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenses.json");
//            JSONObject jsonObject = reader.read();

            c.addExpenses("Pizza", 20, "20-09-2003", "Eatout");
            c.addExpenses("Pasta", 10, "11-01-2022", "Eatout");
            wr.addExpenses(c);


            Customer w3 = new Customer("Kevin", 3);
            Expenses c3 = new Expenses();
            c3.addExpenses("Pizza", 20, "20-09-2003", "Eatout");
            w3.addExpenses(c3);
            JSONObject jsonObject = reader.read();


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenses.json");
            writer.open();
           // writer.write(jsonObject, w3, w3.getId());
            //this should only be executed when you need to increase your code coverege  on auto bot
            writer.write(jsonObject, wr1, wr1.getId());
            writer.close();

            JsonReader reader1 = new JsonReader("./data/testWriterGeneralExpenses.json");
            wr = reader1.read(1);
            assertEquals("Kavyansh", wr.getCustomername());
            assertEquals("Pizza", wr.getreadCustomerExpense().get(0).getExpenseList().get(0));
            assertEquals(20.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(1));
            assertEquals("20-09-2003", wr.getreadCustomerExpense().get(0).getExpenseList().get(2));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(0));


            assertEquals("Pasta", wr.getreadCustomerExpense().get(0).getExpenseList().get(3));
            assertEquals(10.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(4));
            assertEquals("11-01-2022", wr.getreadCustomerExpense().get(0).getExpenseList().get(5));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(1));
            assertEquals(2000.0, wr.getreadCustomerExpense().get(0).getExpenseLimit());


            wr1 = reader1.read(2);

            assertEquals("Pranjal", wr1.getCustomername());

            assertEquals("Pizza", wr1.getreadCustomerExpense().get(0).getExpenseList().get(0));
            assertEquals(20.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(1));
            assertEquals("20-09-2003", wr.getreadCustomerExpense().get(0).getExpenseList().get(2));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(0));
            assertEquals(2000.0, wr.getreadCustomerExpense().get(0).getExpenseLimit());


            assertEquals("Pasta", wr.getreadCustomerExpense().get(0).getExpenseList().get(3));
            assertEquals(10.0, wr.getreadCustomerExpense().get(0).getExpenseList().get(4));
            assertEquals("11-01-2022", wr.getreadCustomerExpense().get(0).getExpenseList().get(5));
            assertEquals("Eatout", wr.getreadCustomerExpense().get(0).getCategory().get(1));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
