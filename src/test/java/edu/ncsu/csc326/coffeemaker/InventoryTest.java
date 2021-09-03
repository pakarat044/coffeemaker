package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for Inventory class.
 *
 * @author Phakarat Khongphaisan
 */
public class InventoryTest {
    /**
     * The object under test.
     */
    private Inventory inventory;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        inventory = new Inventory();

        //Set up for r1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");
    }

    /*
    * Sets the negative number of chocolate units in the inventory.
    * */
    @Test
    public void testSetchocolate(){
        inventory.setChocolate(-5);
        assertEquals(15,inventory.getChocolate());
    }

    /*
     * Add not the number of chocolate units in the inventory
     * to the current amount of chocolate units.
     *
     * */
    @Test(expected = InventoryException.class)
    public void testAddchocolate1() throws InventoryException {
        inventory.addChocolate("wefw");
    }

    /*
     * Add the number less than 0 of chocolate units in the inventory
     * to the current amount of chocolate units.
     * */
    @Test(expected = InventoryException.class)
    public void testAddchocolate2() throws InventoryException {
        inventory.addChocolate("-20");
    }

    /*
     * Sets the negative number of coffee units in the inventory
     * to the specified amount.
     * */
    @Test
    public void testSetcoffee(){
        inventory.setCoffee(-20);
        assertEquals(15,inventory.getCoffee());
    }

    /*
     * Add not the number of coffee units in the inventory
     * to the current amount of coffee units.
     * */
    @Test(expected = InventoryException.class)
    public void testAddcoffee1() throws InventoryException {
        inventory.addCoffee("dsff");
    }

    /*
     * Add the number less than 0 of coffee units in the inventory
     * to the current amount of coffee units.
     * */
    @Test(expected = InventoryException.class)
    public void testAddcoffee2() throws InventoryException {
        inventory.addCoffee("-20");
    }

    /*
     * Sets the negative number of milk units in the inventory
     * to the specified amount.
     * */
    @Test(expected = InventoryException.class)
    public void testSetmilk(){
        inventory.setMilk(-20);
        assertEquals(15,inventory.getMilk());
    }

    /*
     * Add not the number of milk units in the inventory
     * to the current amount of milk units.
     * */
    @Test(expected = InventoryException.class)
    public void testAddmilk() throws InventoryException{
        inventory.addMilk("asda");
    }

    /*
     * Sets the negative number of sugar units in the inventory
     * to the specified amount.
     * */
    @Test(expected = InventoryException.class)
    public void testSetsugar1(){
        inventory.setSugar(-20);
        assertEquals(15,inventory.getSugar());
    }

    /*
     * Add not the number of sugar units in the inventory
     * to the current amount of sugar units.
     * */
    @Test(expected = InventoryException.class)
    public void testSetsugar2() throws InventoryException{
        inventory.addSugar("asda");
    }

    /*
     * Returns true if there are enough coffee ingredients to make
     * the beverage.
     * */
    @Test
    public void testEnoughingredients1(){
        inventory.setCoffee(0);
        assertFalse(inventory.enoughIngredients(recipe1));
    }

    /*
     * Returns true if there are enough milk ingredients to make
     * the beverage.
     * */
    @Test
    public void testEnoughingredients2(){
        inventory.setMilk(0);
        assertFalse(inventory.enoughIngredients(recipe2));}

    /*
     * Returns true if there are enough sugar ingredients to make
     * the beverage.
     * */
    @Test
    public void testEnoughingredients3(){
        inventory.setSugar(0);
        assertFalse(inventory.enoughIngredients(recipe2));
    }
}
