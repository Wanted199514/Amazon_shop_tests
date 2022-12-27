Feature: Amazon Tests
  Scenario: Login
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Click on 'Hello, sign in Account & Lists'
    Then Sign in page opened successfully
    When Input into field 'Email or mobile phone number' correct email
    Then Click on 'Continue' button
    When Input into field 'Password' correct password
    Then Click on 'Sign in' button
    And Amazon main page with authorized user opened successfully

  Scenario Outline: Negative Login. Incorrect password
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Click on 'Hello, sign in Account & Lists'
    Then Sign in page opened successfully
    When Input into field 'Email or mobile phone number' email '<email>'
    Then Click on 'Continue' button
    When Input into field 'Password' password '<password>'
    Then Click on 'Sign in' button
    And Error message appeared successfully with text '<error_message>'
    Examples:
      |         email         |  password  |         error_message        |
      |  12345@gmail.com      |  b123456   |  Your password is incorrect  |
      |  12345@gmail.com      |  !@#$%^&   |  Your password is incorrect  |

  Scenario: Search tests. Find books
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Input into 'Search' field 'Books' word
    Then Click 'ENTER' button in search field
    And Search page with correct results opened successfully

  Scenario: Add to Cart
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Input into 'Search' field 'Books' word
    Then Click 'ENTER' button in search field
    And Search page with correct results opened successfully
    When Click on first product
    Then Correct product page opened successfully
    And Remember first item price
    When Click on 'Add to Cart' button
    Then Product added to cart successfully
    When Click on 'Go to Cart' button
    Then Cart with '1' products opened successfully and 'Subtotal (1 item):' text is correct

  Scenario: Delete product from cart
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Input into 'Search' field 'Books' word
    Then Click 'ENTER' button in search field
    And Search page with correct results opened successfully
    When Click on first product
    Then Correct product page opened successfully
    And Remember first item price
    When Click on 'Add to Cart' button
    Then Product added to cart successfully
    When Click on 'Go to Cart' button
    Then Cart with '1' products opened successfully and 'Subtotal (1 item):' text is correct
    And Check total price for one product
    When Click on 'Delete' button
    Then Cart with '0' products opened successfully and 'Subtotal (0 items):' text is correct

  Scenario: Open "Return & Orders" page
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Click on 'Hello, sign in Account & Lists'
    Then Sign in page opened successfully
    When Input into field 'Email or mobile phone number' correct email
    Then Click on 'Continue' button
    When Input into field 'Password' correct password
    Then Click on 'Sign in' button
    And Amazon main page with authorized user opened successfully
    When Click on 'Return & Orders' page
    Then 'Return & Orders' page opened successfully

  Scenario: Search tests. Search hint
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Input into 'Search' field 'Corsair' word
    Then Search hint 'corsair' opened successfully

  Scenario: Check navigation shop links
    When Go to Amazon page
    Then Amazon main page opened successfully
    And Check navigation shop links is '30'
    Then Navigation shop links '30' is corrected

  Scenario: Add to Cart two items
    When Go to Amazon page
    Then Amazon main page opened successfully
    When Input into 'Search' field 'Books' word
    Then Click 'ENTER' button in search field
    And Search page with correct results opened successfully
    When Click on first product
    Then Correct product page opened successfully
    And Remember first item price
    When Click on 'Add to Cart' button
    Then Product added to cart successfully
    When Back to past page
    Then Search page with correct results opened successfully
    When Click on second product
    Then Correct product page opened successfully
    And Remember second item price
    When Click on 'Add to Cart' button
    Then Products added to cart successfully
    When Click on 'Go to Cart' button
    Then Cart with '2' products opened successfully and 'Subtotal (2 items):' text is correct
    And Check total price for two products