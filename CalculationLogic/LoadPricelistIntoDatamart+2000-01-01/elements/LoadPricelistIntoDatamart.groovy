import com.googlecode.genericdao.search.Filter
//Target
def target = api.getDatamartRowSet("target")

def targetDates = [:]
def priceListIds =[]

//Approved priceList iterator using api.stream
def priceListIterator = api.stream("PL", null, Filter.equal("approvalState", "APPROVED"))
priceListIterator.each { pl ->
    targetDates.put(pl.id,pl.targetDate)
    priceListIds.add(pl.id)
}

priceListIterator?.close()

api.trace("PricelistIds",priceListIds)
api.trace("TargetDates",targetDates)

def newRow
def row
def pid = 0

// iterating through all the approved priceList Ids
for (priceListId in priceListIds) {

    //Streaming over priceList Items of priceList
    def priceListItems = api.stream("PLI","sku",Filter.equal("pricelistId",priceListId))

    // looping over priceListItems to add them into Target DataMart
    while(priceListItems?.hasNext()) {

        row = priceListItems.next() //next Record of priceList Item


        //if any of the field value of priceList Item is not null
        if(!(null in [row.sku,targetDates[priceListId],row.resultPrice,row.currency] )) {
            //new Row
            pid += 1 // Unique id /Primary key for priceList Item

            newRow = [
                    pid: pid,
                    ProductId  : row.sku,
                    TargetDate : targetDates[priceListId],
                    ResultPrice: row.resultPrice,
                    Currency   : row.currency,
                    // "pricelistId" : priceListId
            ]

            //Adding row to Target DataMart
            target?.addRow(newRow)

            //api.trace("row", newRow)
        }
    }

    priceListItems?.close()

}



// TODO place your code here
// HINTS:
// - use only PLIs from Priceslists listed in variable "pricelistIds"
// - target dates of the Pricelists are in variable "targetDates"