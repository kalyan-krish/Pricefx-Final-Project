/**
 *HistoricalQty = get historical sum of total quantity of this Product
 *                           sold to this Customer (retrieve from transactional history).
 *
 * @returnHistoricalQty
 */

 //DataMart Context
 def dmCtx = api.getDatamartContext()

 //DataMart
 def DataMart = dmCtx.getDatamart("Transaction")?:null

 if (DataMart == null) {
     api.addWarning("Transaction DataMart is not found")
     return null
 }

 // Filters
 def filters = [
        Filter.equal("ProductId",out.ProductId),
        Filter.equal("CustomerId",out.CustomerId)
 ]

 // New Query with rollup
 def DataQuery = dmCtx.newQuery(DataMart,true)

 DataQuery.select("SUM(Quantity)","TotalQuantity")

 DataQuery.where(*filters)

 // ExecuteQuery
 def QueryResult = dmCtx.executeQuery(DataQuery)

 // TotalQuantity sold to Customer
 def HistoricalQuantity = QueryResult?.getData()?.find()?.TotalQuantity

 return HistoricalQuantity



