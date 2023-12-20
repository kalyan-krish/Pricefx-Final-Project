/**
 * We are Fetching the competitor Record of the product sku from productCompetition Data
 * That Record to latest to TargetDate of sku and price is in ascending order using spaceship operator to sort the records by multiple columns
 */

//TargetDate
def TargetDate = api.targetDate()?.format("yyyy-MM-dd")

def filters = [
        Filter.equal("competitionType", "Online"),
        Filter.lessThan("infoDate", TargetDate)
]

//Competitor Record from ProductCompetition
def competitor =  api.productCompetition(*filters)?.sort { a, b ->
            b.infoDate <=> a.infoDate ?:
            a.price <=> b.price }?.find()

return competitor