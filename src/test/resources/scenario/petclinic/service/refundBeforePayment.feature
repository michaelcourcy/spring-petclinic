Feature: Before payment evaluate the refund and add it to the sum
         Traceability Contractual Requirement #000232
         All the cats 2 years old at their visit has a refund of 5 % if their owners
         live in Paris and book the visit before the end of the month.

  Scenario: Evaluate the refund for a pet that should have
    Given the current date is '2015/11/21'
    And we have in the customers the owner
    | firstName   | lastName  | address       | city    | telephone   |
    | Michael     | Courcy    | La défense    | Paris   | 0123456789  |
    And this owner own the pet
    | name  | type | birthDate  |
    | kitty | cat  | 2013/11/19 |
    When the visit occur the '2015/11/24' and it cost 100 $
    Then the refund should be 5 $
