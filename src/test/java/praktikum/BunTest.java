package praktikum;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;

    public BunTest(String name,float price){
        this.name = name;
        this.price = price;
    }
    @Parameterized.Parameters
    public static Object[][] getDataBuns() {
        return new Object[][]{
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 300},
        };
    }
    @Test
    public void checkGettersBuns(){
        Bun bun = new Bun(name, price);
        String actualName = bun.getName();
        float actualPrice = bun.getPrice();
        assertEquals(name, actualName);
        assertEquals(price, actualPrice, 0);
    }

}