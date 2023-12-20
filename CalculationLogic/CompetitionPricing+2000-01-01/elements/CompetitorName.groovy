/**
 * Competitor Name from Competitor Record fetched
 */
def competitorName = out.Competition.competitor

// If not found
if (competitorName == null) {
    api.addWarning("Competitor Name is not found")
    return null
}


return competitorName

