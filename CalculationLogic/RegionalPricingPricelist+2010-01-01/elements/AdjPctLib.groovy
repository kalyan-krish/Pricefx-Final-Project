/**
 * Get MarginAdj % value based on productGroup of the product in Context
 * @return MarginAdj %
 */

def GetMarginAdjPct() {

    //productGroup
    def productGroup = api.product("ProductGroup")?:null

    //If productGroup is null
    if (productGroup == null) {
        api.addWarning("ProductGroup is not found for the product So their will be no MarginAdj %")
    }

    //MarginAdj % based on ProductGroup
    def MarginAdj = api.vLookup("MarginAdj",productGroup)?:null

    // if MarginAdj % is null
    if (MarginAdj == null) {
        api.addWarning("MarginAdj % is not found for the productGroup ${productGroup} in the MarginAdj Table")
        return null
    }

    return MarginAdj

}

/**
 * Get AttributeAdj % value based on Product LifeCycle of the product from AttributeAdj Table
 * @return AttributeAdj %
 */

def GetAttributeAdjPct() {

    //ProductLifeCycle
    def productLifeCycle = api.product("ProductLifeCycle")?:null

    // If not found
    if (productLifeCycle == null) {
        api.addWarning("ProductLifeCycle for the Product is not found so their will be no AttributeAdj %")
    }

    //AttributeAdj % value for the ProductLifeCycle from AttributeAdj Table
    def AttributeAdjPct = api.vLookup("AttributeAdj","AttributeAdj",productLifeCycle as String)?:null

    // If not found
    if (AttributeAdjPct == null) {
        api.addWarning("AttributeAdj % value not found for the ProductLifeCycle ${productLifeCycle} in the table")
        return null
    }

    return AttributeAdjPct

}


/**
 * Get PackagingAdj % value based on Size of the product from PackagingAdj Table
 * @return PackagingAdj %
 */

def GetPackagingAdjPct() {

    //ProductLifeCycle
    def productSize = api.product("Size")?:null

    // If not found
    if (productSize == null) {
        api.addWarning("ProductSize for the Product is not found so their will be no PackagingAdj %")
        return null
    }

    //PackagingAdj % value for the Product Size from PackagingAdj Table
    def PackagingAdjPct = api.vLookup("PackagingAdj","PackagingAdj",productSize as String)?:null

    // If not found
    if (PackagingAdjPct == null) {
        api.addWarning("PackagingAdj % value not found for the Product Size ${productSize} in the table")
        return null

    }

    return PackagingAdjPct

}


/**
 * Get TaxTariffAdj % value based on Region from TaxAdj Table
 * @return TaxTariffAdj %
 */

def GetTaxTariffAdjPct() {

    //Region
    def Region = input.Region

    // If not found
    if (Region == null) {
        api.addWarning("Region input is not selected")
        return null
    }

    //TaxAdj % for the Region from TaxAdj Table
    def TaxTariffAdjPct = api.vLookup("TaxAdj",Region as String)?:null

    // If not found
    if (TaxTariffAdjPct == null) {
        api.addWarning("TaxTariffAdj % value not found for the Region ${Region}")
        return null
    }

    return TaxTariffAdjPct

}


