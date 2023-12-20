/**
 * Implement logic to be able to create new price lists for both American and European pricing and
 * link to the Groovy pricing logic.European price list will utilize EUR currency and American price list will use USD currency.

 * Declare Region Input to configure PriceList based on Region
 Region input, given by the user on PriceList UI panel, determines which PriceList to generate

 */

  // Input Generation Mode
  if (api.isInputGenerationExecution()) {

      // Region
      api.inputBuilderFactory().createOptionEntry("Region")
                               .setOptions(["Europe","America"]) // options
                               .setRequired(true) // mandatory
                               .getInput()

  }


