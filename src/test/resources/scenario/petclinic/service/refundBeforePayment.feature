Feature: Before payment evaluate the refund and add it to the sum
         Traceability Contractual Requirement #000232
         All the cats 2 years old at their visit has a refund of 5 % if their owners
         live in Paris and book the visit before july

  Scenario: Evaluate the refund for a pet that's 2 year old, visit before july and owners's live in Paris
    Given the current date is '2015/06/21'
    And we have in the customers the owner
    | idInDataset | firstName   | lastName  | address       | city    | telephone   |
    | 1           | Michael     | Courcy    | La défense    | Paris   | 0123456789  |
    And this owner own the pet
      | idInDataset | name  | type | birthDate  | ownerIdInDataset |
      | 1           | kitty | cat  | 2013/06/19 | 1                |
    When the visit occur the '2015/06/24' and it cost 100 $
    Then the refund should be 5 $

  Scenario: Evaluate the refund for a pet that's 2 year old, visit before july and owners's live in Nantes
    Given the current date is '2015/06/21'
    And we have in the customers the owner
      | idInDataset | firstName   | lastName  | address       | city    | telephone   |
      | 1           | Michael     | Courcy    | La défense    | Nantes  | 0123456789  |
    And this owner own the pet
      | idInDataset | name  | type | birthDate  | ownerIdInDataset |
      | 1           | kitty | cat  | 2013/06/19 | 1                |
    When the visit occur the '2015/06/24' and it cost 100 $
    Then the refund should be 0 $

  Scenario: Evaluate the refund for a pet that's 2 year old, visit in july and owners's live in Paris
    Given the current date is '2015/06/21'
    And we have in the customers the owner
      | idInDataset | firstName   | lastName  | address       | city    | telephone   |
      | 1           | Michael     | Courcy    | La défense    | Paris   | 0123456789  |
    And this owner own the pet
      | idInDataset | name  | type | birthDate  | ownerIdInDataset |
      | 1           | kitty | cat  | 2013/06/19 | 1                |
    When the visit occur the '2015/07/24' and it cost 100 $
    Then the refund should be 0 $
