package games.gameOfFifteen

import kotlin.random.Random
import kotlin.system.measureTimeMillis

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        val values = (1..15).toMutableList()
        do {
            shuffle(values)
        } while (!isEven(values))
        values
    }

    private fun <T> swap(values: MutableList<T>, p: Int, q: Int) {
        val aux = values[p]
        values[p] = values[q]
        values[q] = aux
    }

    private fun <T> shuffle(values: MutableList<T>) {
        for (i in values.indices) {
            val idx = Random.nextInt(i + 1)
            swap(values, i, idx)
        }
    }
}

fun main() {
    val randomGameInitializer = RandomGameInitializer()
    val s = measureTimeMillis {
        println(randomGameInitializer.initialPermutation)
    }
    println(s)
}

