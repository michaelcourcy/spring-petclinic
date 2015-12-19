Feature: Test the validation on the person object

  Background:
    Given the language is 'english'

  Scenario: Create a person with firstName empty and check we have a violation error message on it
    Given we create a new person
    And we set firstName ''
    And we set lastName 'smith'
    When we apply validation
    Then we should have 1 violation
    And we should have an error message on 'firstName' that says 'may not be empty'


  Scenario: Create a person with lastName empty and check we have a violation error message on it
    Given we create a new person
    And we set firstName 'John'
    And we set lastName ''
    When we apply validation
    Then we should have 1 violation
    And we should have an error message on 'lastName' that says 'may not be empty'
