package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Database database;
    @Mock
    Burger burger;
    public void receiveAvailableBuns(){
        List<Bun> buns = new ArrayList<>();
        buns.add(new Bun("black bun", 100));
        buns.add(new Bun("white bun", 200));
        buns.add(new Bun("red bun", 300));
        Mockito.when(database.availableBuns()).thenReturn(buns);
    }
    public void receiveAvailableIngredients(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        ingredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));
        Mockito.when(database.availableIngredients()).thenReturn(ingredients);
    }
    @Test
    public void checkSetBuns(){
        receiveAvailableBuns();
        Bun bun = database.availableBuns().get(1);
        burger.setBuns(bun);
        Mockito.verify(burger).setBuns(bun);
    }

    @Test
    public void checkAddIngredientInBurger(){
        Burger burger = new Burger();
        receiveAvailableIngredients();
        Ingredient ingredient = database.availableIngredients().get(0);
        burger.addIngredient(ingredient);
        boolean isIngredientInBurger = burger.ingredients.contains(ingredient);
        assertTrue(isIngredientInBurger);

    }
    @Test
    public void checkRemoveIngredient(){
        Burger burger = new Burger();
        receiveAvailableIngredients();
        Ingredient ingredientSauce = database.availableIngredients().get(0);
        burger.addIngredient(ingredientSauce);
        Ingredient ingredientFilling = database.availableIngredients().get(3);
        burger.addIngredient(ingredientFilling);
        burger.removeIngredient(0);
        int sizeIngredients = burger.ingredients.size();
        assertEquals(1, sizeIngredients);
        boolean isSauceInBurger = burger.ingredients.contains(ingredientSauce);
        assertFalse(isSauceInBurger);

    }

    @Test
    public void checkMoveIngredient(){
        Burger burger = new Burger();
        receiveAvailableIngredients();
        Ingredient ingredientFirst = database.availableIngredients().get(1);
        burger.addIngredient(ingredientFirst);
        Ingredient ingredientSecond = database.availableIngredients().get(4);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(0, 1);
        Ingredient ingredientFirstAfterMove = burger.ingredients.get(0);
        assertEquals(ingredientFirstAfterMove, ingredientSecond);
    }
    @Test
    public void checkGetPrice(){
        Burger burger = new Burger();
        receiveAvailableBuns();
        Bun bun = database.availableBuns().get(1);
        burger.setBuns(bun);
        receiveAvailableIngredients();
        Ingredient ingredientSauce = database.availableIngredients().get(0);
        burger.addIngredient(ingredientSauce);
        Ingredient ingredientFillingFirst = database.availableIngredients().get(3);
        burger.addIngredient(ingredientFillingFirst);
        Ingredient ingredientFillingSecond = database.availableIngredients().get(5);
        burger.addIngredient(ingredientFillingSecond);
        double actualPriceBurger = burger.getPrice();
        double expectedPriceBurger = 900;
        assertEquals(expectedPriceBurger,actualPriceBurger, 0);
    }
    @Test
    public void checkGetReceipt(){
        Burger burger = new Burger();
        receiveAvailableBuns();
        Bun bun = database.availableBuns().get(1);
        burger.setBuns(bun);
        receiveAvailableIngredients();
        Ingredient ingredientSauce = database.availableIngredients().get(0);
        burger.addIngredient(ingredientSauce);
        Ingredient ingredientFillingFirst = database.availableIngredients().get(3);
        burger.addIngredient(ingredientFillingFirst);
        StringBuilder actualReceipt = new StringBuilder(String.format("(==== %s ====)%n","white bun"));
        actualReceipt.append(String.format("= %s =%n","sauce hot sauce"));
        actualReceipt.append(String.format("= %s =%n","filling cutlet"));
        actualReceipt.append(String.format("(==== %s ====)%n","white bun"));
        actualReceipt.append(String.format("%nPrice: %f%n", 600.000000));
        assertEquals(actualReceipt.toString(), burger.getReceipt());
    }
}