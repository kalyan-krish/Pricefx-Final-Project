/**
 * Competitor Product Label from competitor Record fetched
 */


//competitor label
def competitorProductLabel = out.Competition.label

if (competitorProductLabel == null) {
    api.addWarning("No Competitor product label found")
    return null
}

return competitorProductLabel

