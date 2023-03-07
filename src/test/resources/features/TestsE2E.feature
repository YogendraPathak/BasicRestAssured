Feature: Tests for learning
Description: Purpose of these tests is to learn rest assured

Background: User generates token for authorization
		Given I am an authorized user
		
  Scenario: Authorized user should be able to get book list
    #Given User accesses the book services
    When I fetch book list using 'get' services
    Then I verify status code of response
    
   #Scenario: Authorized user should be able to get book info
    #Given User accesses the book services
    #When I fetch book info using 'get' services for isbn '9781449325862'
    #Then I verify status code '200' 
    
#    And check more outcomes
#
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
