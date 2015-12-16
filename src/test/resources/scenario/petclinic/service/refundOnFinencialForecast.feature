Feature: In the financial forecast evaluate the visits elligible for a refund
  Traceability Contractual Requirement #000232
  All the cats 2 years old at their visit has a refund of 5 % if their owners
  live in Paris and book the visit before july

  Scenario: Evaluate the list of
    Given the current date is '2015/06/21'
    And we have in the customers the owner
      | idInDataset | firstName   | lastName      | address       | city    | telephone   |
      | 1           | Michael     | Courcy        | La défense    | Paris   | 0123456789  |
      | 2           | Arnaud      | De la Breteche| Proce         | Nantes  | 0123456789  |
      | 3           | Annie       | Festou Pottier| Massy         | Paris   | 0123456789  |
    And this owner own the pet
      | idInDataset | name  | type  | birthDate  | ownerIdInDataset |
      | 1           | kitty | cat   | 2013/06/19 | 1                |
      | 2           | sssysy| snake | 2013/06/18 | 2                |
      | 3           | choo  | cat   | 2013/06/18 | 2                |
      | 4           | sharky| fish  | 2013/06/17 | 3                |
      | 5           | miss  | cat   | 2013/06/15 | 3                |
    When we evaluate the list of refundable visit
    Then the list should contain
      | name |
      | kitty|
      | miss |
    But the list should not contain
      | name  |
      | choo  |
      | sharky|
