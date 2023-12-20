/**
 * Margin % = "Margin (per unit)" / "ListPrice (per unit)"
 *
 */

// if any of value is null raising warning
if (out.Margin == null || out.ListPrice == null) {
    api.addWarning("Margin % unable to calculate missing in parameters")
    return null
}

//Divide by Zero Exception check
if (out.ListPrice == 0) {
    api.addWarning("Margin % unable to calculate due to Zero Division Exception")
    return null
}

// Margin %
return (out.Margin / out.ListPrice)


