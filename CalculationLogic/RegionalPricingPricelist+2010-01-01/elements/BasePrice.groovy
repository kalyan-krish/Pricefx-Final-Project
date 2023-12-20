/**
 * Base Price, defines the average cost of producing the product in EUR currency,
 * utilize existing Product Cost PX table.
 */

// BasePrice or AvgCost from ProductCost PX Table (attribute1 == AvgCost in EUR)
def BasePrice = api.productExtension("ProductCost")?.find()?.attribute1

// If BasePrice is not found
if (BasePrice == null) {
    api.addWarning("BasePrice/AvgCost for the Product is not found or ProductCost PX Table is not found")
    return null
}

// If currency is EUR then return the BasePrice as ProductCost (AvgCost is in EUR)
if (out.Currency == "EUR") {
    return BasePrice
}
// Else if currency is USD then use MoneyUtils LibraryLogic (ExchangeRate) to convert to USD by passing below params
else if (out.Currency == "USD") {
    return libs.MoneyUtils.ExchangeRate.convertMoney(BasePrice as BigDecimal,"EUR","USD")
}