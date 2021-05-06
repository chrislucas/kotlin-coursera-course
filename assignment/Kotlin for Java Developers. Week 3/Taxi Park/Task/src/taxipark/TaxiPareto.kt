package taxipark

fun TaxiPark.checkParetoPrincipleV1(): Boolean {
    if (trips.isEmpty()) return false

    //val totalIncome = trips.sumByDouble { trip -> trip.cost }
    val totalIncome = trips.sumByDouble(Trip::cost)

    val sortedDriversIncome: List<Double> = trips
        .groupBy(Trip::driver)
        .map { (_, tripsByDriver) -> tripsByDriver.sumByDouble(Trip::cost) }
        .sortedDescending()

    val numberOfTopDrivers = (0.2 * allDrivers.size).toInt()

    val incomeByTopDrivers = sortedDriversIncome
        .take(numberOfTopDrivers)
        .sum()
    // 20% dos motoristas realizaram 80% ou mais dos ganhos em todas as corrigas registradas
    return incomeByTopDrivers >= 0.8 * totalIncome
}

