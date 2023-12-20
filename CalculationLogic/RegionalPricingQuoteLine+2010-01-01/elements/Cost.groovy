

/**
 *
 * Cost = Average Cost of producing the product
 From the Product Cost table. The cost is stored in EUR currency,
 so remember to recalculate to the customer currency, before using for further calculations
 */

 //AverageCost from ProductCost of Product
 def Cost = api.productExtension("ProductCost")?.find()?.attribute1
 // If cost is null
 if (Cost == null) {
     api.addWarning("AverageCost not found in PX Table ProductCost for the product")
     return null
 }

 else {
     // Conversion to GBP from EUR
     if (out.Region == "Europe" && out.Currency == "GBP")
         return libs.MoneyUtils.ExchangeRate.convertMoney(Cost as BigDecimal,"EUR","GBP")

     // Conversion to USD from EUR
     else if (out.Region == "America" && out.Currency == "USD")
         return libs.MoneyUtils.ExchangeRate.convertMoney(Cost as BigDecimal,"EUR","USD")

     // Returning Cost in EUR
     else if (out.Region == "Europe" && out.Currency == "EUR")
         return Cost
 }