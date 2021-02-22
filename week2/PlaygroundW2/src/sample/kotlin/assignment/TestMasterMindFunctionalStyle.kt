package sample.kotlin.assignment

object DifferentLetters {
    //casos de testes retirados da avaliacao do curso
    fun evaluate() {
        listOf(
            Pair(Pair("ABCD", "ABCD"), Evaluation(4, 0))
            , Pair(Pair("ABCD", "CDBA"), Evaluation(0, 4))
            , Pair(Pair("ABCD", "ABDC"), Evaluation(2, 2))
            , Pair(Pair("ABCD", "ABCF"), Evaluation(3, 0))
            , Pair(Pair("DAEF", "FECA"), Evaluation(0, 3))
            , Pair(Pair("ACEB", "BCDF"), Evaluation(1, 1))
            , Pair(Pair("FBAE", "ABCD"), Evaluation(1, 1))

            , Pair(Pair("FBAE", "AFDC"), Evaluation(0, 2))
            , Pair(Pair("FBAE", "CBAE"), Evaluation(3, 0))
            , Pair(Pair("FBAE", "CBFE"), Evaluation(2, 1))
            , Pair(Pair("FBAE", "FBAE"), Evaluation(4, 0))


            , Pair(Pair("EBAC", "ABCD"), Evaluation(1, 2))
            , Pair(Pair("EBAC", "AFCB"), Evaluation(0, 3))
            , Pair(Pair("EBAC", "CBDF"), Evaluation(1, 1))
            , Pair(Pair("EBAC", "EBAC"), Evaluation(4, 0))
        )
            .forEach { pair ->
                val (strings, expected) = pair
                val result = evaluateGuess(strings.first, strings.second)
                expected eq result
            }
    }
}

object RepeatedLetters {

    fun evaluate() {
        // casos de testes retirados da avaliacao do curso
        listOf(
            Pair(Pair("AABC", "ADFE"), Evaluation(1, 0))
            , Pair(Pair("AABC", "ADFA"), Evaluation(1, 1))

            , Pair(Pair("AABC", "DFAA"), Evaluation(0, 2))
            , Pair(Pair("AABC", "DEFA"), Evaluation(0, 1))
            , Pair(Pair("ABCD", "EAAA"), Evaluation(0, 1))
            , Pair(Pair("AABC", "ADFA"), Evaluation(1, 1))

            , Pair(Pair("AABC", "DEFA"), Evaluation(0, 1))
            , Pair(Pair("EDEB", "CBFE"), Evaluation(0, 2))
            , Pair(Pair("CFDF", "FCCD"), Evaluation(0, 3))
            , Pair(Pair("AABC", "AEFD"), Evaluation(1, 0))

            , Pair(Pair("DCFC", "ABEC"), Evaluation(1, 0))
            , Pair(Pair("FDCD", "FBAD"), Evaluation(2, 0))
            , Pair(Pair("DEFA", "AABC"), Evaluation(0, 1))
            , Pair(Pair("DAAE", "AABC"), Evaluation(1, 1))

            , Pair(Pair("BBDC", "DFBB"), Evaluation(0, 3))
            , Pair(Pair("DBFF", "FFDD"), Evaluation(0, 3))
            , Pair(Pair("BDAD", "AAAE"), Evaluation(1, 0))
            , Pair(Pair("FDDB", "CABB"), Evaluation(1, 0))

            , Pair(Pair("BDBC", "DDFC"), Evaluation(2, 0))
            , Pair(Pair("ECDE", "CEEE"), Evaluation(1, 2))
            , Pair(Pair("AAAF", "ABCA"), Evaluation(1, 1))
            , Pair(Pair("BCDA", "AFEA"), Evaluation(1, 0))

            , Pair(Pair("EEEE", "AFEA"), Evaluation(1, 0))
            , Pair(Pair("EEBE", "AFEA"), Evaluation(0, 1))
            , Pair(Pair("EEAD", "EEEE"), Evaluation(2, 0))
            , Pair(Pair("BACD", "EAFF"), Evaluation(1, 0))
        )
            .forEach { pair ->
                val (strings, expected) = pair
                val result = evaluateGuess(strings.first, strings.second)
                expected eq result
            }
    }
}

fun main() {
    println("DifferentLetters Running...")
    DifferentLetters.evaluate()
    println("RepeatedLetters Running...")
    RepeatedLetters.evaluate()
}