Feature: purchase beverage
  Scenario: I have enough money to purchase beverage
    Given empty coffeemaker
    When add recipe1
    And paid 50 for recipe1
    Then change 0

  Scenario: I have not enough money to purchase beverage
    Given empty coffeemaker
    When add recipe1
    And paid 20 for recipe1
    Then change 20

