/**
 * Margin (per unit) = Invoice Price (per unit) - Cost (per unit)
 */

// if any of value is null raising warning
if (out.InvoicePrice == null || out.Cost == null) {
    api.addWarning("Margin unable to calculate missing in parameters")
    return null
}


return (out.InvoicePrice - out.Cost) // Margin (per unit)