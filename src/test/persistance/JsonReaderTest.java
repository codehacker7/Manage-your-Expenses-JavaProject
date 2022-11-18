package persistance;

import model.Customer;
import org.json.JSONArray;
import org.json.JSONObject;
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
        JsonReader reader = new JsonReader("./data/expenses1.json");
        try {
            Customer customer = reader.read(1);
            assertEquals("kavyansh", customer.getCustomername());
            assertEquals(20, customer.getreadCustomerExpense().get(0).getTotalAmountSpent());
            assertEquals(1, customer.getId());
            assertEquals("Pasta", customer.getreadCustomerExpense().get(0).getExpenseList().get(0));
            assertEquals(20.0, customer.getreadCustomerExpense().get(0).getExpenseList().get(1));
            assertEquals("20-09-2022", customer.getreadCustomerExpense().get(0).getExpenseList().get(2));
            assertEquals("Eatout", customer.getreadCustomerExpense().get(0).getCategory().get(0));
            assertEquals(2000.0, customer.getreadCustomerExpense().get(0).getExpenseLimit());

            JsonReader reader1 = new JsonReader("./data/expenses1.json");
            JSONObject jsonObject = reader1.read();
            JSONArray cus = jsonObject.getJSONArray("customer");

            JSONObject jsonobject = cus.getJSONObject(0);
            assertEquals("kavyansh", jsonobject.get("name"));
            assertEquals(1, jsonobject.get("id"));

            JSONArray expensesjsonArray = jsonobject.getJSONArray("expenses");
            JSONObject expensesobject = expensesjsonArray.getJSONObject(0);
            assertEquals("Pasta",expensesobject.getString("expensename"));
            assertEquals(20,expensesobject.getInt("price"));
            assertEquals("20-09-2022",expensesobject.getString("date"));
            assertEquals("Eatout",expensesobject.getString("category"));







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
            assertEquals(2000.0, wr.getreadCustomerExpense().get(0).getExpenseLimit());


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testIdNotPresent(){
        JsonReader reader = new JsonReader("./data/testReaderExpenses.json");
            try {
                Customer wr = reader.read(2);
                assertEquals("No details found", wr.getCustomername());
                assertEquals(-1, wr.getId());

            } catch (IOException e) {
                fail("Couldn't read from file");
            }
    }

    @Test
    void testreader(){
        try{
          JsonReader reader = new JsonReader("./data/testReaderEmptyExpenses.json");
          JSONObject jsonObject = reader.read();
          System.out.println(jsonObject);
            JSONArray cus = jsonObject.getJSONArray("customer");
            JSONObject jsonobject = cus.getJSONObject(0);
            assertEquals("kavyansh", jsonobject.get("name"));
            assertEquals(1, jsonobject.get("id"));
            assertEquals(2000,jsonobject.get("expenselimit"));


        }catch (IOException c){

        }
    }









}
