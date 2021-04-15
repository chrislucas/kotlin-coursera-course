package taxipark

/**
 * Esse arquivo contem as solucoes de cada tarefa vindo dos instrutores do curso
 * (Svetlana Isakova)
 * Isso vai servir de comparacao com as minhas proprias solucoes que enviei para
 * os avaliadores, com o objetivo de aprender mais.
 *
 * Aqui tbm tera minhas solucoes combinandas com as sugestoes dos instrutores
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

// Task 2  first solution
fun TaxiPark.findPassengerWhoCompletedAtLeastXTrips(min: Int): Set<Passenger> =
    run {
        trips.flatMap { trip -> trip.passengers }   // lista de passageiros que estiveram em uma viagem
            .groupBy passenger@{ it } // Map<Passanger, List<Passenger>>
            //.filter { it.value.size >= min } // manter somente passageiros que fizeram ao menos uma quantidade min de viagens
            //.filter { (_, passangers) -> passangers.size >= min }
            .filterValues { passangers -> passangers.size >= min }

            // Podemos usar a funcao map para transformat o resultado do filter numa lista de keys
            // ou recuperar as keys direto do map

            //.map { it.key }
            //.toSet()
            .keys
    }

// Task 2 second solution
fun TaxiPark.findPassengerWhoCompletedAtLeastMinQuantityOfTrips(min: Int): Set<Passenger> =
    run {
        // funcao partition particiona uma lista em 2 atraves de um criterio
        // o retorno eh um par Pair(first, second) de lista, no atributo first esta a lista com os items
        // que atendem a criterio e no atributo second o restante dos items
        val (whoCompleteAtLeastMinTrips, _) = allPassengers.partition passenger@{ passenger ->
            // aqui iteramos pelas viagens e verificamos se um dado passageiro fez uma das
            // viagens da lista, se sim somamos 1. O retorno da funcao sumBy eh o criterio
            // para colocar um motorista na lista 1 ou 2
            trips.sumBy trip@{ trip ->
                if (passenger in trip.passengers) 1 else 0
            } >= min
        }

        whoCompleteAtLeastMinTrips.toSet()
    }

// Task 2 3th solution

fun TaxiPark.filterPassengerWhoCompletedAtLeastXTrips(min: Int): Set<Passenger> =
    run {
        // retornar uma lista com todos os passageiros que nao atendem ao criterio estabelicido
        // na funcao filter not
        allPassengers.filterNot { passenger ->
            // criterio: Passageiro que teve menos corridas do que um valor MIN estabelecido
            //trips.sumBy { trip -> if (passenger in trip.passengers) 1 else 0 } < min

            // podemos reescrever a funcao predicado acima usando a funcao count
            // e a funcao predicado que usava a declaracao if pode ser substituida da seguinte
            // forma: trip -> passenger in trip.passengers
            trips.count { trip -> passenger in trip.passengers } < min
        }.toSet()
    }


// Task 3
// My solution for task 3
fun TaxiPark.frequentPassengers(driver: Driver): Set<Passenger> {
    return this.run {
        val getDriverByTrip = { trip: Trip -> trip.driver }
        val getPassengersOnTrip = { trip: Trip -> trip.passengers }
        val passengers = trips
            .filter { trip -> trip.driver == driver }
            // map <Driver, List<Set<Passanger>> = motorista -> corridas e seus respectivos passageiros
            .groupBy(getDriverByTrip, getPassengersOnTrip)
            // linearizando a lista de passageiros por motorista de forma a deixa-la numa unica lista
            .flatMap { (_, passengers) -> passengers.flatten() }
            .groupingBy { passager -> passager }    // agrupando por passageiro
            .eachCount()    // contando cada passageiro
            .filter { entry -> entry.value > 1 }
        passengers.keys
    }
}

// Task 3 solution 1 by Svetlana Isakova
fun TaxiPark.findFrequentPassengersV1(driver: Driver): Set<Passenger> =
   run {
       trips.filter { trip -> trip.driver == driver } // filtra as corridas por um motorista
           // a partir da lista de viagens recupera-se todos os passageiros
           // lembrando que essa lista pode ter passageiros repitidos
           .flatMap(Trip::passengers)
               // agrupe-os num mapa <Passageiro, List<Passageiro>>
           .groupBy { passenger -> passenger }
           .filter { (_, passengers) -> passengers.size > 1}
           //.filterValues { passengers -> passengers.size > 1 }
           .keys
   }

// Task 3 solution 3: uma mescla da minha primeira solucao com a primeira sugerida pela instrutora
// Task 3 solution 1 by Svetlana Isakova
fun TaxiPark.findFrequentPassengersV3(driver: Driver): Set<Passenger> =
    run {
        trips.filter { trip -> trip.driver == driver } // filtra as corridas por um motorista
            // a partir da lista de viagens recupera-se todos os passageiros
            // lembrando que essa lista pode ter passageiros repitidos
            .flatMap(Trip::passengers)
            // agrupe-os passageiros
            .groupingBy { passenger -> passenger }
                // contem quantos passageiros iguais existem
            .eachCount()
                // filtre por aqueles que aparecem +1x e crie um mapa <Passenger, Int>
            .filterValues { it > 1 }
            .keys
    }

// Task 3 solution 2 by Svetlana Isakova
fun TaxiPark.findFrequentPassengersV2(driver: Driver): Set<Passenger> =
    run {
        // filtrar os passageiros pelo seguinte criterio
        allPassengers.filter {
            passenger ->
            // aqui filtramos viagens com um determinado motorista e
            // dessas viagens verificamos se o passageiro que vem da funcao filter
            // estava nessa viagem. ao final o filtro de passageiros verifica se um determinado
            // passageiro viajou mais de Nx com o motorista escolhido para decidir se ele fica na lista
            // final ou nao
            trips.count {
                trip ->
                // aqueles que fizeram mais de uma viagem com um determinado motorista
                trip.driver == driver && passenger in trip.passengers
            } > 1 // mais de uma viagem
        }.toSet()
    }

// Task 4


// Task 5