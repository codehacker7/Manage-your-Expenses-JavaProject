package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {


    Customer c;

    @BeforeEach
    public void setup(){
        c = new Customer("Kavyansh",1);

    }

    @Test
    public void TestConstructor() {
        assertTrue(c.getCustomername() == "Kavyansh");
        assertTrue(c.getId() == 1);

    }













}




