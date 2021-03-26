package taxipark

/**
 * Esse arquivo contem as solucoes de cada tarefa vindo dos instrutores do curso
 * Isso vai servir de comparacao com as minhas proprias solucoes que enviei para
 * os avaliadores, com o objetivo de aprender mais
 * */

// Task 1
// esssa eh a minha solucao
fun TaxiPark.subtractFakeDriversV1(): Set<Driver> =
    allDrivers.subtract(trips.map { it.driver })

// Task 1
fun TaxiPark.subtractFakeDriversV2(): Set<Driver> =
    allDrivers.subtract(trips.flatMap { listOf(it.driver) })


// Task 1
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

// Task 1
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

// Task 2
fun TaxiPark.findPassengerWhoCompletedAtLeastXTrips(min: Int) : Set<Passenger> =
    run {
        trips.flatMap { trip -> trip.passengers }   // lista de passageiros que estiveram em uma viagem
            .groupBy passenger@{ it } // Map<Passanger, List<Passenger>>
            .filter { it.value.size >= min } // manter somente passageiros que fizeram ao menos uma quantidade MIN de fviage
            .keys
    }

// Task 2
fun TaxiPark.findPassengerWhoCompletedAtLeastMinQuantityOfTrips(min: Int) : Set<Passenger> =
    run {
        // funcao partition particiona uma lista em 2 atraves de um criterio
        // o retorno eh um par de lista, no atributo first esta a lista com os items
        // que atendem a criterio e no atributo second o restante dos items
        val (whoCompleteAtLeastMinTrips, _) = allPassengers.partition passenger@{
            passenger ->
            // aqui iteramos pelas viagens e verificamos se um dado passageiro fez uma das
            // viagens da lista, se sim somamos 1. O retorno da funcao sumBy eh o criterio
            // para colocar um motorista na lista 1 ou 2
            trips.sumBy trip@ {
                trip -> if (passenger in trip.passengers) 1 else 0
            } >= min
        }

        whoCompleteAtLeastMinTrips.toSet()
    }

// Task 3