Feature: In the financial forecast evaluate the visits eligible for a refund
  Traceability Contractual Requirement #000232
  All the cats 2 years old at their visit has a refund of 5 % if their owners
  live in Paris and book the visit before july

  Scenario: Evaluate the list of visits eligible for a refund
    Given the current date is '2015/06/21'
    And we have this list of potential visits
      | ownerLiveIn   | petName | petType | petBirthDate |
      | Paris         | coco    | cat     | 2013/06/21   |
      | Paris         | miss    | cat     | 2012/06/21   |
      | Paris         | brutus  | dog     | 2013/06/21   |
      | Toronto       | medor   | dog     | 2013/06/21   |
      | Vancouver     | pipette | cat     | 2012/06/21   |
      | Vancouver     | stick   | cat     | 2013/06/21   |
    When we evaluate the list of refundable visit
    Then the list should contain
      | name  |
      | coco  |
    But the list should not contain
      | name    |
      | miss    |
      | brutus  |
      | medor   |
      | pipette |
      | stick   |
