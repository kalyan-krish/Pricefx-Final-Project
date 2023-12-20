/**
 * ProductCost from PX Table ProductCost
 */

def productCost = api.productExtension("ProductCost")?.find()?.attribute1

if (productCost == null) {
    api.addWarning("Product Cost is missing for the Product")
    return null
}

return productCost