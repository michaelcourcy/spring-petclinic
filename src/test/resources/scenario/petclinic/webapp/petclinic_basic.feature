# this feature is based on the petclinic spring sample demo
# https://github.com/spring-projects/spring-petclinic
Feature: Visit the pet clinic basic test

  Scenario: Go to the home page and check the image
    Given I navigate to 'http://localhost:9966/petclinic/'
    Then I should find the image 'pets.png'
    And I should find the image 'spring-pivotal-logo.png'

  Scenario: Go to the owners search page without any search criteria
    Given I navigate to 'http://localhost:9966/petclinic/owners/find.html'
    When  I fill the form 'search-owner-form' with
    | field     | value|
    | lastName  |      |
    And I submit
    Then I should find 'Betty Davis' in the data table

  Scenario: Go to the owners search page and search for someone that does not exist
    Given I navigate to 'http://localhost:9966/petclinic/owners/find.html'
    When  I fill the form 'search-owner-form' with
      | field     | value   |
      #choose a name you're sure you'll not find
      | lastName  | WWWXXXYYYY  |
    And I submit
    Then I should find no data table

  Scenario: Go to the add owner page and check that the phone number is rejected
    Given I navigate to 'http://localhost:9966/petclinic/owners/new'
    When  I fill the form 'add-owner-form' with
      | field       | value             |
      | firstName   | Michael           |
      | lastName    | Courcy            |
      | address     | 73 Route de béac  |
      | city        | Saint Nazaire     |
      | telephone   | 06 68 41 87 71    |
    And I submit
    Then I should find the error message 'Valeur numérique hors limite'

  Scenario: Go to the add owner page and check the owner is well added 
    Given I navigate to 'http://localhost:9966/petclinic/owners/new'
    When  I fill the form 'add-owner-form' with
      | field       | value             |
      | firstName   | Michael           |
      | lastName    | Courcy            |
      | address     | 73 Route de béac  |
      | city        | Saint Nazaire     |
      | telephone   | 0668418771        |
    And I submit
    Then I should find 'Owner Information' in the page
    And I should find 'Michael Courcy' in the page


  Scenario: From the previous owner page add a pet
    Given I click Add New Pet
    When I fill the form 'pet' with
      | field       | value             |
      | name        | Choupette         |
      | birthDate   | 2015/11/02        |
      | type        | cat               |
    And I submit
    Then I should find 'Pets and Visits' in the page
    And I should find 'Choupette' in the page
