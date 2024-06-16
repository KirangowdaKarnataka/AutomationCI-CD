@tag
Feature: Purchase the order from the ecommerce website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce page


  @tag2
  Scenario Outline: Positive  Test of Submitting the order
    Given Logged In with <name> and password <password>
    When I add product <productname> to cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page 

    Examples: 
      | name                   | password     | productname     |
      | sureshreaddy@gmail.com | 9480QWERTYaz | ADIDAS ORIGINAL |
      