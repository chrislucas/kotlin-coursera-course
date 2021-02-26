package com.br.playground3.samples.combinatory


object CatalanNumbers {

    /**
     *
     *
     * */

    @JvmStatic
    fun numbers(ith: Int): MutableList<Int> {
        val numbers = MutableList(ith + 1) { 1 }
        for (k in 2 .. ith) {
            numbers[k] = numbers[k - 1] * ((ith + k) / k)
        }
        return numbers
    }


    @JvmStatic
    fun ithCaltalanNumberRec(ith: Long): Long {
        return if (ith <= 0) {
            1
        } else {
            var acc = 0L
            for (i in 0L until ith) {
                acc += ithCaltalanNumberRec(i) * ithCaltalanNumberRec(ith - i - 1)
            }
            acc
        }
    }
}

fun main() {
    for (i in 3 .. 100L) {
        val ith = CatalanNumbers.ithCaltalanNumberRec(i)
        println("$i: $ith")
    }
}