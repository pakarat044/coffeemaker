/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modifications 
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to 
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Unit tests for CoffeeMaker class.
 * 
 * @author Phakarat Khongphaisan
 */
public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
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
		coffeeMaker = new CoffeeMaker();
		
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
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	/**
	 * Only three recipes may be added to the CoffeeMaker.
	 */
	@Test
	public void testAddRecipe1() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * A recipe consists of a name, price, units of coffee, units of milk, units of sugar, and units of chocolate.
	 * Each recipe name must be unique in the recipe list.
	 */
	@Test
	public void testAddRecipe2() throws RecipeException {
		coffeeMaker.addRecipe(recipe1);
		recipe2.setName("Coffee");
		assertFalse(coffeeMaker.addRecipe(recipe2));
	}

	/**
	 * Price must be handled as an integer
	 */
	@Test(expected = RecipeException.class )
	public void testAddRecipe3() throws RecipeException {
		recipe1.setPrice("Coffee");
		assertFalse(coffeeMaker.addRecipe(recipe1));
	}

	/**
	 * A recipe may be deleted from the CoffeeMaker
	 * if it exists in the list of recipes in the CoffeeMaker.
	 */
	@Test
	public void testDeleteRecipe1() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertEquals(recipe1.getName(),coffeeMaker.deleteRecipe(0));
	}

	/**
	 * A recipe may be deleted from the CoffeeMaker
	 * if it not exists in the list of recipes in the CoffeeMaker.
	 * return null
	 */
	@Test
	public void testDeleteRecipe2() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertNull(coffeeMaker.deleteRecipe(2));
	}

	/**
	 * A recipe may be deleted from the CoffeeMaker
	 * if it not exists in the list of recipes in the CoffeeMaker.
	 * negative number
	 */
	@Test
	public void testDeleteRecipe3() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		assertNull(coffeeMaker.deleteRecipe(-2));
	}

	/**
	 * A recipe may be edited in the CoffeeMaker if it exists in the list of recipes in the CoffeeMaker.
	 * After selecting a recipe to edit, the user will then enter the new recipe information. A recipe name may not be change.
	 */
	@Test
	public void testEditRecipe1() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.editRecipe(0,recipe2);
		assertEquals(coffeeMaker.getRecipes()[0].getName(),"Coffee");
	}

	/**
	 * A recipe may be edited in the CoffeeMaker if it exists in the list of recipes in the CoffeeMaker.
	 * After selecting a recipe to edit, the user will then enter the new recipe information. A recipe name may not be change.
	 * if recipe number is negative, it shpuld be null.
	 */
	@Test
	public void testEditRecipe2() {
		coffeeMaker.addRecipe(recipe1);
		assertNull(coffeeMaker.editRecipe(-1,recipe2));
	}

	/**
	 * Inventory may be added to the machine at any time from the main menu, and is added to the current inventory in the CoffeeMaker.
	 * amt coffee
	 */
	@Test
	public void testAddInventory1() throws InventoryException {
		coffeeMaker.addInventory("12","0","0","0");
	}

	/**
	 * Inventory may be added to the machine at any time from the main menu, and is added to the current inventory in the CoffeeMaker.
	 * amt milk
	 */
	@Test
	public void testAddInventory2() throws InventoryException {
		coffeeMaker.addInventory("0","12","0","0");
	}

	/**
	 * Inventory may be added to the machine at any time from the main menu, and is added to the current inventory in the CoffeeMaker.
	 * amt sugar
	 */
	@Test
	public void testAddInventory3() throws InventoryException {
		coffeeMaker.addInventory("0","0","12","0");
	}

	/**
	 * Inventory may be added to the machine at any time from the main menu, and is added to the current inventory in the CoffeeMaker.
	 * amt chocolate
	 */
	@Test
	public void testAddInventory4() throws InventoryException {
		coffeeMaker.addInventory("0","0","0","12");
	}

	/**
	 * Inventory may be checked at any time from the main menu. The units of each item in the inventory are displayed.
	 */
	@Test
	public void checkInventory() throws InventoryException {
		coffeeMaker.addInventory("12","12","0","12");
		assertEquals(coffeeMaker.checkInventory(),"Coffee: 27\nMilk: 27\nSugar: 15\nChocolate: 27\n");
	}

	/**
	 * The user selects a beverage and inserts an amount of money.
	 */
	@Test
	public void purchaseBeverage1() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(0,coffeeMaker.makeCoffee(0,50));
		coffeeMaker.addRecipe(recipe3);
		assertEquals(50,coffeeMaker.makeCoffee(2,50));
	}

	/**
	 * The user selects a beverage and inserts an amount of money.
	 * If the beverage is in the RecipeBook and the user paid enough money the beverage will be dispensed and any change will be returned.
	 */
	@Test
	public void purchaseBeverage2() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.makeCoffee(0,50);
		assertEquals(coffeeMaker.checkInventory(),"Coffee: 12\nMilk: 14\nSugar: 14\nChocolate: 15\n");
	}
}
