/**
 * Margin % - i.e. overall quote margin %, computed as
 * "Sum(MarginPerUnit * Quantity)"/"Sum(InvoicePricePerUnit * Quantity)"
 * You are summarizing data from across all line items.
 * MarginPerUnit is a margin of the line item (in money per unit).
 * Quantity is the product quantity given by sales rep on the line item.
 * InvoicePricePerUnit is the Invoice Price (in money per unit) evaluated on the line item.
 */

  // checking if PrePhase Return
  if (quoteProcessor.isPrePhase()) {
      return
  }

  // PostPhase
  // Fetching LineItems from QuoteView of Current Quote except the folders
  def lineItems = quoteProcessor.quoteView.lineItems.findAll() {
      !it.folder
  }

  // Declaring variables useful inside loop
  def MarginPerUnit = 0.0
  def Quantity
  def InvoicePricePerUnit = 0.0
  def SumOfMarginPerUnitQuantity = 0.0
  def SumOfInvoicePricePerUnitQuantity = 0.0

  // Looping through lineItems
  for (lineItem in lineItems) {

      //Margin of each lineItem
      MarginPerUnit  = lineItem?.outputs?.find() { row ->
          row.resultName == "Margin"
      }?.result

      //InvoicePrice of each lineItem
      InvoicePricePerUnit = lineItem?.outputs?.find() { row ->
          row.resultName == "InvoicePrice"
      }?.result

      //Quantity of each lineItem
      Quantity = lineItem?.inputs?.find() {row ->
          row.name == "Quantity"
      }?.value

      //Summing up above values according to formula
      SumOfInvoicePricePerUnitQuantity += (InvoicePricePerUnit * Quantity)
      SumOfMarginPerUnitQuantity += (MarginPerUnit * Quantity)

  }

  // Divide by Zero Exception
  if (SumOfInvoicePricePerUnitQuantity == 0) {
      api.addWarning("Unable to calculate OverAllMargin % due to Divide by Zero Exception")
      return null
  }

  api.trace(SumOfInvoicePricePerUnitQuantity)
  api.trace(SumOfMarginPerUnitQuantity)
  //OverAllMargin %
  def OverAllMarginPct = (SumOfMarginPerUnitQuantity)/(SumOfInvoicePricePerUnitQuantity)

  api.trace(OverAllMarginPct)

  def HeaderOutput = [

          resultName : "OverAllMarginPct",
          resultLabel : "OverAllMargin %",
          result : OverAllMarginPct,
          formatType : "PERCENT",
          resultType : "SIMPLE"
  ]

  //Adding Output to Header of Quote
  quoteProcessor.addOrUpdateOutput(HeaderOutput)