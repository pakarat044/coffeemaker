Feature: purchase beverage
  Scenario: I have enough money to purchase beverage
    Given empty coffeemaker
    When add recipe1
    And paid 50 for recipe position at 1
    Then change 0

  Scenario: I have not enough money to purchase beverage
    Given empty coffeemaker
    When add recipe1
    And paid 20 for recipe position at 1
    Then change 20

  Scenario: I have enough money to purchase beverage but dont have enough inventory.
    Given empty coffeemaker
    When add recipe2
    And paid 75 for recipe position at 1
    Then change 75

