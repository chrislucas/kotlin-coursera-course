package taxipark

/**
 * Esse arquivo contem as solucoes de cada tarefa vindo dos instrutores do curso
 * Isso vai servir de comparacao com as minhas proprias solucoes que enviei para
 * os avaliadores, com o objetivo de aprender mais
 * */

// Task 1
// esssa eh a minha solucao
fun TaxiPark.subtractFakeDrivers(): Set<Driver> =
    //allDrivers.subtract(trips.flatMap { listOf(it.driver) })
    allDrivers.subtract(trips.map { it.driver })

// Task 2
/**
 * Essa solucao tem um melhor desempenho visto que com uma unica iteracao
 * (map {} ) montamos uma lista de motoristas e e aplicamos  uma operacao
 * de subtracao entre os conjuntos
 * */
fun removeFakeDrivers(taxiPark: TaxiPark): Set<Driver> =
    taxiPark.run {
        // Set(All Drivers) - Set (Drivers Who did some Trip)
        //allDrivers.minus(trips.map { it.driver })
        allDrivers - trips.map { it.driver }
    }

// Task 2
/**
 * Aqui precisamos de 2 iteracoes aninhadas, uma para iterar por cada motorista
 * e a segunda por cada viagem w verifcar qual o motorista que a realizou para poder
 * remove-lo do conjunto de todos os possiveis motoristas
 * */
fun filterFakeDrivers(taxiPark: TaxiPark): Set<Driver> =
    taxiPark.run {
        // Set(All Drivers) filter -> Set (Drivers Who did some Trip)
        allDrivers.filter { driver ->
            trips.none { trip -> trip.driver == driver }
        }.toSet()
    }

// Task 3
fun TaxiPark.findPassengerWhoCompletedAtLeastXTrips(min: Int) : Set<Passenger> =
    run {
        trips.flatMap { trip -> trip.passengers }   // lista de passageiros que estiveram em uma viagem
            .groupBy passenger@{ it } // Map<Passanger, List<Passenger>>
            .filter { it.value.size >= min } // manter somente passageiros que fizeram ao menos uma quantidade MIN de fviage
            .keys
    }

// Task 3
fun TaxiPark.findPassengerWhoCompletedAtLeastXTripsV2(min: Int) : Set<Passenger> =
    run {
        allPassengers.partition passenger@{
            passenger ->
            trips.sumBy trip@{
                trip ->
                if (passenger in trip.passengers) 1 else 0
            } >= min
        }
        setOf()
    }