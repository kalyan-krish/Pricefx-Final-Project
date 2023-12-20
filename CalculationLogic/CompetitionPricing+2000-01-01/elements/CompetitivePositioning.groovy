/**
 * CompetitivePositioning = from provided Price Parameter, based on Business Unit of the SKU
 */

//BusinessUnit of Product
def BusinessUnit = api.product("BusinessUnit")?:null

// If BU is null
if (BusinessUnit == null) {
    api.addWarning("Business unit is not found for the product")
    return null
}


else {
    def keys = [
            Filter.equal("name",BusinessUnit)
    ]
    // competitivePositioning value from competition strategy table with keys
    def CompetitivePositioning = api.findLookupTableValues("CompetitionStrategy","attribute1",*keys)?.find()?.attribute1

    if (CompetitivePositioning == null) {
        api.addWarning("Competitive Positioning is not available for Product Business Unit")
        return null
    }

    return CompetitivePositioning
}

