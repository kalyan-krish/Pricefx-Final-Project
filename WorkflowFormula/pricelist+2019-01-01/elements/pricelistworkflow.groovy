/**
 As a Product Manager, I want to have an Approval Workflow for a Pricelist, to be able to review the newly calculated Pricelist prices before exporting to ERP system.
 Details:

 The approval process should have 2 steps, one Watcher and one Approver.
 1st approval step:
         Only watcherShould be anyone from the Pricing Manager user group to be notified about new pricelists

 2nd approval step:
           A 2nd approval step is needed only if any line of the Pricelist has expected Margin% less than the threshold (found in the provided PP PricelistApprovalLevel).
 If a threshold value is not specified for certain product’s Business Unit, then we do not need to validate such product.
 This step should be approved by 2 approvers from the Product Manager user group.
 */

//api.trace("pricelist",pricelist)

// PriceListLineItems for the PriceList from Context
def priceListLineItems = api.stream("PLI","sku",Filter.equal("pricelistId",pricelist.id))


// Declaring Variables outside closure
def marginPct
def BusinessUnit
def MarginThreshold
def filters
// To Fetch Table Id of PriceListApprovalLevels Parameters Table
def PriceListApprovalLevels = api.findLookupTable("PriceListApprovalLevels")
def ApprovalWorkflowRequired = false // flag variable

priceListLineItems?.each { lineItem ->

    //api.trace("lineItem",lineItem)

    marginPct = GetMarginpct.getMarginPct(lineItem) // Margin %


    BusinessUnit = GetBusinessUnit.getBusinessUnit(lineItem) // BusinessUnit

    filters = [
            Filter.equal("lookupTable.id",PriceListApprovalLevels?.id),//table id
            Filter.equal("BusinessUnit",BusinessUnit) // BusinessUnit
    ]

    // Margin Threshold from PriceListApprovals Table
    MarginThreshold = api.find("MLTV",0,1,null,*filters)?.find()?.attribute1

    // If Margin Threshold is null skip
    if (MarginThreshold == null) {
        // skip the current priceList LineItem as Margin Threshold not available
        return
    }

    // if margin %  <  Threshold
    if (marginPct < MarginThreshold) {
        ApprovalWorkflowRequired = true // flag variable = true
    }


   //api.trace("MarginThreshold",MarginThreshold)

   //api.trace("BusinessUnit",BusinessUnit)

   //api.trace("marginPct",marginPct)

   //api.trace("Approvalneeded",ApprovalWorkflowRequired)

}

priceListLineItems?.close() // closing closure

// PriceList created notification goes for the watcher group (Price Manager)
workflow.addWatcherStep("Pricing Manager")
        .withUserGroupWatchers("PriceManager")
        .withReasons("Anyone of Pricing Manager Group need to be notified")


//api.trace("ApprovalStatus",ApprovalWorkflowRequired)

// If Flag = true means Approval need Approval Request goes for ProductManager
if (ApprovalWorkflowRequired == true) {
    workflow.addApprovalStep("Product Managers")
            .withUserGroupApprovers("ProductManager")
            .withReasons("Minimum 2 Approvals of Product Manager Group is Needed")
            .withMinApprovalsNeeded(2)
}

