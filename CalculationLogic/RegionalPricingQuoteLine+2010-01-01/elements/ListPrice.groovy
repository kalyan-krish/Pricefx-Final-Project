/**
 * List Price (per unit) = comes from the approved regional Price List data (based on the customer region).
 * Note: Remember to recalculate the price from priceList currency to the customer currency,
 * because, for example, not all countries in European region use EUR.
 */

 // listPrice with default value 0.0
 def ListPrice = 0.0

 // If Region is Europe
 if (out.Region == "Europe") {

     // priceList function return the resultPrice of the product from the selected PriceList
     ListPrice = api.pricelist("Europe")

     if (ListPrice == null) {
         api.addWarning("ListPrice not found for the Product in the Europe PriceList")
         return null
     }
     else {
         if (out.Currency == "EUR")
             api.trace("Region and Currency of Customer are same No Currency Conversion Required")

         // As some of Countries in Europe Use GBP currency so converting into GBP currency from EUR
         else if (out.Currency == "GBP")
             ListPrice = libs.MoneyUtils.ExchangeRate.convertMoney(ListPrice as BigDecimal, "EUR", "GBP")

     }

 }

 // else if Region is America ( As no Country in America Region will use other Currency no Conversion Required)
 else if (out.Region == "America") {

     ListPrice = api.pricelist("America")

     if (ListPrice == null) {
         api.addWarning("ListPrice not found for the product in America PriceList")
         return null
     }

 }

 else {
     api.addWarning("PriceList not available for the Region of the Customer")
     return null
 }

 return ListPrice