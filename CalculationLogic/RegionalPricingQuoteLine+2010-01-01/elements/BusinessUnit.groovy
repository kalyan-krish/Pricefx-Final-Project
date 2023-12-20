/**
 * Business Unit of the Product from product Master Table
 */

// BusinessUnit
 String BusinessUnit = api.product("BusinessUnit")?:null

 //if not found
 if (BusinessUnit == null) {
     api.addWarning("Business Unit for the product is not found")
     return null
 }

 return BusinessUnit