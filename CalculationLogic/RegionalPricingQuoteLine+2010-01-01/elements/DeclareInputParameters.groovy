
// Declaring Quantity Input and Requested Price for each QuoteLine Item

if (api.isInputGenerationExecution()) {

    //Quantity
    api.inputBuilderFactory().createIntegerUserEntry("Quantity")
            .setLabel("Required Quantity")
            .setRequired(true)
            .setValue(1)
            .getInput()

    def QuantityParam = api.getParameter("Quantity")

    //Set limits > 1
    QuantityParam.setConfigParameter("inputType","range")
    QuantityParam.setConfigParameter("from",1)


    //RequestedPrice

    api.inputBuilderFactory().createUserEntry("RequestedPrice")
            .setLabel("Requested Price")
            .setRequired(false)
            .getInput()

    def PriceParam = api.getParameter("RequestedPrice")

    //Set Limit > 0 and DataType
    PriceParam.setConfigParameter("dataType","float")
    PriceParam.setConfigParameter("from",0.0)


}
