/**
 * Calculate AttributeAdjAbs value using below formula:
 * Get AttributeAdj % from the function defined
 * Calculate AttributeAdjAbs = Base Price * Attribute Adjustment %
 * @ return AttributeAdjAbs
 */

// AttributeAdj %
def AttributeAdjPct = AdjPctLib.GetAttributeAdjPct()

// AttributeAdjAbs

if (!(null in [out.BasePrice,AttributeAdjPct])) {
    def AttributeAdjAbs = (out.BasePrice * AttributeAdjPct)

    return AttributeAdjAbs

}