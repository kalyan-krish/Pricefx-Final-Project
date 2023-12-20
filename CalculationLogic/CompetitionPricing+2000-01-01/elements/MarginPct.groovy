/**
 * MarginPct = ("ListPrice" - "ProductCost") / "ListPrice"]
 *
 */

// if any of value is null
if (out.ListPrice == null || out.ProductCost == null) {
    api.addWarning("Unable to calculate Margin Pct missing parameters")
    return null
}

//checking for zero by division
if (out.ListPrice == 0) {
    api.addWarning("Unable to calculate Margin Pct as divide by zero exception")
    return null
}

// Margin %
def MarginPct = (out.ListPrice - out.ProductCost) / out.ListPrice


return MarginPct