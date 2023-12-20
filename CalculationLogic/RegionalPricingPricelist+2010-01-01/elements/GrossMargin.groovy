/** Calculate Gross Margin % using below formula:
 *
 * Gross Margin % = (ListPrice - BasePrice ) / ListPrice
 * @return Gross Margin %
 */

// Raise Warning
 if (null in [out.BasePrice, out.ListPrice]) {
     api.addWarning("Unable to calculate Gross Margin % : missing in parameters")
     return null
 }

 // Gross Margin %
 def GrossMarginPct =  ((out.ListPrice - out.BasePrice) / out.ListPrice)

 //Fetching Thresholds to Raise Alerts if Gross Margin <= Threshold
 def BusinessUnit = api.product("BusinessUnit")?:null
 if (BusinessUnit == null) {
     api.addWarning("BusinessUnit not found for the product Unable to Check for Thresholds and raise Alerts")
     return null
 }
 api.trace(BusinessUnit)

 def RedThreshold = api.vLookup("PriceStrategy","RedAlert",BusinessUnit as String)
 def YellowThreshold = api.vLookup("PriceStrategy","YellowAlert",BusinessUnit as String)
 def CriticalThreshold = api.vLookup("PriceStrategy","CriticalAlert",BusinessUnit as String)

 if (GrossMarginPct <= CriticalThreshold) {
     api.criticalAlert("Gross Margin % is unacceptable low")
 }
 else if (GrossMarginPct <= RedThreshold) {
     api.redAlert("Gross Margin % is very low")
 }
 else if (GrossMarginPct <= YellowThreshold) {
     api.yellowAlert("Gross Margin % is low")
 }

 return GrossMarginPct

