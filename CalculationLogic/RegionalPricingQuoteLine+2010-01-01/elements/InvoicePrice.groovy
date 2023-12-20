/**
 *
 * Invoice Price (per unit) = Requested Price,
 * but only if the Requested price is entered, otherwise it will be the same as List Price
 * @return InvoicePrice
 */


 def InvoicePrice = 0.0

 // If RequestedPrice is entered InvoicePrice = Requested Price
 if (out.RequestedPrice != null) {

      InvoicePrice = out.RequestedPrice

 }

 // Else InvoicePrice = ListPrice
 else {

      InvoicePrice = out.ListPrice

 }

 return InvoicePrice as BigDecimal
