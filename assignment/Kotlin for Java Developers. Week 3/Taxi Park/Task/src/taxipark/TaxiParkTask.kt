package taxipark

import kotlin.math.abs
import kotlin.math.roundToInt

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    removeFakeDrivers(this)


/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    return if (minTrips <= 0) {
        allPassengers
    } else {
        // findPassengerWhoCompletedAtLeastXTrips(minTrips)
        // groupPassengerFilterByMinTrips(minTrips, trips.flatMap trips@{ trip -> trip.passengers })
        // functionalGroupPassengerFilterByMinTrips(minTrips)
        // findPassengerWhoCompletedAtLeastXTrips(minTrips)
        findPassengerWhoCompletedAtLeastMinQuantityOfTrips(minTrips)
    }
}

// TASK 2
// V1, foi interessante para construir o raciocinio
private fun groupPassengerFilterByMinTrips(
    minTrips: Int,
    passengersWhoHasAtLeastOneTrip: List<Passenger>
): Set<Passenger> {
    return passengersWhoHasAtLeastOneTrip.run {
        val map = mutableMapOf<Passenger, Int>()
        this.forEach {
            map[it] = map[it]?.plus(1) ?: 1
        }
        map
    }.filter { entry ->
        entry.value >= minTrips
    }.keys
}

// TASK 2
// v2, mas concisa
private fun TaxiPark.functionalGroupPassengerFilterByMinTrips(minTrips: Int) =
    trips.flatMap trips@{ trip -> trip.passengers }
        .groupingBy passenger@{ it }
        .eachCount()
        .filter { entry -> entry.value >= minTrips }
        .keys


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
        val passengers = trips
            .filter { trip -> trip.driver == driver }
            .groupBy(gK, gV)    // map <Driver, List<Set<Passanger>> = motorista -> corridas
            // motoristas que fizeram +1 corrida
            //.filter { entry -> entry.value.size > 1 }
            .flatMap { entry -> entry.value.flatten() } // linearizando a lista de passageiros por motorista de forma a deixa-la numa unica lista
            .groupingBy { passager -> passager }    // agrupando por passageiro
            .eachCount()    // contando cada passageiro
            .filter { entry -> entry.value > 1 }
        passengers.keys
    }
}


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    return smartPassenger(this)
}

// TODO corrigir esse metodo
private fun smartPassenger(taxiPark: TaxiPark): Set<Passenger> {
    return taxiPark.run {
        val mapPassengerDiscount = mutableMapOf<Passenger, Int>()

        trips.flatMap trip@{ trip -> listOf(trip.passengers to trip.discount) }
            .map { (passengers, pDiscount) ->
                passengers.forEach {
                    if (pDiscount == null) {
                        mapPassengerDiscount[it] = mapPassengerDiscount[it]?.minus(1) ?: -1
                    } else {
                        mapPassengerDiscount[it] = mapPassengerDiscount[it]?.plus(1) ?: 1
                    }
                }
            }

        val smartPassenger = mapPassengerDiscount.filter { it.value > 0 }

        smartPassenger.keys
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
            val s = duration - (duration % 10)
            val e = duration - (duration % 10) + 9
            val range = IntRange(s, e)
            mapRange[range] = mapRange[range]?.plus(1) ?: 1
        }
        // o compilador do curso nao esta aceitando essa instrucao
        // provavelmente por conta da versao do compilador kotlin
        //mapRange.maxByOrNull { it.value }?.key

        mapRange.maxBy { it.value }?.key
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean =
    this.checkV1()


fun TaxiPark.checkV1(): Boolean {

    if (trips.isEmpty())
        return false

    val contributionPerDriver = mutableMapOf<Driver, Double>()

    this.trips.forEach {
        contributionPerDriver[it.driver] =
            contributionPerDriver[it.driver]?.plus(it.cost) ?: it.cost
    }
    val incomming80Percent = this.trips.map { trip -> trip.cost }.sum() * .8

    val drivers20Percent = (this.allDrivers.size * .2).roundToInt()

    val s = contributionPerDriver.entries
        .sortedByDescending { it.value }            // ordenar pelos motoristas que mais contribuiem
        .slice(0 until drivers20Percent)    // fazer um recorto de 20% dos motoristas que contribuiram com corridas
        .sumByDouble { it.value }   // efetuar um somatorio


    return s >= incomming80Percent  // verificar se os 20% motoristas que mais receberam contribuiem com 80% o mais do somatorio
}

private fun Double.almostEquals(that: Double, diff: Double) = abs(that - this) <= diff