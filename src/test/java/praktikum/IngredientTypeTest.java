package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    public static boolean isInEnum(String data){
        try {
            Enum.valueOf(IngredientType.class, data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Test
    public void checkIsIngredientInEnum(){
        boolean hasSauce = isInEnum("SAUCE");
        assertEquals(true, hasSauce);
        boolean hasFilling = isInEnum("FILLING");
        assertEquals(true, hasFilling);
    }
    @Test
    public void checkLengthOfEnum(){
        int realLengthOfEnum = IngredientType.values().length;
        assertEquals(2, realLengthOfEnum);
    }

}