

/**
 * Region of Customer Selected in Header InPut of Quote Header
 */

// CustomerId
 String CustomerId = out.CustomerId

// Region of Customer from Customer Master data
 def Region = api.customer("Region",CustomerId)?:null

// If Region not found
 if (Region == null) {
     api.addWarning("Region is not found for the Customer")
     return null
 }

 return Region
