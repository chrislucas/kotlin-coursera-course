package taxipark

import org.w3c.dom.ranges.Range

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
    return frequentPassengers(this, driver)
}

// separando numa funcao para depois comparar com outras possiveis solucoes mais simples
private fun frequentPassengers(taxiPark: TaxiPark, driver: Driver): Set<Passenger> {
    return taxiPark.run {
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
            .filter { entry -> entry.value > 1 }
        passagers.keys
    }
}


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    return smartPassenger(this)
}

private fun smartPassenger(taxiPark: TaxiPark): Set<Passenger> {
    return taxiPark.run {
        val smartPassenger = mutableMapOf<Passenger, Double>()
        var maxValue = 0.0
        this.trips
            .filter { trip -> trip.discount ?: 0.0 > 0.0 }
            .forEach { trip ->
                val discount = trip.discount ?: 0.0
                trip.passengers.forEach {
                    smartPassenger[it] = smartPassenger[it]?.plus(discount) ?: discount
                    if (maxValue < smartPassenger[it] ?: 0.0)
                        maxValue = smartPassenger[it] ?: 0.0
                }
            }
        smartPassenger.filter { it.value == maxValue }.keys
    }
}


/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return theMostFrequentTripDurationPeriod(this)
}

private fun theMostFrequentTripDurationPeriod(taxiPark: TaxiPark): IntRange? {
    return taxiPark.run {
        val mapRange = mutableMapOf<IntRange, Int>()
        this.trips.forEach { trip ->
            val duration = trip.duration
            val s = duration - (duration  % 10)
            val e = duration - (duration  % 10) + 9
            val range = IntRange(s, e)
            mapRange[range] = mapRange[range]?.plus(1) ?: 1
        }
        mapRange.maxByOrNull { it.value }?.key
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    TODO()
}