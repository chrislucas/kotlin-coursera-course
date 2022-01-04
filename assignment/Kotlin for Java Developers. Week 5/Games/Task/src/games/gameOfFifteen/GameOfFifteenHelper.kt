package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    return permutation.isSorted() || permutation.parityOfPermutation()
}

/*
    Se o numero de trocas para fazer o vetor voltar ao normal for par
    a paridade é par do contrário impar
    https://www.ime.unicamp.br/~marcia/AlgebraLinear/determinantes.html#
 */

fun <T : Comparable<T>> List<T>.parityOfPermutation(): Boolean {
    if (this.isEmpty())
        return false

    val map = mutableMapOf<T, Int>()
    for (i in this.indices) {
        for (j in i + 1 until this.size) {
            if (this[i] > this[j]) {
                map[this[i]] = map[this[i]]?.plus(1) ?: 1
            }
        }
    }
    return map.values.sum() and 1 == 0
}


internal fun <T : Comparable<T>> List<T>.isSorted(): Boolean {
    for (i in 1 until this.size) {
        if (this[i - 1] > this[i])
            return false
    }
    return true
}


fun main() {
    println(listOf(4, 2, 1, 3).parityOfPermutation())
    println(listOf(2, 1, 4, 3).parityOfPermutation())
    println(listOf(2, 1, 3, 4).parityOfPermutation())
    println(listOf(4, 1, 2, 3).parityOfPermutation())
    println(listOf(3, 2, 1, 4).parityOfPermutation())
}