/**
 * Total Invoice Price = Invoice Price (per unit) * Quantity
 */
 // if any of value is null
 if (out.InvoicePrice == null || out.Quantity == null) {
     api.addWarning("Unable to Calculate TotalInvoicePrice as missing in parameter(s)")
     return null
 }


 return (out.InvoicePrice * out.Quantity)
