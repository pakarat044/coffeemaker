package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class MyStepdefs {
    private CoffeeMaker coffeeMaker;
    private int change;

    @Given("empty coffeemaker")
    public void emptyCoffeemaker() {
        // Write code here that turns the phrase above into concrete actions
        coffeeMaker = new CoffeeMaker();
    }

    @When("add recipe{int}")
    public void addRecipe1(int int1) throws RecipeException {
        // Write code here that turns the phrase above into concrete actions

        Recipe recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        Recipe recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        Recipe[] recipesList = new Recipe[]{recipe1, recipe2};

        coffeeMaker.addRecipe(recipesList[int1-1]);
    }

    @When("paid {int} for recipe position at {int}")
    public void paidForRecipe1(int int1, int recipenum) {
        // Write code here that turns the phrase above into concrete actions
        change = coffeeMaker.makeCoffee(recipenum-1,int1);
    }

    @Then("change {int}")
    public void change(int int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(int1,change);
    }
}
