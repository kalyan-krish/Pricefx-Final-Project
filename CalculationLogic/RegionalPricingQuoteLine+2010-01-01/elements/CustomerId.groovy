
// Customer Id of Customer selected in Header Input of Quote

 def HeaderInputCustomer = input.Customer

 if (api.isDebugMode()) {
     HeaderInputCustomer = "CD-00003"
 }

 return HeaderInputCustomer

