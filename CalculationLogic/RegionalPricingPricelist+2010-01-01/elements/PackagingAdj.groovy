/**
 * Calculate PackagingAdjAbs value using below formula:
 * Get PackagingAdj % from the function defined
 * Calculate PackagingAdjAbs = Base Price * Packaging Adjustment %
 * @ return PackagingAdjAbs
 */

// PackagingAdj %
def PackagingAdjPct = AdjPctLib.GetPackagingAdjPct()


// PackagingAdjAbs

if (!(null in [out.BasePrice,PackagingAdjPct])) {
    def PackagingAdjAbs = (out.BasePrice * PackagingAdjPct)


    return PackagingAdjAbs

}