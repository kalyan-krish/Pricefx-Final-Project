/** To Calculate MarginAdjAbs value use below formula:
 *  Get the MarginAdj % based on productGroup of the Product from Function defined
 *  Then Calculate MarginAdj Absolute = BasePrice * MarginAdj %
 *  @return MarginAdjAbs
 */

// MarginAdj %
def MarginAdjPct = AdjPctLib.GetMarginAdjPct()


if (!(null in [out.BasePrice,MarginAdjPct] ) ){
  // MarginAdjAbs
    def MarginAdjAbs = out.BasePrice * MarginAdjPct


    return MarginAdjAbs

}