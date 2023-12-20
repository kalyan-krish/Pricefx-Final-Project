/** Calculate ListPrice using below formula:

 * List Price = Base Price + Margin Adj + Attribute Adj + Packaging Adj +TaxTariff Adj
 * @return ListPrice
 */


if (null in [out.BasePrice,out.MarginAdj,out.PackagingAdj,out.AttributeAdj,out.TaxTariffAdj]) {
    api.addWarning("Unable to calculate List Price: missing in parameters")
    return null
}

//listPrice
def ListPrice = (out.BasePrice + out.MarginAdj + out.AttributeAdj + out.PackagingAdj + out.TaxTariffAdj)


return ListPrice
