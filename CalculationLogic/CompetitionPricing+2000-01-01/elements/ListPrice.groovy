/**
 CompetitivePositioning = from provided Price Parameter, based on Business Unit of the SKU
 CompetitorProductPrice = from competitor record of the product
 ListPrice = CompetitionProductPrice * CompetitivePositioning
 */

// if any of value is null
if (out.CompetitivePositioning == null || out.CompetitorProductPrice == null) {
    api.addWarning("List Price unable to calculate as missing in parameters")
    return null
}

//list Price
BigDecimal listPrice = (out.CompetitorProductPrice * out.CompetitivePositioning)

return listPrice