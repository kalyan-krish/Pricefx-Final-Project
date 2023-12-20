/**
 * Get BusinessUnit from PriceList Item
 * @param pli
 * @return BusinessUnit
 */

def getBusinessUnit(pli) {

    // BusinessUnit from ProductMasterData from product in PriceList Line Item
    def BusinessUnit = api.find("P",0,1,null,
            Filter.equal("sku",pli.sku))?.find()?.attribute2

    // if BusinessUnit is not null
    if (BusinessUnit != null) {
        return BusinessUnit
    }
}