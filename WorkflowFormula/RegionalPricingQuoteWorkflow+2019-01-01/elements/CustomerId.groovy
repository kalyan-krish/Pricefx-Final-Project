// Customer Id of Customer selected in Quote Header

def Customer = quote?.inputs?.find() {row ->
    row.name == "Customer"
}?.value


return Customer
