Feature: RestfullBooker Booking Test
  @SMOKE
  Scenario Outline: Create new token
    When I create new token with username "<username>" password "<password>"
    Then User must get back a valid status code "200"
    Examples:
      | username | password |
      | admin | password123 |

@SEPP
Scenario: Get all bookings and verify status code
  When User get all booking
  Then User must get back a valid status code "200"

  @SANITY
  Scenario Outline: Create new booking and verify by firstname
    When as user creating new booking with information  firstname "<firstname>" lastname "<lastname>" totalPrice "<totalprice>" depositPaid"<depositpaid>"  additionalneeds "<additionalneeds>"
    Then verify created booking By id
    Examples:
      | firstname | lastname | totalprice | depositpaid |  additionalneeds |
      | Dheyay | Dhradha | 599 | yes |  Vegan BreakFast |


    @REGRESSION
    Scenario Outline: Update created booing and verify it's updated by firstname
      When as user I update created booking with information  firstname "<firstname>" lastname "<lastname>" totalPrice "<totalprice>" depositPaid"<depositpaid>"  additionalneeds "<additionalneeds>"
      Then User must get back a valid status code "200"
      Examples:
        | firstname | lastname | totalprice | depositpaid | additionalneeds |
        | Focus | Dhradha | 599 | yes |  Vegan BreakFast |


@REMOVE
Scenario: Deleting created booking
  When  as user I deleting created booking by id
