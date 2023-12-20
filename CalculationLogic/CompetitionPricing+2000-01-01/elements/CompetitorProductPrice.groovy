/**
 * Competitor Price from competitor Record fetched
 */

//competitorPrice of the product
def competitorPrice = out.Competition.price

// if competitor price is not found
if (competitorPrice == null) {
    api.addWarning("No Competitor price found")
    return null
}

return competitorPrice
