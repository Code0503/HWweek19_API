Feature: Products Features
  @SMOKE
  Scenario: Getting list of products
    When User get all list of products
    Then verify status code is "200"
    @SANITY
  Scenario Outline: creating BestBuy new product
    When I create new product detail by name "<name>" type "<type>" price "<price>" upc "<upc>" shipping "<shipping>" description "<description>"  manufacturer "<manufacturer>" model "<model>" url "<url>"
    Then verify status code is "201"
    Examples:
      | name | type | price | upc | shipping | description | manufacturer | model | url |
      | Selenium Batteries | Lithium | 10 | Code | 12 |  unit with charging dock | Automation Ltd | Auto |http://www.bestbuy.com/site/energizer-max-batteries-aa-4-pack/150115.p?id=1051384046217&skuId=150115&cmp=RMXTD |
     @REGRESSION
      Scenario: Get created booking by ID
        When user I getting created product list by ID

@INTERESTING
       Scenario Outline: Updating details in created product
         When I user updating product details by name "<name>" type "<type>" price "<price>" upc "<upc>" shipping "<shipping>" description "<description>"  manufacturer "<manufacturer>" model "<model>" url "<url>"
         Then verify status code is "200"
         Examples:
           | name | type | price | upc | shipping | description | manufacturer | model | url |
           | Selenium Java Batteries | Lithium | 1 | Code | 12| unit with charging dock| Automation Ltd | Auto Product No. |http://www.bestbuy.com/site/energizer-max-batteries-aa-4-pack/150115.p?id=1051384046217&skuId=150115&cmp=RMXTD |
  @DELETE
  Scenario: user is deleting created product
    When I delete created product by product id
    And verify status code is "200"
    Then try to get created product by ID and Verify status code "404"
