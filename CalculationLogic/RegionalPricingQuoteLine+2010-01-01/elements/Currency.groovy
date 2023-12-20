/**
 * Currency of Customer Selected in Header Input of Quote Header
 */

 // CustomerId
 String CustomerId = out.CustomerId

 // Currency from Customer Master Table
 def Currency = api.customer("CustomerCurrency",CustomerId)?:null

// if Currency not found
 if (Currency == null) {
     api.addWarning("Currency is not found for the Customer selected")
     return null
 }

 return Currency