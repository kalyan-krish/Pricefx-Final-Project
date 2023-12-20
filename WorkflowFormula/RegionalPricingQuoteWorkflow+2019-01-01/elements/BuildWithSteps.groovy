/**
 * Differentiate between American and European Quotes and use the following criteria for creation of the workflow steps. If the Quote Total Invoice Price is over the given threshold, then given user group must be asked for approval.

 The thresholds are provided in a Price Parameter table, here we show it only for reference.

 NOTE
 the values are in local currency.
 America:

 > 5,000 ⇒ Sales Manager

 > 10,000 ⇒ VP Sales

 > 25,000 ⇒ CFO

 Europe:

 > 5,000 ⇒ Sales Manager

 > 10,000 ⇒ VP Sales

 > 50,000 ⇒ Director of Intl Sales

 > 100,000 ⇒ CFO

 Note: Verify that the appropriate User Groups exist (and their proper naming) in the User Administration component of the partition.


 */
//api.trace("Quote",quote)

// TotalInvoicePrice from Quote header
def TotalInvoicePrice = quote?.outputs?.find() { row ->
    row.resultName == "TotalInvoicePrice"
}?.result


//api.trace("TotalInvoicePrice",TotalInvoicePrice)

//Filters
def filters = [
        Filter.equal("Region",out.Region), // Region
        Filter.lessOrEqual("attribute2",TotalInvoicePrice) // TotalInvoicePrice
]

//Fetching ApprovalRecords less than or equal to TotalInvoicePrice and Region in filters sort by Descending order of Threshold
//Fetch first record from that gives specific Approval required
def Approval = api.findLookupTableValues("ApprovalThreshold","-attribute2",*filters)?.find()

// If Approval is null then no Approval required for the Quote
if (Approval == null) {
    api.trace("Approval Record not found in parameters table: No approval Required for Quote")
    return null
}

//ApprovalThreshold
def ApprovalThreshold = Approval?.attribute2
//ApprovalUserGroup
def ApprovalUserGroup = Approval?.attribute1

// if TotalInvoicePrice > ApprovalThreshold
if (TotalInvoicePrice > ApprovalThreshold) {
    workflow.addApprovalStep("ApprovalRequired")
            .withUserGroupApprovers(ApprovalUserGroup)
            .withReasons("${ApprovalUserGroup} Approvals are Required because TotalInvoicePrice ( ${TotalInvoicePrice} ) > ApprovalThreshold (${ApprovalThreshold})")
            .withMinApprovalsNeeded(2)
}



