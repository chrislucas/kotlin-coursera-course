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
            .filter { (_, passengers) -> passengers.size > 1 }
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
        allPassengers.filter { passenger ->
            // aqui filtramos viagens com um determinado motorista e
            // dessas viagens verificamos se o passageiro que vem da funcao filter
            // estava nessa viagem. ao final o filtro de passageiros verifica se um determinado
            // passageiro viajou mais de Nx com o motorista escolhido para decidir se ele fica na lista
            // final ou nao
            trips.count { trip ->
                // aqueles que fizeram mais de uma viagem com um determinado motorista
                trip.driver == driver && passenger in trip.passengers
            } > 1 // mais de uma viagem
        }.toSet()
    }

// Task 4
// Solucao proposta pela instrutora no curso Svetlana Isakova,
fun TaxiPark.findPassengersWhoHadMostQuantityOfDiscount(): Set<Passenger> {

    /**
     * Particionando as viagens em 2 lista, uma cujos passageiros tiveram desconto
     * e na outra nao
     * */
    val (tripWhosePassengerHaveDiscount, tripWhosePassengerHaveNotDiscount) =
        trips.partition { it.discount is Double }

    fun checkIfPassengerHasMoreTripsWithDiscount(passenger: Passenger): Boolean {
        return tripWhosePassengerHaveDiscount.count { trip -> trip.passengers.contains(passenger) } >
                tripWhosePassengerHaveNotDiscount.count { trip -> trip.passengers.contains(passenger) }
    }

    return allPassengers.filter(::checkIfPassengerHasMoreTripsWithDiscount).toSet()
}

/**
 * Mesma coisa escrita de outra forma muitoooooo similar. Nao quero os creditos, so estou explorando
 * possibildades :)
 * */
fun TaxiPark.findThePassengersWhoHadMoreDiscountThanNoDiscount(): Set<Passenger> {
    return trips.partition { it.discount == null }  // prefiro essa comparacao ao inves it.discount is Double, parece mais clara
        .run {
            val (isNull, isNotNull) = this
            allPassengers.filter { passenger ->
                isNotNull.count { tripWithDiscount -> passenger in tripWithDiscount.passengers } >
                        isNull.count { tripWithoutDiscount -> passenger in tripWithoutDiscount.passengers }
            }.toSet()
        }
}

/**
 * Outra versao sugerida pela instrutora porem com algumas modificacoes para facilitar a leitura
 * como a criacao de lambdas definidas em variaveis pora deixar o codigo mais legivel
 * */
fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscount(): Set<Passenger> {

    // key select
    val selectPassengerForKeyMap: (Passenger) -> Passenger = { it } // passenger -> passenger pra ficar mais claro

    // value transformation
    val relatePassengerToTrip: (Passenger) -> List<Trip> =
        { passenger -> trips.filter { trip -> passenger in trip.passengers } }

    // : Set<Map.Entry<Passenger, List<List<Trip>>>>
    val passengerAndTrips =
        allPassengers.groupBy(selectPassengerForKeyMap, relatePassengerToTrip).entries

    return passengerAndTrips.filter { entry ->

        /**
         * o metodo groupBy retorna um Map<K, List<V>>
         *     No caso temos um Map<Passenger, List<List<Trip>>> entao temos que
         *     desencapsular a lista de viagens (trips) da primeira lista
         * */

        val listOfTripsPerPassenger = entry.value.first()
        val (withDiscount, withoutDiscount) = listOfTripsPerPassenger.partition { trip -> trip.discount != null }
        withDiscount.size > withoutDiscount.size
    }
        .map { it.key }
        .toSet()
}

/**
 * Melhorias na versao de findThePassengersWithMoreDiscountThanNoDiscount
 *
 * o uso do groupBu na solucao acima gera uma estrutura de mapa na seguinte forma
 *  Map<K, List<List<V>> onde a lista de lista so tem uma lista causando um desperdicio
 *  fazendo com que tenhamos que recupera a lista atravs do metodo FIRST = list[0]
 *
 *  Para evitar isso vamos usar a funcao associateBt
 * */

fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscountV2(): Set<Passenger> {
    val set: Set<Map.Entry<Passenger, List<Trip>>> =
        allPassengers.associateBy({ passenger -> passenger }) { passenger ->
            trips.filter { trip -> passenger in trip.passengers }
        }.entries

    return set.filter { (_, trips) ->
        val (withDiscount, withoutDiscount) = trips.partition { trip -> trip.discount != null }
        withDiscount.size > withoutDiscount.size
    }.map { (passenger, _) -> passenger }
        .toSet()
}

fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscountV6(): Set<Passenger> {
    val map: Map<Passenger, List<Trip>> =
        allPassengers.associateBy({ passenger -> passenger }) { passenger ->
            trips.filter { trip -> passenger in trip.passengers }
        }
    return map.filter { (_, trips) ->
        val (withDiscount, withoutDiscount) = trips.partition { trip -> trip.discount != null }
        withDiscount.size > withoutDiscount.size
    }.map { (passenger, _) -> passenger }
        .toSet()
}

fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscountV7(): Set<Passenger> {
    val map: Map<Passenger, List<Trip>> =
        allPassengers.associateBy({ passenger -> passenger }) { passenger ->
            trips.filter { trip -> passenger in trip.passengers }
        }
    return map.filterValues { trips ->
        val (withDiscount, withoutDiscount) = trips.partition { trip -> trip.discount != null }
        withDiscount.size > withoutDiscount.size
    }.keys
}

fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscountConcisely(): Set<Passenger> {

    val qPassengersWhoHaveTraveledWithDiscount: (Passenger) -> Int = { p ->
        trips.count { trip -> p in trip.passengers && trip.discount != null }
    }

    val qPassengersWhoHaveTraveledWithoutDiscount: (Passenger) -> Int = { p ->
        trips.count { trip -> p in trip.passengers && trip.discount == null }
    }

    return allPassengers.filter { passenger ->
        qPassengersWhoHaveTraveledWithDiscount(passenger) >
                qPassengersWhoHaveTraveledWithoutDiscount(passenger)
    }.toSet()
}

/**
 * A mesma solucao usando associateWith, economizando uma lambda
 * */
fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscountV5(): Set<Passenger> {
    // como o conjunto acima
    val map = allPassengers.associateWith { passenger ->
        trips.filter { trip -> passenger in trip.passengers }
    }

    return map.filter { (_, trips) ->
        val (with, without) = trips.partition { trip -> trip.discount != null }
        with.size > without.size
    }.map { (passenger, _) -> passenger }.toSet()
}

/**
 * Uma ideia baseada na solucao proposta pela instrutora
 * O retorno da funcao groupBy me causa encomodo uma vez que temos um mapa Map<K, List<List<V>>> como retorno
 * e precisamos usar a funcao first() para recuperar o a lista de viagens de dentro da lista que o metodo groupBy ja
 * preve como valor do mapa. Para melhor entendimento leia o codigo fonte da funcao groupBy
 * */
fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscountV3(): Set<Passenger> {
    // Grouping<Passenger, List<Trip>>
    return allPassengers.groupingBy { passenger ->
        trips.filter { trip -> passenger in trip.passengers }
    }.run {
        val passengers = mutableSetOf<Passenger>()
        this.sourceIterator().forEach { passenger ->
            val (withDiscount, withoutDiscount) = this.keyOf(passenger).partition { trip -> trip.discount != null }
            if (withDiscount.size > withoutDiscount.size)
                passengers.add(passenger)
        }
        passengers
    }
}

private fun <K, V> Grouping<K, V>.mapping(): Map<K, V> =
    mutableMapOf<K, V>().apply {
        sourceIterator().forEach { e -> this[e] = keyOf(e) }
    }

fun TaxiPark.findThePassengersWithMoreDiscountThanNoDiscountV4(): Set<Passenger> {
    return allPassengers.groupingBy { passenger ->
        trips.filter { trip -> passenger in trip.passengers }
    }.mapping()
        .filter { (_, trips) ->
            val (withDiscount, withoutDiscount) = trips.partition { trip -> trip.discount != null }
            withDiscount.size > withoutDiscount.size
        }.keys

}

// Task 5

fun TaxiPark.theMostFrequentTripDurationPeriod(): IntRange? {
    return this.run {
        val mapDurationTrips = trips.groupBy { trip ->
            trip.run {
                /**
                 * duration - (duration % 10)
                 * se a duracao foi de 29 (coloque aqui Unidade de medida de tempo que quiser)
                 * o intervalor eh entre 20 .. 29
                 * se foi 25 -> 20  .. 29
                 * entao para alcancar esse intervalor, subtraimos a parte unitario do valor
                 * total tornando o inicio do invervalo, pegamos esse mesmo valor e adicionamos
                 * 9. dai temos os intervalos 0 ..  9 , 10 .. 19 e assim por diante
                 * */
                duration - (duration % 10) .. duration - (duration % 10) + 9
            }
        }
        mapDurationTrips.maxByOrNull { (_, trips) -> trips.size }?.key
    }
}