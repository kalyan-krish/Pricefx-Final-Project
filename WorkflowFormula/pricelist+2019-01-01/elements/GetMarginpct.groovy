/**
 * Calculates expected profitability ( Margin % ) of the given Price List Item (PLI)
 *
 * @param pli Object (as Map) of PriceList Line Item (i.e. single row of the PriceList)
 * @return the calculated expected profitability ( Margin % ) of the line
 */

def getMarginPct(pli) {
    def basePrice = pli.calculationResults?.find { it.resultName == "BasePrice" }?.result // BasePrice
    def listPrice = pli.calculationResults?.find { it.resultName == "ListPrice" }?.result // ListPrice
    def marginPct = null
    if (listPrice && basePrice != null) {
        marginPct = (listPrice - basePrice) / listPrice  // Margin %
    }
    return marginPct?:0.0
}