/** Sales Discount % = "Sales Discount (per unit)" / "List Price (per unit)"

 Sales Discount (per unit) = difference between the List Price (per unit) and Invoice Price (per unit)

 Generate an Alert on Sales Discount % - based on thresholds found in SalesDiscountThreshold table.
 It depends on Region of a customer (selected by user in the Quote header) and Business Unit of the product
 on the Quote line item. If the calculated Sales Discount % is above the threshold, then the Pricing Manager
 wants to be alerted on the Quote Line, i.e. the Sales Discount % result should have alert (be colored)
 based on the level of severity. For now, consider only the Red alert.
 *
 */

 if (out.InvoicePrice == null || out.ListPrice == null) {
     api.addWarning("Unable to Calculate SalesDiscount and SalesDiscount % as missing in parameters")
     return null
 }

 else {
     // sales Discount
     def SalesDiscount = (out.ListPrice - out.InvoicePrice)

     //checking divide by zero Exception
     if (out.ListPrice == 0) {
         api.addWarning("Unable to Calculate Sales Discount % due to Divide by Zero Exception")
     }
     else {
         // SalesDiscount %
         def SalesDiscountPct = SalesDiscount / out.ListPrice
         // keys
         def keys = [
                 "key2": out.BusinessUnit,
                 "key1": out.Region
         ]

         //RedAlertThreshold
         def RedAlertThreshold = api.vLookup("SalesDiscountThreshold", "RedAlert", keys)
         def YellowAlertThreshold = api.vLookup("SalesDiscountThreshold","YellowAlert",keys)
         def CriticalAlertThreshold = api.vLookup("SalesDiscountThreshold","CriticalAlert",keys)

         // Raising Alerts if SalesDiscount % is > Threshold
         if (SalesDiscountPct > CriticalAlertThreshold)
             api.criticalAlert("Alert: SalesDiscount % is unacceptable high")

         else if (SalesDiscountPct > RedAlertThreshold)
             api.redAlert("Alert : SalesDiscount % is very high")

         else if (SalesDiscountPct > YellowAlertThreshold)
             api.yellowAlert("Alert : SalesDiscount % is high")



         return SalesDiscountPct
     }
 }