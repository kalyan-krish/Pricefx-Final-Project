
/**
 * Calculate TaxTariffAdjAbs value using below formula:
 * Get TaxTariffAdj % from the function defined
 * Calculate TaxTariffAdjAbs = Base Price * TaxTariff Adjustment %
 * @ return TaxTariffAdjAbs
 */

// TaxTariffAdj %
def TaxTariffAdjPct = AdjPctLib.GetTaxTariffAdjPct()

// TaxTariffAdjAbs

if (!(null in [out.BasePrice,TaxTariffAdjPct])) {
    def TaxTariffAdjAbs = (out.BasePrice * TaxTariffAdjPct)


    return TaxTariffAdjAbs
}