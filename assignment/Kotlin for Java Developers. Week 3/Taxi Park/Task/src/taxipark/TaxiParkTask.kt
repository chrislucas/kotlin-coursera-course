package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    allDrivers.subtract(trips.flatMap { listOf(it.driver) })


/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    return if (minTrips <= 0) {
        allPassengers
    } else {
        groupPassengerFilterByMinTrips(minTrips, trips.flatMap trips@{ trip -> trip.passengers })
    }
}

// V1, foi interessante para construir o raciocinio
private fun groupPassengerFilterByMinTrips(
    minTrips: Int,
    passengersWhoHasAtLeatOneTrip: List<Passenger>
): Set<Passenger> {
    return passengersWhoHasAtLeatOneTrip.run {
        val map = mutableMapOf<Passenger, Int>()
        this.forEach {
            map[it] = map[it]?.plus(1) ?: 1
        }
        map
    }.filter { entry ->
        entry.value >= minTrips
    }.keys
}

// v2, mas concisa
private fun functionalGroupPassengerFilterByMinTrips(minTrips: Int, passengers: List<Passenger>) =
    passengers.groupingBy { passenger -> passenger }
        .eachCount()
        .filter { entry -> entry.value >= minTrips }.keys


/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    val gK = { trip: Trip -> trip.driver }
    val gV = { trip: Trip -> trip.passengers }
    val passagers = trips
        .filter { trip -> trip.driver == driver }
        .groupBy(gK, gV)    // map <Driver, List<Set<Passanger>> = motorista -> corridas
        // motoristas que fizeram +1 corrida
        //.filter { entry -> entry.value.size > 1 }
        .flatMap { entry -> entry.value.flatten() } // linearizando a lista de passageiros por motorista de forma a deixa-la numa unica lista
        .groupingBy { passager -> passager }    // agrupando por passageiro
        .eachCount()    // contando cada passageiro
        .filter { entry -> entry.value > 1 }    // mantendo somente os passageiros que aparecem mais de uma vez com o mesmo motorista

    return passagers.keys
}


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    TODO()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return TODO()
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    TODO()
}