Feature: Vet can create vaccine type defining the recurrence rule
         MANTIS 281

  Scenario: List all the type of vaccine
    Given I navigate to 'http://localhost:9966/petclinic/vaccines/find.html'
    When  I fill the form 'search-vaccine-form' with
      | field         | value|
      | vaccineName   |      |
    And I submit
    Then I should find all the vaccine type in the data table

  Scenario: search one vaccine
    Given I navigate to 'http://localhost:9966/petclinic/vaccines/find.html'
    When  I fill the form 'search-vaccine-form' with
      | field         | value           |
      | vaccineName   | Bordetella      |
    And I submit
    Then I should find 'Bordetella' in the data table

  Scenario: Go to the add vaccine type and create a new vaccine with a recurrence of 3 years
    Given Given I navigate to 'http://localhost:9966/petclinic/vaccines/new'
    When  I fill the form 'add-vaccine-form' with
      | field                 | value     |
      | vaccineName           | Lepto     |
      | recurrence            | 3         |
      | is2TimeInjection      | true      |
      | timeBetweenInjection  | 40        |
    And I submit
    Then I should find 'Vaccine information' in the page
    And I should find 'Lepto' in the page
    And I should find 'Edit vaccine' in the page


  Scenario: Go to the add vaccine type and create a new vaccine with a recurrence of 3 years
    Given Given I navigate to 'http://localhost:9966/petclinic/vaccines/new'
    When  I fill the form 'add-vaccine-form' with
      | field                 | value     |
      | vaccineName           | Lepto     |
      | recurrence            | 3         |
      | is2TimeInjection      | false     |
      | timeBetweenInjection  | 40        |
    And I submit
    Then I should find the error message 'time between injection should be zero if the vaccine do not need 2 injections'
