package sample.kotlin.assignment

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

infix fun <T> T.eq(that: T): Boolean {
    val msg = if (this == that) {
        "OK: $this == $that"
    } else {
        "Error: $this != $that"
    }
    println(msg)
    return this == that
}


fun evaluateGuess(secret: String, guess: String): Evaluation {

    val rightPositions = secret.zip(guess).count { it.run { first == second } }

    val commonLetters = "ABCDEF".sumBy { ch ->
        val s = secret.count { it == ch }
        val g = guess.count { it == ch }
        // se s > g return g senao s
        s.coerceAtMost(g)
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}

fun main(args: Array<String>) {
    val result = Evaluation(rightPosition = 1, wrongPosition = 1)
    evaluateGuess("BCDF", "ACEB") eq result
    evaluateGuess("AAAF", "ABCA") eq result
    evaluateGuess("ABCA", "AAAF") eq result
}