/**
 * Currency, finds the corresponding currency for a Region
 *   Steps:
 *         1. Use Company Parameter Table Region to get the Currency based on Region Selected
 *         2. Use any function vLookup or findLookupTableValues to fetch the data
 *         3. Compare with Input Region and return Currency
 *
 */

// Region Input
def Region = input.Region

// Currency from Region Table for the Given Region using VLookUp
def Currency = api.vLookup("Region","Currency",Region as String)?:null

// If Currency not found
if (Currency == null) {
    api.addWarning("Currency not found in Region Table for the given Region or Region input value not Selected")
    return null
}

return Currency