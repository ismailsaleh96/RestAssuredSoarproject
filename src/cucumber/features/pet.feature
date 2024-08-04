Feature: User add new address
@pet
  Scenario Outline: User adds new Pet to Store
  When User add new pet to the store with a custom <id>
  Then Check if the response code is 200 after post
  And Check if the response body is same as request body
  When User calls created pet with <id>
  Then Resposne code is 200 after get
  And Pet attributes are the same as created one <id>
  When User delete added pet from store
  Then Resposne code is 200
  Examples:
  | id           |
  |98989898989898|
  



